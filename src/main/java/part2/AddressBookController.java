package part2;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping(value = "/addressbook/{id}")
    public String getAddressBook(@PathVariable long id, Model model){
        AddressBook addressBook = addressBookRepository.findById(id);
        return BuddyInfoController.getString(addressBook, model);
    }

    @GetMapping(value = "/addressbook/{id}", produces = "application/json")
    @ResponseBody
    public AddressBook getAddressBook(@PathVariable long id){
        AddressBook addressBook = addressBookRepository.findById(id);
        return addressBook;
    }

    @PostMapping(value = "/addressbook")
    public String createAddressBook(Model model) {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        model.addAttribute("addressBook", addressBook);
        return "address_book_created";
    }

    @PostMapping(value = "/addressbook", produces = "application/json")
    @ResponseBody
    public AddressBook createAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return addressBook;
    }
}
