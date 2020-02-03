import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import part2.AddressBookApplication;

@SpringBootTest(classes = AddressBookApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressBookAppTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() throws Exception{
        this.mockMvc.perform(post("/addressbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Address Book ID: 1")));
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to your Address Book!")));
    }

    @Test
    public void testAddBuddies() throws Exception {
        // Initially there should be no buddies.
        this.mockMvc.perform(get("/addressbook/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("No Books Available")));

        // Add two buddies.
        this.mockMvc.perform(post("/addressbook/1/buddy")
                .param("name", "moe")
                .param("phone-num", "613")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("moe")))
                .andExpect(content().string(containsString("613")));
        this.mockMvc.perform(post("/addressbook/1/buddy")
                .param("name", "babak")
                .param("phone-num", "613")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("moe")))
                .andExpect(content().string(containsString("babak")));

        // Check that the addressbook endpoint is consistent with the post response.
        this.mockMvc.perform(get("/addressbook/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("moe")))
                .andExpect(content().string(containsString("babak")));
    }

    @AfterAll
    public void testRemoveBuddies() throws Exception {
        // Make sure that the addressbook has the two entries moe and babak.
        this.mockMvc.perform(get("/addressbook/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("moe")))
                .andExpect(content().string(containsString("babak")));

        // Remove one buddy and make sure only the one we want to remove is the one removed.
        this.mockMvc.perform(delete("/addressbook/1/buddy")
                .param("name", "moe")
                .param("phone-num", "613")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("babak")))
                .andExpect(content().string(not(containsString("moe"))));

        // Make sure the addressbook endpoint is consistent with the response.
        this.mockMvc.perform(get("/addressbook/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("babak")))
                .andExpect(content().string(not(containsString("moe"))));
    }
}