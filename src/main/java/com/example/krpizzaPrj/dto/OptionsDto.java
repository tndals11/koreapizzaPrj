package com.example.krpizzaPrj.dto;

public class OptionsDto {

    private String optionCode;
    private String optionName;

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return "OptionsDto{" +
                "optionCode='" + optionCode + '\'' +
                ", optionName='" + optionName + '\'' +
                '}';
    }
}
