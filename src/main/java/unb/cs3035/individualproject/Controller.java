package unb.cs3035.individualproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Controller {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField phoneNumberField;


    public void showAddContactDialog() {
        Stage addContactStage = new Stage();
        addContactStage.initModality(Modality.APPLICATION_MODAL);
        addContactStage.setTitle("Add Contact");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        Button confirmButton = new Button("Add");
        confirmButton.setOnAction(e -> {
            addContact(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText());
            addContactStage.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> addContactStage.close());

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(new Label("Phone Number:"), 0, 2);
        grid.add(phoneNumberField, 1, 2);
        grid.add(confirmButton, 0, 3);
        grid.add(cancelButton, 1, 3);

        Scene addContactScene = new Scene(grid, 300, 200);
        addContactStage.setScene(addContactScene);
        addContactStage.showAndWait();
    }

    public void showEditContactDialog() {
        Contact selectedContact = Main.model.getContactListView().getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Contact Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a contact to edit.");
        alert.getDialogPane().setMaxWidth(300);

        if (selectedContact != null) {
            Stage editContactStage = new Stage();
            editContactStage.initModality(Modality.APPLICATION_MODAL);
            editContactStage.setTitle("Edit Contact");

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10, 10, 10, 10));

            firstNameField = new TextField();
            firstNameField.setPromptText("First Name");
            firstNameField.setText(selectedContact.getFirstName());

            lastNameField = new TextField();
            lastNameField.setPromptText("Last Name");
            lastNameField.setText(selectedContact.getLastName());

            phoneNumberField = new TextField();
            phoneNumberField.setPromptText("Phone Number");
            phoneNumberField.setText(selectedContact.getPhoneNumber());

            Button confirmButton = new Button("Save");
            confirmButton.setOnAction(e -> {
                updateContact(selectedContact, firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText());
                editContactStage.close();
            });

            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> editContactStage.close());

            grid.add(new Label("First Name:"), 0, 0);
            grid.add(firstNameField, 1, 0);
            grid.add(new Label("Last Name:"), 0, 1);
            grid.add(lastNameField, 1, 1);
            grid.add(new Label("Phone Number:"), 0, 2);
            grid.add(phoneNumberField, 1, 2);
            grid.add(confirmButton, 0, 3);
            grid.add(cancelButton, 1, 3);

            Scene editContactScene = new Scene(grid, 300, 200);
            editContactStage.setScene(editContactScene);
            editContactStage.showAndWait();
        }
        else
        {

            alert.showAndWait();
        }
    }


    public void addContact(String firstName, String lastName, String phoneNumber) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty()) {
            Contact contact = new Contact(firstName, lastName, phoneNumber);

            List<Contact> tempList = new ArrayList<>(Main.model.getContacts());
            tempList.add(contact);

            tempList.sort(Contact::compareTo);

            Main.model.setContacts(new TreeSet<>(tempList));

            updateContactListView();
            clearInputFields();
        }
    }



    public void deleteSelectedContact() {
        Contact selectedContact = Main.model.getContactListView().getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Contact Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a contact to remove.");
        alert.getDialogPane().setMaxWidth(300);

        if (selectedContact != null) {
            Main.model.getContacts().remove(selectedContact);
            updateContactListView();
        }
        else
        {
            alert.showAndWait();
        }
    }


    public void updateContact(Contact contact, String firstName, String lastName, String phoneNumber) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty()) {

            Contact updatedContact = new Contact(firstName, lastName, phoneNumber);

            Main.model.getContacts().remove(contact);

            List<Contact> tempList = new ArrayList<>(Main.model.getContacts());
            tempList.add(updatedContact);

            tempList.sort(Contact::compareTo);

            Main.model.setContacts(new TreeSet<>(tempList));

            updateContactListView();
            clearInputFields();
        }
    }


    public void updateContactListView()
    {
        Main.model.updateContactListView();
    }



    private void clearInputFields() {
        firstNameField.clear();
        lastNameField.clear();
        phoneNumberField.clear();
    }

}
