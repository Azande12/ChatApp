// ALL PART 3 METHODS BELOW
public static void storeMessage(Message msg) {
    if (msg.getFlag().equalsIgnoreCase("Sent")) {
        sentMessages.add(msg);
    } else if (msg.getFlag().equalsIgnoreCase("Stored")) {
        storedMessages.add(msg);
    } else if (msg.getFlag().equalsIgnoreCase("Disregard")) {
        disregardedMessages.add(msg);
    }
    messageHashes.add(msg.getMessageHash());
    messageIDs.add(msg.getMessageID());
}

public static void storedMessagesMenu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n--- STORED MESSAGES MENU ---");
    System.out.println("1. Display sender and recipient of all stored messages");
    System.out.println("2. Display longest stored message");
    System.out.println("3. Search for message ID");
    System.out.println("4. Search all messages for a recipient");
    System.out.println("5. Delete message using hash");
    System.out.println("6. Display full sent messages report");
    System.out.print("Choose option: ");

    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
        case 1: displaySenderRecipient(); break;
        case 2: displayLongestMessage(); break;
        case 3: searchByMessageID(); break;
        case 4: searchByRecipient(); break;
        case 5: deleteByHash(); break;
        case 6: displayReport(); break;
        default: System.out.println("Invalid option");
    }
}

public static void displaySenderRecipient() {
    System.out.println("\n--- Stored Messages ---");
    if (storedMessages.isEmpty()) {
        System.out.println("No stored messages found.");
        return;
    }
    for (Message msg : storedMessages) {
        System.out.println("Recipient: " + msg.getRecipient());
        System.out.println("Message: " + msg.getMessage());
        System.out.println("---------------------");
    }
}

public static void displayLongestMessage() {
    if (storedMessages.isEmpty()) {
        System.out.println("No stored messages.");
        return;
    }
    Message longest = storedMessages.get(0);
    for (Message msg : storedMessages) {
        if (msg.getMessage().length() > longest.getMessage().length()) {
            longest = msg;
        }
    }
    System.out.println("\nLongest stored message: " + longest.getMessage());
}

public static void searchByMessageID() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Message ID to search: ");
    String searchID = sc.nextLine();
    for (Message msg : storedMessages) {
        if (msg.getMessageID().equalsIgnoreCase(searchID)) {
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessage());
            return;
        }
    }
    System.out.println("Message ID not found in stored messages.");
}

public static void searchByRecipient() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter recipient number: ");
    String searchRec = sc.nextLine();
    boolean found = false;
    System.out.println("\nMessages for " + searchRec + ":");
    for (Message msg : storedMessages) {
        if (msg.getRecipient().equals(searchRec)) {
            System.out.println(msg.getMessage());
            found = true;
        }
    }
    if (!found) System.out.println("No stored messages for this recipient.");
}

public static void deleteByHash() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter message hash to delete: ");
    String hash = sc.nextLine();
    for (int i = 0; i < storedMessages.size(); i++) {
        if (storedMessages.get(i).getMessageHash().equals(hash)) {
            String deletedMsg = storedMessages.get(i).getMessage();
            storedMessages.remove(i);
            System.out.println("Message: \"" + deletedMsg + "\" successfully deleted.");
            return;
        }
    }
    System.out.println("Message hash not found.");
}

public static void displayReport() {
    System.out.println("\n--- SENT MESSAGES REPORT ---");
    if (sentMessages.isEmpty()) {
        System.out.println("No sent messages to report.");
        return;
    }
    for (Message msg : sentMessages) {
        System.out.println("Message Hash: " + msg.getMessageHash());
        System.out.println("Recipient: " + msg.getRecipient());
        System.out.println("Message: " + msg.getMessage());
        System.out.println("--------------------------------");
    }
}

public static void loadTestData() {
    storeMessage(new Message("M001", "+27834557896", "Did you get the cake?", "Sent"));
    storeMessage(new Message("M002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
    storeMessage(new Message("M003", "+27834484567", "Yohoooo, I am at your gate.", "Disregard"));
    storeMessage(new Message("M004", "0838884567", "It is dinner time !", "Sent"));
    storeMessage(new Message("M005", "+27838884567", "Ok, I am leaving without you.", "Stored"));
}
