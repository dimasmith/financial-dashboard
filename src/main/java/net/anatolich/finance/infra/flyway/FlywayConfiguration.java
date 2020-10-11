package net.anatolich.finance.infra.flyway;

import org.apache.commons.configuration2.Configuration;
import org.flywaydb.core.Flyway;

public class FlywayConfiguration {

    private final Configuration configuration;

    public FlywayConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void runMigrations() {
        final var flyway = Flyway.configure()
            .dataSource(
                configuration.getString("database.url"),
                configuration.getString("database.username"),
                configuration.getString("database.password")
            )
            .load();
        flyway.migrate();
    }
}
