package com.ciklum.test.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;

/**
 *
 */
public class Phone {
    @JsonProperty
    @Pattern.List(value ={
//            +393471234567 | 3381234567
            @Pattern(regexp = "^([+]39)?((38[{8,9}|0])|(34[{7-9}|0])|(36[6|8|0])|(33[{3-9}|0])|(32[{8,9}]))([\\d]{7})$",
                    message = "Phone number have wrong format. Example: +19991234567 or 9991234567"),
//            "(111)123-4567",
//            "111-123-4567",
//            "(111)-123-4567",
            @Pattern(regexp = "^((\\(\\d{3}\\))|(\\d{3}-))\\d{3}-\\d{4}",
                    message = "Phone number have wrong format. Example: +19991234567 or 9991234567")
    })
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PhoneType phoneType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone phone = (Phone) o;

        if (!phoneNumber.equals(phone.phoneNumber)) return false;
        if (phoneType != phone.phoneType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = phoneNumber.hashCode();
        result = 31 * result + phoneType.hashCode();
        return result;
    }
}
