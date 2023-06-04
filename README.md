# blisart-api

For the Alliance! ;)

## Software dependencies

- IntelliJ IDEA.
- JDK >= 17. You can download it from IntelliJ IDEA itself.

## Run the project

These are the steps we currently follow:

1. `docker-compose up` or `podman-compose up`.
2. Start the application from IntelliJ IDEA.
3. `mvn flyway:migrate` to apply all the necessary migrations, which for some reason is not done automatically.