package technical.review;

import java.io.Serializable;

public class EmployeeAddress implements Serializable {
    private String address;
    private String houseName;
    private String streetName;
    private String cityName;
    private String stateName;
    private Integer pinCode;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String address, String houseName, String streetName, String cityName, String stateName, Integer pinCode) {
        this.address = address;
        this.houseName = houseName;
        this.streetName = streetName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "" +
                "Employee Address: " + address + '\n' +
                "Employee House Name: " + houseName + '\n' +
                "Employee Street Name: " + streetName + '\n' +
                "Employee City Name: " + cityName + '\n' +
                "Employee State Name: " + stateName + '\n' +
                "Employee Pin Code: " + pinCode;
    }
}
