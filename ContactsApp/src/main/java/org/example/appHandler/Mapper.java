package org.example.appHandler;

import org.example.model.Contact;

public class Mapper {

    public static Contact convertToEntity(String meaning) {
        String[] text = meaning.split(";");
        Contact contact = new Contact();
        contact.setFullName(text[0].trim());
        contact.setPhoneNumber(text[1].trim());
        contact.setEmail(text[2].trim());
        return contact;
    }

    public static String convertToString(Contact contact) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contact.getFullName().trim()).append(";")
                .append(contact.getPhoneNumber().trim()).append(";").append(contact.getEmail().trim()).append("\n");
        return stringBuilder.toString();
    }
}
