package part2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int phoneNumber;
    private String address;

    @ManyToOne
    @JsonIgnore
    private AddressBook addressBook;

    public BuddyInfo(String name, int phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public BuddyInfo(String name, int phoneNumber) {
        this(name, phoneNumber, null);
    }

    public BuddyInfo() {
        this(null, 0);
    }

    public String getName() {
        return this.name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNum) {
        this.phoneNumber = phoneNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (id == null)
            return "name: " + this.name + " number: " + phoneNumber;
        else
            return "id: " + id + " name: " + this.name + " number: " + phoneNumber;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
