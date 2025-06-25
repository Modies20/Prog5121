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
import java.util.*;
import java.security.MessageDigest;

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
                case "1" -> sendMessages();
                case "2" -> JOptionPane.showMessageDialog(null, "Coming Soon");
                case "3" -> System.exit(0);
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
Public class POE{
   static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        loadTestData();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display senders and recipients");
            System.out.println("2. Display longest sent message");
            System.out.println("3. Search by message ID");
            System.out.println("4. Search all messages to recipient");
            System.out.println("5. Delete by message hash");
            System.out.println("6. Display full report");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> displaySendersRecipients();
                case 2 -> displayLongestMessage();
                case 3 -> searchByID(scanner);
                case 4 -> searchByRecipient(scanner);
                case 5 -> deleteByHash(scanner);
                case 6 -> displayReport();
            }
        } while (choice != 0);
    }

    static void loadTestData() {
        addMessage("+27834557896", "Did you get the cake?", "Sent");
        addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addMessage("+27834484567", "Yohoooo, I am at your gate", "Disregard");
        addMessage("0838884567", "It is dinner time!", "Sent");
    }

    static void addMessage(String recipient, String text, String flag) {
        String id = UUID.randomUUID().toString();
        String hash = sha256(text);
        Message msg = new Message(recipient, text, id, hash);

        switch (flag.toLowerCase()) {
            case "sent" -> sentMessages.add(msg);
            case "stored" -> storedMessages.add(msg);
            case "disregard" -> disregardedMessages.add(msg);
        }

        messageHashes.add(hash);
        messageIDs.add(id);
    }

    static void displaySendersRecipients() {
        System.out.println("Senders and Recipients of Sent Messages:");
        for (Message msg : sentMessages) {
            System.out.println("Sender: System | Recipient: " + msg.getRecipient());
        }
    }

    static void displayLongestMessage() {
        Message longest = sentMessages.stream().max(Comparator.comparingInt(m -> m.getText().length())).orElse(null);
        if (longest != null)
            System.out.println("Longest Message: " + longest.getText());
    }

    static void searchByID(Scanner sc) {
        System.out.print("Enter message ID: ");
        String id = sc.nextLine();
        for (Message msg : sentMessages) {
            if (msg.getId().equals(id)) {
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getText());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    static void searchByRecipient(Scanner sc) {
        System.out.print("Enter recipient number: ");
        String rec = sc.nextLine();
        boolean found = false;
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(rec)) {
                System.out.println("Message: " + msg.getText());
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this recipient.");
    }

    static void deleteByHash(Scanner sc) {
        System.out.print("Enter message hash to delete: ");
        String hash = sc.nextLine();
        Iterator<Message> iter = sentMessages.iterator();
        while (iter.hasNext()) {
            Message msg = iter.next();
            if (msg.getHash().equals(hash)) {
                iter.remove();
                System.out.println("Message deleted.");
                return;
            }
        }
        System.out.println("Message hash not found.");
    }

    static void displayReport() {
        System.out.println("Sent Messages Report:");
        for (Message msg : sentMessages) {
            System.out.println("ID: " + msg.getId());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getText());
            System.out.println("Hash: " + msg.getHash());
            System.out.println("--------------------------");
        }
    }

    static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes)
                hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            return "HashError";
        }
    }
}
}
   
