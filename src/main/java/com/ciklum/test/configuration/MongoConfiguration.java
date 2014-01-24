package com.ciklum.test.configuration;


import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MongoConfiguration {
    @JsonProperty
    @NotEmpty
    private String mongohost;
    @JsonProperty @Min(1) @Max(65535)
    private int mongoport;
    @JsonProperty @NotEmpty
    private String mongodbname;

    public String getMongohost() {
        return mongohost;
    }

    public void setMongohost(String mongohost) {
        this.mongohost = mongohost;
    }

    public int getMongoport() {
        return mongoport;
    }

    public void setMongoport(int mongoport) {
        this.mongoport = mongoport;
    }

    public String getMongodbname() {
        return mongodbname;
    }

    public void setMongodbname(String mongodbname) {
        this.mongodbname = mongodbname;
    }
}
