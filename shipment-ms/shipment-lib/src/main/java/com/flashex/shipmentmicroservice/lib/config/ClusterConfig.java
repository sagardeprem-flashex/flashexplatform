//package com.flashex.shipmentmicroservice.lib.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
//import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableCassandraRepositories(basePackages = "com.flashex.shipmentmicroservice")
//public class ClusterConfig extends AbstractCassandraConfiguration{
//
//    public static final String KEYSPACE = "shipment";
////
////    @Override
//    public SchemaAction getSchemaAction() {
//        return SchemaAction.CREATE_IF_NOT_EXISTS;
//    }
//
//    @Override
//    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
//        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);
//
//        return Arrays.asList(specification);
//    }
//
//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//    }
//
//    //    @Override
//    protected String getKeyspaceName() {
//            return KEYSPACE;
//        }
//    //
//    ////    @Override
//    public String[] getEntityBasePackages() {
//        return new String[]{"com.flashex.shipmentmicroservice"};
//    }
//
//
////    @Bean
////    public CassandraClusterFactoryBean cluster() {
////        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
////        cluster.setContactPoints("127.1");
////        cluster.setPort(9042);
////        return cluster;
////    }
//
////    @Bean
////    public CassandraMappingContext cassandraMapping()
////      throws ClassNotFoundException {
////            return new BasicCassandraMappingContext();
////        }
////    }
//
//}
//
//
