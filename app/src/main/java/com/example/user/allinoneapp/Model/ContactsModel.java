package com.example.user.allinoneapp.Model;

import java.util.List;

/**
 * Created by user on 2/25/2017.
 */

public class ContactsModel {

    List<String> phoneNumber;
    String firstName;

    public String getFirstAlpha() {
        return firstName.substring(0,1);
    }
    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
