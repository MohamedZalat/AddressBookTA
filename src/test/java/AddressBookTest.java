import org.junit.*;
import part2.AddressBook;
import part2.BuddyInfo;

import static org.junit.Assert.*;

public class AddressBookTest {

    BuddyInfo budInfo, budInfo2;
    AddressBook addBook;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        budInfo = new BuddyInfo("moe", 1234);
        budInfo2 = new BuddyInfo("joe", 5678);

        addBook = new AddressBook();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddressBookAdd() {
        addBook.addBuddy(budInfo);
        assertNotEquals("The following should not be empty", null, addBook);
    }

}