package com.kpg.flatter.utills;

public class Address {
    private String city;
    private String zipCode;
    private String street;
    private String flatNumber;

    public Address() {
    }

    public Address(String city, String zipCode, String street, String flatNumber) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.flatNumber = flatNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}
