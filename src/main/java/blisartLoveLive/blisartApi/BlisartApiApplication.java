package blisartLoveLive.blisartApi;

import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.sql.DataSource;
import java.util.stream.Stream;

@SpringBootApplication
public class BlisartApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlisartApiApplication.class, args);
    }

    // TODO Attempt at configuring Spring so that it doesn't crash if the MySQL
    //  database isn't ready.
    @Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        var dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
        dsv.setValidationQuery(DatabaseDriver.MYSQL.getValidationQuery());
        return dsv;
    }

    // TODO Attempt at configuring Spring so that it doesn't crash if the MySQL
    //  database isn't ready.
    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            // Let beans that need the database depend on the DatabaseStartupValidator
            // like the JPA EntityManagerFactory or Flyway
           String[] flyway = bf.getBeanNamesForType(Flyway.class);
           Stream.of(flyway)
                   .map(bf::getBeanDefinition)
                   .forEach(it -> it.setDependsOn("databaseStartupValidator"));

            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn("databaseStartupValidator"));
        };
    }
}
