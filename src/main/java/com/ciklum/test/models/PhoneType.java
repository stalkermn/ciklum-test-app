package com.ciklum.test.models;

/**
 *
 */
public enum PhoneType {
   HOME("Home"), WORK("Work"), MOBILE("Mobile"), FAX("Fax"), ETC("etc");

    private String value;

    PhoneType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
