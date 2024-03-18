package spring.basics;

import java.io.StringWriter;

public class Branch {

    private String branchName;
    private String ifsCode;
    private String branchID;
    private String branchAccount;
    private String branchContact;

    public Branch() {
    }

    public Branch(String branchName, String ifsCode, String branchID, String branchAccount, String branchContact) {
        this.branchName = branchName;
        this.ifsCode = ifsCode;
        this.branchID = branchID;
        this.branchAccount = branchAccount;
        this.branchContact = branchContact;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfsCode() {
        return ifsCode;
    }

    public void setIfsCode(String ifsCode) {
        this.ifsCode = ifsCode;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchAccount() {
        return branchAccount;
    }

    public void setBranchAccount(String branchAccount) {
        this.branchAccount = branchAccount;
    }

    public String getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(String branchContact) {
        this.branchContact = branchContact;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchName='" + branchName + '\'' +
                ", ifsCode='" + ifsCode + '\'' +
                ", branchID='" + branchID + '\'' +
                ", branchAccount='" + branchAccount + '\'' +
                ", branchContact='" + branchContact + '\'' +
                '}';
    }
}
