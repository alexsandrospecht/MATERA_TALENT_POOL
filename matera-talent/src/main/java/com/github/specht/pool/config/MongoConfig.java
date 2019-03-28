package com.github.specht.pool.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import java.io.IOException;

@Configuration
public class MongoConfig {

    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embedded";

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        final EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        final MongoClient mongoClient = mongo.getObject();

        return new MongoTemplate(mongoClient, MONGO_DB_NAME);
    }

    @Bean
    @DependsOn("mongoTemplate")
    public Jackson2RepositoryPopulatorFactoryBean populator() {
        final Resource sourceData = new ClassPathResource("data.json");
        final Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[] { sourceData });

        return factory;
    }

}