package org.example.appHandler;

import org.example.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleService {

    private final ContactStore contactStore;

    public ConsoleService(ContactStore contactStore) {
        this.contactStore = contactStore;
    }

    public void consoleHandler() {

        System.out.println("Введите одну из следующих команд команду: \n" +
                "показать;  \n" +
                "сохранить, пример ввода: \"иванов иван иванович; +890999999; someEmail@example.example\";" + "\n" +
                "удалить, пример ввода \"someEmail@example.example\";" + "\n" +
                "выход");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            System.out.println("consoleHandler " + text);

            if (text.equals("показать")) {
                System.out.println("consoleHandler показать " + text + " " + contactStore.getAllContacts().size());
                contactStore.getAllContacts().forEach(System.out::println);
            }

            if (text.matches(".+\\s.+;.+@.+\\..+")) {
                System.out.println("consoleHandler add " + text);
                String[] contact = text.split(";");
                Contact contact1 = new Contact();
                contact1.setFullName(contact[0]);
                contact1.setPhoneNumber(contact[1]);
                contact1.setEmail(contact[2]);
                contactStore.addContact(contact1);
            }


            if (text.matches("[A-Za-z]+@.+\\..+")) {
                System.out.println("consoleHandler delete " + text);
                contactStore.deleteContact(text);
            }

            if (text.equals("выход")) {
                break;
            }
        }
    }
}
