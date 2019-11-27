package com.flashex.tripplanningmicroservice.lib.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = { "com.flashex.tripplanningmicroservice.lib" })
public class CassandraConfiguration extends AbstractCassandraConfiguration {
        /*
         * Provide a contact point to the configuration.
         */

        @Value("${spring.data.cassandra.contact-points}")
        private String CONTACT_POINTS;

        @Value("${spring.data.cassandra.keyspace-name}")
        private String KEYSPACE;

        @Override
        public String getContactPoints() {
            return CONTACT_POINTS;
        }

        /*
         * Provide a keyspace name to the configuration.
         */
        @Override
        public String getKeyspaceName() {
            return KEYSPACE;
        }
}
