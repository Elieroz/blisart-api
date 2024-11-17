package blisartLoveLive.blisartApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseMigrationManager {
	// TODO Parameterize?
	// `allowMultiQueries=true` lets us execute the whole content of an SQL file as
	// a single statement. SQL files probably contain many SQL statements.
	private static final String DATABASE_URL = "jdbc:mysql://arthas:arthas@localhost:3306/blizzard?allowMultiQueries=true";

	private static Connection MYSQL;
	static {
		try {
			MYSQL = DriverManager.getConnection(DATABASE_URL);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws SQLException {
		if (args.length != 1) {
			throw new IllegalArgumentException(
					"Specify the type of the migration! apply-pending-updates, revert-last-update or from-scratch.");
		}

		final String migrationType = args[0];

		switch (migrationType) {
		case "apply-pending-updates" -> applyPendingUpdates();
		case "revert-last-update" -> revertLastUpdate();
		case "from-scratch" -> fromScratch();
		default -> throw new IllegalArgumentException("Unexpected value: " + migrationType);
		}
	}

	private static void applyPendingUpdates() throws SQLException {
		final List<String> appliedVersions = getAppliedVersions();
		System.out.println(String.format("Applied version updates: %s", appliedVersions));

		final Map<String, File> versionToUpdateFile = mapVersionsToUpdateFiles();
		System.out.println(String.format("Available version update files: %s", versionToUpdateFile.keySet()));

		versionToUpdateFile.forEach((version, updateFile) -> {
			if (appliedVersions.contains(version)) {
				System.out.println(String.format("Skipping update for version \"%s\" - already applied.", version));
			} else {
				System.out.println(String.format("Applying update for version \"%s...\"", version));

				try {
					final String sql = Files.readString(updateFile.toPath());

					MYSQL.createStatement().executeUpdate(sql);

					addVersionToVersionHistory(version);

					System.out.println("\tDone!");
				} catch (IOException | SQLException e) {
					throw new RuntimeException(e);
				}

			}
		});

		System.out.println("Done!");
	}

	private static void revertLastUpdate() throws SQLException {
		final List<String> appliedVersions = getAppliedVersions();

		if (appliedVersions.size() == 0) {
			throw new RuntimeException("No version updates have been applied! Cannot revert any.");
		}

		final String lastAppliedVersionUpdate = appliedVersions.getLast();

		final File versionRollbackFile = new File(
				String.format("src/main/resources/db/migration/%s/rollback.sql", lastAppliedVersionUpdate));

		System.out.println(String.format("Reverting update \"%s\"...", lastAppliedVersionUpdate));

		try {
			MYSQL.createStatement().execute(Files.readString(versionRollbackFile.toPath()));

			removeVersionFromVersionHistory(lastAppliedVersionUpdate);

			System.out.println("\tDone!");
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void fromScratch() throws SQLException {
		while (getAppliedVersions().size() != 0) {
			revertLastUpdate();
		}

		applyPendingUpdates();
	}

	/**
	 * 
	 * @return The database update versions that are reportedly applied.
	 */
	private static List<String> getAppliedVersions() throws SQLException {
		final ResultSet resultSet = MYSQL.createStatement().executeQuery("SELECT * FROM version_history");

		final List<String> appliedVersions = new ArrayList<>();

		while (resultSet.next()) {
			appliedVersions.add(resultSet.getString("version"));
		}

		return appliedVersions;
	}

	/**
	 * Database version migrations are expected:
	 * <li>to be in the `src/main/resources/db/migration` directory</li>
	 * <li>to be directories named after the database version they work on; for
	 * example: `0.1.0.sql`</li>
	 * <li>to contain the `update.sql` and `rollback.sql` files
	 * 
	 * @return A map that maps each available version update to its SQL update file.
	 */
	private static Map<String, File> mapVersionsToUpdateFiles() {
		final List<File> versionMigrations = Arrays.asList(new File("src/main/resources/db/migration").listFiles());

		return versionMigrations.stream().map(versionMigration -> {
			return Map.entry(versionMigration.getName(), new File(versionMigration, "update.sql"));
		}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private static void addVersionToVersionHistory(final String version) throws SQLException {
		MYSQL.createStatement().execute(String
				.format("INSERT INTO version_history (version, update_date) VALUES ('%s', NOW())", version));
	}

	private static void removeVersionFromVersionHistory(final String version) throws SQLException {
		MYSQL.createStatement().execute(String.format("DELETE FROM version_history WHERE version = '%s'", version));
	}
}
