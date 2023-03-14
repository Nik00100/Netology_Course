public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        Contact Sergey = phoneBook.add("Сергей", "+7(999)-999-99-99");
        Contact Marina = phoneBook.add("Марина", "+7(999)-888-88-88");
        Contact Fedor = phoneBook.add("Федор","+7(999)-777-77-77");

        phoneBook.addContactToGroup("Работа", Sergey);
        phoneBook.addContactToGroup("Работа", Fedor);
        phoneBook.addContactToGroup("Семья", Marina);
        phoneBook.addContactToGroup("Друзья", Fedor);

        phoneBook.showContactsInGroup("Работа");
        phoneBook.showContactsInGroup("Семья");
        phoneBook.showContactsInGroup("Друзья");
        phoneBook.showContactsInGroup("Разное");

        phoneBook.findByNumber("+7(999)-888-88-88");
        phoneBook.findByNumber("+7(999)-111-11-11");
    }
}