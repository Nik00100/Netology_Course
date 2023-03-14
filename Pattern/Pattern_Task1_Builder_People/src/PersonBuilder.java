public class PersonBuilder {
    private String name;
    private String surname;
    private int age;
    private String address;


    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (!(age >= 0 && age < 140)) {
            throw new IllegalArgumentException("Недопустимый возраст!");
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        Person person = new Person(name, surname, age);
        person.setAddress(address);
        if (person.getName() == null && person.getSurname() == null) {
            throw new IllegalStateException("Указаны не все обязательные поля!");
        }
        return person;
    }
}
