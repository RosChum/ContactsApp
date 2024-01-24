package org.example.appHandler;

import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContactStore {

    @Value(value = "${resource-path}")
    private String resourcePath;

    private final Map<String, Contact> contactList;

    public ContactStore() {
        this.contactList = new HashMap<>();
    }

    public void addContact(Contact contact) {
        if (!contactList.containsKey(contact.getEmail())) {
            contactList.put(contact.getEmail().trim(), contact);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resourcePath, true))) {
                bufferedWriter.write(Mapper.convertToString(contact), 0, Mapper.convertToString(contact).length());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Контакт сохранен");
        } else {
            System.out.println("Контакт с таким email уже существует");
        }
    }

    public void deleteContact(String email) {
        if (contactList.containsKey(email)) {
            contactList.remove(email);
            System.out.println("Контакт удален");
        } else {
            System.out.println("Контакт с таким email не найден");
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contactList.values());
    }

    public void initContacts(HashMap<String, Contact> contacts) {
        contactList.putAll(contacts);
    }
}
