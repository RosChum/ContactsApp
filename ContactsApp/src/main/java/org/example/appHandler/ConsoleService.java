package org.example.appHandler;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleService {

    private final ContactStore contactStore;

    public ConsoleService(ContactStore contactStore) {
        this.contactStore = contactStore;
    }

    public void consoleHandler() {
        String description = "Введите одну из следующих команд команду: \n" +
                "1 - \"показать\" для вывода списка контактов;  \n" +
                "2 - для сохранения контактов введите ФИО, телефон, email через знак \";\", пример ввода: " +
                "\"Иванов Иван Иванович; +890999999; someEmail@example.example\";" + "\n" +
                "3 - для удаления контакта введите email, пример ввода \"someEmail@example.example\";" + "\n" +
                "4 - для выхода введите \"выход\".";

        System.out.println(description);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("показать")) {
                if (!contactStore.getAllContacts().isEmpty()) {
                    contactStore.getAllContacts().forEach(System.out::println);
                } else {
                    System.out.println("Список контактов пуст");
                }
            } else if (text.matches(".+;\\s?\\+\\d+;\\s?[A-Za-z0-9]+@.+\\..+")) {
                contactStore.addContact(Mapper.convertToEntity(text));
            } else if (text.matches("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+")) {
                contactStore.deleteContact(text);
            } else if (text.equalsIgnoreCase("выход")) {
                break;
            } else {
                System.out.println("Введена неверная команда! \n" + description);
            }


        }
    }
}
