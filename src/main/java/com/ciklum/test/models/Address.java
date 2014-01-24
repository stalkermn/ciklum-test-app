package com.ciklum.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

import java.util.Set;

/**
 */
public class Address {
    @Id
    @ObjectId
    private String id;
    @JsonProperty
    private String city;
    @JsonProperty
    private String state;
    @JsonProperty
    private String country;
    @JsonProperty
    private String postalCode;
    @JsonProperty
    private Set<Phone> phones;



    public Address(){}

    public Address(String id) {
        this.id = id;
    }

    public Address(String id, final Set<Phone> phones) {
        this.id = id;
        this.phones = phones;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!country.equals(address.country)) return false;
        if (!phones.equals(address.phones)) return false;
        if (!postalCode.equals(address.postalCode)) return false;
        if (!state.equals(address.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + postalCode.hashCode();
        result = 31 * result + phones.hashCode();
        return result;
    }
}
