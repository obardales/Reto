package com.reto.obardales.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reto.obardales.service.dto.PhoneDto;

public class RequestPostUsersPhone {

    private String number;

    @JsonProperty("citycode")
    private String cityCode;

    @JsonProperty("countrycode")
    private String countryCode;

    public PhoneDto toPhone() {
        PhoneDto phone = new PhoneDto();
        phone.setNumber(this.getNumber());
        phone.setCityCode(this.getCityCode());
        phone.setCountryCode(this.getCountryCode());
        return phone;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
