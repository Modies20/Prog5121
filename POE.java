/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import com.mycompany.poe.Login;
import static com.mycompany.poe.Login.registerCellPhoneNumber;
import static com.mycompany.poe.Login.registerPassword;
import static com.mycompany.poe.Login.registerUserName;
import com.mycompany.poe.Message;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
/*public*/ class POE {

    /**
     * @param args the command line arguments
     */


        public static void main(String[] args) {
        
        //prompt user to enter their first name and last name
        String firstName = JOptionPane.showInputDialog(null,"Enter your first name","FirstName",
                JOptionPane.QUESTION_MESSAGE);
        
        String lastName = JOptionPane.showInputDialog(null,"Enter your last name","Last Name",
                JOptionPane.QUESTION_MESSAGE);
        
        // Login class calls 
        Login.registerUser();
        Login.checkUserName (registerUserName);
        Login.checkPasswordComplexity (registerPassword);
        Login.checkCellPhoneNumber (registerCellPhoneNumber);
        Login.returningLoginStatus();
        Login.loginUser();
    }
}
    

    public static void main(String[] args) {
        // Login Simulated
        boolean loggedIn = login();
        if(!loggedIn) System.exit(0);
        
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        
        while(true) {
            String input = JOptionPane.showInputDialog(
                "1) Send Messages\n2) Show recently sent messages\n3) Quit"
            );
            
            switch(input) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case "3":
                    System.exit(0);
            }
        }
    }

    private static boolean login() {
        String user = JOptionPane.showInputDialog("Enter username");
        String pass = JOptionPane.showInputDialog("Enter password");
        return "admin".equals(user) && "1234".equals(pass);
    }

    private static void sendMessages() {
        int count = Integer.parseInt(JOptionPane.showInputDialog("Number of messages to send?"));
        
        for(int i=0; i<count; i++) {
            Message msg = new Message();
            
            // Generate 10-digit ID
            msg.setMessageID(String.format("%010d", new Random().nextInt(1000000000)));
            
            // Recipient Validation
            String recipient = JOptionPane.showInputDialog("Recipient number (+XX...)");
            if(msg.checkRecipientCell(recipient) != 1) {
                JOptionPane.showMessageDialog(null, "Invalid number format");
                continue;
            }
            
            // Message Validation
            String content = JOptionPane.showInputDialog("Enter message");
            if(content.length() > 250) {
                JOptionPane.showMessageDialog(null, 
                    "Message exceeds 250 characters by " + (content.length()-250));
                continue;
            }
            
            // Send/Store Logic
            String choice = JOptionPane.showInputDialog(
                "1) Send\n2) Disregard\n3) Store"
            );
            String result = msg.sentMessage(Integer.parseInt(choice));
            
            JOptionPane.showMessageDialog(null, 
                "MessageID: " + msg.getMessageID() + "\n" +
                "Hash: " + msg.createMessageHash() + "\n" +
                "Recipient: " + recipient + "\n" +
                "Message: " + content
            );
        }
    }

    
