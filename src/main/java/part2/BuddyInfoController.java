package part2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BuddyInfoController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @PostMapping(value="addressbook/{id}/buddy")
    public String addBuddyInfo(@PathVariable long id,
                               @RequestParam(name="name") String name,
                               @RequestParam(name="phone-num") int phoneNum,
                               Model model) {
        AddressBook addressBook = createAddressBookBuddy(id, name, phoneNum);
        return getString(addressBook, model);
    }

    @PostMapping(value="/addressbook/{id}/buddy", produces = "application/json")
    @ResponseBody
    public AddressBook addBuddyInfo(@PathVariable long id,
                                    @RequestParam(name="name") String name,
                                    @RequestParam(name="phone-num") int phoneNum) {
        AddressBook addressBook = createAddressBookBuddy(id, name, phoneNum);
        return addressBook;
    }

    private AddressBook createAddressBookBuddy(long id, String name, int phoneNum) {
        BuddyInfo buddyInfo = new BuddyInfo(name, phoneNum);

        AddressBook addressBook = addressBookRepository.findById(id);
        System.out.println("Pre-adding buddy: " + addressBook.getBuddies());
        addressBook.addBuddy(buddyInfo);
        System.out.println("Post-adding buddy: " + addressBook.getBuddies());

        addressBookRepository.save(addressBook);
        return addressBook;
    }

    public static String getString(AddressBook addressBook, Model model) {
        List<BuddyInfo> buddies = addressBook.getBuddies();

        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", buddies);

        return "address_book";
    }

    @DeleteMapping(value="addressbook/{id}/buddy")
    public String removeBuddyInfo(@PathVariable long id,
                                  @RequestParam(name="name") String name,
                                  Model model) {
        AddressBook addressBook = removeAddressBookBuddy(id, name);
        return getString(addressBook, model);
    }

    @DeleteMapping(value="/addressbook/{id}/buddy", produces = "application/json")
    @ResponseBody
    public AddressBook removeBuddyInfo(@PathVariable long id,
                                       @RequestParam(name="name") String name,
                                       @RequestParam(name="phone-num") int phoneNum) {
        AddressBook addressBook = removeAddressBookBuddy(id, name);

        return addressBook;
    }

    private AddressBook removeAddressBookBuddy(long id, String name) {
        List<BuddyInfo> buddyInfos = buddyInfoRepository.findByName(name);

        BuddyInfo buddyInfo = null;
        if (buddyInfos.size() > 0) buddyInfo = buddyInfos.get(0);

        AddressBook addressBook = addressBookRepository.findById(id);
        addressBook.removeBuddy(buddyInfo);

        addressBookRepository.save(addressBook);

        return addressBook;
    }
}
