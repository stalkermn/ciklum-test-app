package com.ciklum.test.configuration;

import org.codehaus.jackson.annotate.JsonProperty;

import com.yammer.dropwizard.config.Configuration;

public class AddressManagerConfiguration extends Configuration {

    @JsonProperty
    private MongoConfiguration mongoConfiguration;

    public MongoConfiguration getMongoConfiguration() {
        return mongoConfiguration;
    }

    public void setMongoConfiguration(MongoConfiguration mongoConfiguration) {
        this.mongoConfiguration = mongoConfiguration;
    }
}
