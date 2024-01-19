package unb.cs3035.individualproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Set;
import java.util.TreeSet;

public class Model {

    private Set<Contact> contacts = new TreeSet<>();
    private ListView<Contact> contactListView;

    public Model()
    {
        contactListView = new ListView<>(FXCollections.observableArrayList(contacts));
        contactListView.setPrefWidth(300);
        addPredefinedContacts();
        updateContactListView();
    }

    public void addPredefinedContacts() {
        contacts.add(new Contact("John", "Doe", "123-456-7890"));
        contacts.add(new Contact("Jane", "Smith", "987-654-3210"));
        contacts.add(new Contact("Bob", "Johnson", "555-123-4567"));
        contacts.add(new Contact("Alice", "Williams", "789-012-3456"));
        contacts.add(new Contact("Charlie", "Brown", "321-654-0987"));
        contacts.add(new Contact("Eva", "Davis", "444-555-6666"));
        contacts.add(new Contact("Frank", "Miller", "777-888-9999"));
        contacts.add(new Contact("Grace", "Jones", "555-111-2233"));
        contacts.add(new Contact("Henry", "Smith", "555-444-5566"));
        contacts.add(new Contact("Isabel", "Johnson", "555-777-8899"));
        contacts.add(new Contact("Jack", "Brown", "555-000-1122"));
        contacts.add(new Contact("Katherine", "Davis", "555-333-4455"));
        contacts.add(new Contact("Liam", "Miller", "555-666-7788"));
        contacts.add(new Contact("Mia", "Williams", "555-999-0011"));
        contacts.add(new Contact("Sophia", "Johnson", "555-111-2233"));
        contacts.add(new Contact("Ethan", "Miller", "555-444-5566"));
        contacts.add(new Contact("Ava", "Smith", "555-777-8899"));
        contacts.add(new Contact("Logan", "Brown", "555-000-1122"));
        contacts.add(new Contact("Zoe", "Davis", "555-333-4455"));
        contacts.add(new Contact("Carter", "Wilson", "555-666-7788"));
        contacts.add(new Contact("Harper", "Anderson", "555-999-0011"));
        contacts.add(new Contact("Noah", "Wilson", "555-222-3344"));
        contacts.add(new Contact("Olivia", "Moore", "555-555-6677"));
        contacts.add(new Contact("Peter", "Anderson", "555-888-9900"));
        contacts.add(new Contact("Quinn", "Parker", "555-123-4567"));
    }

    public ListView<Contact> getContactListView()
    {
        return contactListView;
    }

    public Set<Contact> getContacts()
    {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts)
    {
        this.contacts = contacts;
    }

    public void updateContactListView() {
        ObservableList<Contact> observableContacts = FXCollections.observableArrayList(contacts);
        contactListView.setItems(observableContacts);
    }

}

    class Contact implements Comparable<Contact> {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    @Override
    public int compareTo(Contact other) {
        return this.firstName.compareTo(other.firstName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + phoneNumber;
    }
}
