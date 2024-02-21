package com.arnab.android_mysql_nodejs.pojo;

public class Country {
    private int countryId;
    private String countryCode;
    private String englishname;
    private String frenchname;

    public Country() {
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getFrenchname() {
        return frenchname;
    }

    public void setFrenchname(String frenchname) {
        this.frenchname = frenchname;
    }
}