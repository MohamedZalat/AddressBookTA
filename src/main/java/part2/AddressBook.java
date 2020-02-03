package part2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "addressBook")
    private List<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<BuddyInfo>();
    }

    public void addBuddy(BuddyInfo buddy) {
        buddy.setAddressBook(this);
        buddies.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddies.remove(buddy);
        buddy.setAddressBook(null);
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String returnme = "--------\n";
        for (BuddyInfo bud: buddies) {
            returnme += bud.toString() + "\n--------\n";
        }
        return returnme;
    }

    public static void main(String[] args) {
        AddressBook addBook = new AddressBook();
        BuddyInfo bud = new BuddyInfo("moe", 1234);
        BuddyInfo myBud = new BuddyInfo("joe", 5678);

        addBook.addBuddy(bud);
        addBook.addBuddy(myBud);
        System.out.println(addBook.toString());
    }
}
