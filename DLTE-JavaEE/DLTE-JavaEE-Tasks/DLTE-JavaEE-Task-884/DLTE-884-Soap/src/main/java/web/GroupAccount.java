package web;

import application.db.Entities.Customer;

import java.util.List;

public class GroupAccount {
    private Customer CustomerList;

    public GroupAccount() {
    }

    public GroupAccount(Customer customerList) {
        CustomerList = customerList;
    }

    public Customer getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(Customer customerList) {
        CustomerList = customerList;
    }

    @Override
    public String toString() {
        return "GroupAccount{" +
                "CustomerList=" + CustomerList +
                '}';
    }
}
