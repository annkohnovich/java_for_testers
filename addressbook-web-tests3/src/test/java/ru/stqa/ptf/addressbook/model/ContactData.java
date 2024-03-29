package ru.stqa.ptf.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

public class ContactData {
    @Id
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    @Type(type = "text")
    @Column(name = "mobile")
    private String mobilePhone;
    @Expose
    @Type(type = "text")
    @Column(name = "home")
    private String homePhone;
    @Expose
    @Type(type = "text")
    @Column(name = "work")
    private String workPhone;

    @Transient
    private String allPhones;
    @Expose
    @Type(type = "text")
    private String address;
    @Expose
    @Type(type = "text")
    private String email;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;
    @Transient
    private String allEmails;
    @Transient
    private File photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();


    public int getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() { return allPhones; }

    public String getEmail() {
        return email;
    }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllEmails() { return allEmails; }

    public String getAddress() { return address; }

    public File getPhoto() { return photo; }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(homePhone, that.homePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(allPhones, that.allPhones) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mobilePhone, homePhone, workPhone, allPhones, address, email, groups);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}


