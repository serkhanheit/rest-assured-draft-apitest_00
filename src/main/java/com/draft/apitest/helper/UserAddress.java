package com.draft.apitest.helper;

public class UserAddress{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private UserAddressGeo geo;

    UserAddress() {};

    // UserAddress(String street, String suite, String zipcode, UserAddressGeo geo){
    //     this.street = street;
    //     this.suite = suite;
    //     this.zipcode = zipcode;
    //     this.geo = geo;
    // }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public UserAddressGeo getGeo() {
        return geo;
    }

    public void setGeo(UserAddressGeo geo) {
        this.geo = geo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
