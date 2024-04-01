package entity.console;

//employeeAddress entity
public class EmployeeAddress {
    private String houseName;
    private String streetName;
    private String cityName;
    private String stateName;
    private Integer pinCode;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String houseName, String streetName, String cityName, String stateName, Integer pinCode) {
        this.houseName = houseName;
        this.streetName = streetName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
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
}
