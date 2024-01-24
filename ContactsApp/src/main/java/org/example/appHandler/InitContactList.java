package org.example.appHandler;

import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class InitContactList {

    private ContactStore contactStore;

    @Value(value = "${resource-path}")
    private String resourcePath;

    public InitContactList(ContactStore contactStore) {
        this.contactStore = contactStore;
    }

    @PostConstruct
    public void initContactList() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resourcePath))) {

            HashMap<String, Contact> contactMap = new HashMap<>();
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                if (!text.isEmpty()) {
                    Contact contact = Mapper.convertToEntity(text);
                    contactMap.put(contact.getEmail(), contact);
                } else {
                    break;
                }
            }
            contactStore.initContacts(contactMap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
