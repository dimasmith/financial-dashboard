package net.anatolich.finance.infra.configuration;

import net.anatolich.finance.DashboardApplication;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.EnvironmentConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ExternalizedConfiguration {

    private final CompositeConfiguration configuration;

    public ExternalizedConfiguration() {
        final var configurations = new Configurations();
        configuration = new CompositeConfiguration();
        configuration.addConfiguration(new EnvironmentConfiguration());
        configuration.addConfiguration(new SystemConfiguration());
        try {
            final var properties = configurations.properties(
                DashboardApplication.class.getResource("/default.properties")
            );
            configuration.addConfiguration(properties);
        } catch (ConfigurationException e) {
            throw new IllegalStateException("Cannot read properties", e);
        }
    }

    public Configuration configuration() {
        return configuration;
    }
}
