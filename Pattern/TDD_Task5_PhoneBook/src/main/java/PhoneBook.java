import java.util.*;

public class PhoneBook {

    //контакты в группах
    private Map<String, List<Contact>> groupMap = new HashMap<>();
    //список всех контактов
    private TreeSet<Contact> contacts = new TreeSet<>();

    //создание группы контактов
    public List<Contact> addGroup(String groupName) {
        List<Contact> contactGroup = null;

        //проверка на существование группы
        if (groupMap.containsKey(groupName)) {
            contactGroup = groupMap.get(groupName);
        }
        //создание новой группы
        else {
            contactGroup = new ArrayList<>();
            groupMap.put(groupName, contactGroup);
        }
        return contactGroup;
    }

    //добавление нового контакта
    public Contact add(String name, String mobileNumber) {
        Contact contact = new Contact(name, mobileNumber);
        contacts.add(contact);
        return contact;
    }

    //поиск контакта по номеру телефона
    public Contact findByNumber(String mobileNumber) {
        //поиск наименьшего элемента больше или равного элемента с телефоном mobileNumber
        //контакты упорядочены по номерам телефонов
        Contact result = contacts.ceiling(new Contact(null, mobileNumber));
        return result.getMobileNumber().equals(mobileNumber)? result : null;
    }

    //добавление контакта в группу
    public boolean addContactToGroup(String groupName, Contact contact) {
        List<Contact> contactGroup = addGroup(groupName);
        return contactGroup.add(contact);
    }

    //поиск и вывод контактов по группе
    public List<Contact> showContactsInGroup(String groupName) {
        List<Contact> result = null;
        if (groupMap.containsKey(groupName)) {
            System.out.println(String.format("В группе %s количество контактов %d : %s",
                    groupName, groupMap.get(groupName).size(), addGroup(groupName)));
            result = groupMap.get(groupName);
        } else {
            System.out.println(String.format("Группы %s нет в справочнике.", groupName));
        }
        return result;
    }
}
