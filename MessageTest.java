
import com.mycompany.poe.Message;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
public class MessageTest {
     Message msg = new Message();

    @Test
    void testValidMessageLength() {
        String content = "a".repeat(250);
        assertTrue(content.length() == 250);
    }

    @Test
    void testInvalidRecipient() {
        assertEquals(0, msg.checkRecipientCell("081123"));
    }
}

