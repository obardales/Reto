package com.reto.obardales.service.dto;

import com.reto.obardales.repository.entities.Phone;

public class PhoneDto {

    private String number;
    private String cityCode;
    private String countryCode;
    private String userId;

    public PhoneDto() {
    }

    public PhoneDto(String number, String cityCode, String countryCode, String userId) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.userId = userId;
    }

    public static PhoneDto from(Phone phone) {
        return new PhoneDto(
                phone.getNumber(),
                phone.getCityCode(),
                phone.getCountryCode(),
                phone.getUser() != null ? phone.getUser().getId() : null);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
