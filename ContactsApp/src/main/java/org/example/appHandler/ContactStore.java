package org.example.appHandler;

import org.example.model.Contact;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContactStore {

    private final Map<String, Contact> contactList;

    public ContactStore() {
        this.contactList = new HashMap<>();
    }

    public boolean addContact(Contact contact) {
        if (!contactList.containsKey(contact.getEmail())) {
            contactList.put(contact.getEmail().trim(), contact);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./src/main/resources/default-contacts.txt", true))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(contact.getFullName()).append(";")
                        .append(contact.getPhoneNumber()).append(";").append(contact.getEmail()).append("\n");
                bufferedWriter.write(stringBuilder.toString(), 0, stringBuilder.length());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public void deleteContact(String email) {
        contactList.remove(email);
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contactList.values());
    }

    @PostConstruct
    public void initContactList() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/main/resources/default-contacts.txt"))) {


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
