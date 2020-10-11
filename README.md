# Financial Dashboard

A toy project to play with the JavaFX. 
The project does not use Spring intentionally. 
The idea is to shake the rust off and configure some thins by hand :)

## Running

The financial dashboard needs a database to run. 
Use included docker-compose file to start MySql.
After that you may run the project. 

```shell script
docker-compose up -d
mvn clean javafx:run
```

### HiDPI

The UI scaling does not work consistently in all operating systems.
Sometimes it is necessary to enforce scaling manually. 
Use the `-Dglass.gtk.uiScale=2.0` property to scale the UI.

## Configuration options

The project uses `commons-configuration` to keep settings.
Use system properties to override defaults. 

### Available properties

| Property | Default value | Description |
| :--- | :--- | :--- |
| `database.url` | `jdbc:mysql://localhost:3306/finance` | the JDBC url of the database connection |
| `database.username` | `finance` | the user name of the database connection |
| `database.password` | `s3cr3t` | the password for the connection |

