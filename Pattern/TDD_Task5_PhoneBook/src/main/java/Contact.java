import java.util.Objects;

public class Contact implements Comparable<Contact>{
    private String name;
    private String mobileNumber;

    public Contact(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return mobileNumber == contact.mobileNumber && Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mobileNumber);
    }

    @Override
    public String toString() {
        return "имя = " + name + ", телефон = " + mobileNumber + '.';
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public int compareTo(Contact c) {
        return mobileNumber.compareTo(c.getMobileNumber());
    }
}
