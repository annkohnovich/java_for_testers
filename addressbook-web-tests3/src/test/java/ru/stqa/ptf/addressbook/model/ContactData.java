package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String mobilePhone;
    private final String email;

    public ContactData(String first_name, String last_name, String mobile_phone, String email) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.mobilePhone = mobile_phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }
}
