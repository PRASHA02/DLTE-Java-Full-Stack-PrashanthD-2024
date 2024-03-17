package web;

import application.db.Entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class GroupAccount {
    private List<Customer> customerList;

    public GroupAccount() {
    }

    public GroupAccount(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "GroupAccount{" +
                "customerList=" + customerList +
                '}';
    }
}
