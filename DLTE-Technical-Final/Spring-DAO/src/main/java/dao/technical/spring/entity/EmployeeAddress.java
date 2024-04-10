package dao.technical.spring.entity;

//backend employeeAddress entity
public class EmployeeAddress {

    private String houseName;
    private String streetName;
    private String cityName;
    private String stateName;
    private Integer pinCode;
    private Integer empID;
    private String flag;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String houseName, String streetName, String cityName, String stateName, Integer pinCode,Integer empID, String flag) {
        this.houseName = houseName;
        this.streetName = streetName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
        this.empID = empID;
        this.flag = flag;
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

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "houseName='" + houseName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", pinCode=" + pinCode +
                ", empID=" + empID +
                ", flag='" + flag + '\'' +
                '}';
    }
}
