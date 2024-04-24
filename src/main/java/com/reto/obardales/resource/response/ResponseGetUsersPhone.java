package com.reto.obardales.resource.response;

import com.reto.obardales.service.dto.PhoneDto;

public class ResponseGetUsersPhone {

    private String id;
    private String number;
    private String cityCode;
    private String countryCode;

    public ResponseGetUsersPhone() {
    }

    public ResponseGetUsersPhone(String id, String number, String cityCode, String countryCode) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public static ResponseGetUsersPhone from(PhoneDto phoneDto) {
        return new ResponseGetUsersPhone(
                null,
                String.valueOf(phoneDto.getNumber()),
                String.valueOf(phoneDto.getCityCode()),
                String.valueOf(phoneDto.getCountryCode()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
