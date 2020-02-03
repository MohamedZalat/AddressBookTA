import org.junit.*;
import part2.BuddyInfo;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    BuddyInfo budInfo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        budInfo = new BuddyInfo("moe", 1234);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBuddyInfo() {
        BuddyInfo newBud = budInfo;
        assertEquals("The name should be moe", "moe", newBud.getName());
        assertEquals("The phone number should be 1234", 1234, newBud.getPhoneNumber());
    }

    @Test
    public void testGetName() {
        assertEquals("The name should be moe.", "moe", budInfo.getName());
    }

    @Test
    public void testSetNameWrong() {
        budInfo.setName("joe");
        assertNotEquals("Should be joe now.", "moe", budInfo.getName());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("The phoneNum should be 1234.", 1234, budInfo.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        budInfo.setPhoneNumber(2468);
        assertEquals("Should be 2468 now.", 2468, budInfo.getPhoneNumber());
    }

}