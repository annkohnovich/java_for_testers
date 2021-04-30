package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String mobilePhone;
    private final String email;

    public ContactData(String first_name, String last_name, String mobile_phone, String email) {
        this.id = null;
        this.firstName = first_name;
        this.lastName = last_name;
        this.mobilePhone = mobile_phone;
        this.email = email;
    }

    public ContactData(String id, String first_name, String last_name, String mobile_phone, String email) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.mobilePhone = mobile_phone;
        this.email = email;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}


