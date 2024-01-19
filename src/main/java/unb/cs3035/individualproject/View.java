package unb.cs3035.individualproject;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View extends Pane {

    private static BorderPane root;


    public View()
    {
        Image addContactImage = new Image("add.png");
        ImageView addContactImageView = new ImageView(addContactImage);
        addContactImageView.setFitHeight(25);
        addContactImageView.setFitWidth(25);

        Button addContactButton = new Button("", addContactImageView);
        addContactButton.setOnAction(e -> Main.controller.showAddContactDialog());

        Tooltip addTooltip = new Tooltip("Add");
        addContactButton.setTooltip(addTooltip);

        Image editContactImage = new Image("edit.png");
        ImageView editContactImageView = new ImageView(editContactImage);
        editContactImageView.setFitHeight(25);
        editContactImageView.setFitWidth(25);

        Button editContactButton = new Button("", editContactImageView);
        editContactButton.setOnAction(e -> Main.controller.showEditContactDialog());

        Tooltip editTooltip = new Tooltip("Edit");
        editContactButton.setTooltip(editTooltip);

        Image deleteContactImage = new Image("delete.png");
        ImageView deleteContactImageView = new ImageView(deleteContactImage);
        deleteContactImageView.setFitHeight(25);
        deleteContactImageView.setFitWidth(25);

        Button deleteContactButton = new Button("", deleteContactImageView);
        deleteContactButton.setOnAction(e -> Main.controller.deleteSelectedContact());

        Tooltip deleteTooltip = new Tooltip("Delete");
        deleteContactButton.setTooltip(deleteTooltip);

        HBox buttonContainer = new HBox(25,addContactButton, editContactButton, deleteContactButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setStyle("-fx-background-color: #3498db;");
        buttonContainer.setPadding(new Insets(5));

        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #3498db;");
        menuBar.setPadding(new Insets(5));
        menuBar.setPrefHeight(35);

        Menu fileMenu = new Menu("File");

        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(e -> Main.controller.showAddContactDialog());
        fileMenu.getItems().add(newMenuItem);

        MenuItem exitMenuItem = new MenuItem("Quit");
        exitMenuItem.setOnAction(e -> Platform.exit());
        fileMenu.getItems().add(exitMenuItem);

        Menu editMenu = new Menu("Edit");

        MenuItem modifyMenuItem = new MenuItem("Modify");
        modifyMenuItem.setOnAction(e -> Main.controller.showEditContactDialog());
        editMenu.getItems().add(modifyMenuItem);

        MenuItem removeMenuItem = new MenuItem("Remove");
        removeMenuItem.setOnAction(e -> Main.controller.deleteSelectedContact());
        editMenu.getItems().add(removeMenuItem);

        Menu helpMenu = new Menu("Help");

        MenuItem helpScreenItem = new MenuItem("Help Screen");
        helpScreenItem.setOnAction(e -> showHelpScreen());

        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());

        helpMenu.getItems().addAll(helpScreenItem, aboutItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(Main.model.getContactListView());
        root.setBottom(buttonContainer);

    }

    private void showHelpScreen() {
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setTitle("Help Screen");
        helpAlert.setHeaderText(null);

        Image addImage = new Image("add.png");
        ImageView addImageView = new ImageView(addImage);
        addImageView.setFitHeight(30);
        addImageView.setFitWidth(30);
        Label addLabel = new Label("click to add a new contact.", addImageView);

        Image editImage = new Image("edit.png");
        ImageView editImageView = new ImageView(editImage);
        editImageView.setFitHeight(30);
        editImageView.setFitWidth(30);
        Label editLabel = new Label("click to edit the selected contact.", editImageView);

        Image deleteImage = new Image("delete.png");
        ImageView deleteImageView = new ImageView(deleteImage);
        deleteImageView.setFitHeight(30);
        deleteImageView.setFitWidth(30);
        Label deleteLabel = new Label("click to delete the selected contact.", deleteImageView);

        VBox content = new VBox(10, addLabel, editLabel, deleteLabel);
        content.setMaxWidth(300);
        helpAlert.getDialogPane().setContent(content);

        helpAlert.showAndWait();
    }



    private void showAboutDialog() {
        Stage aboutStage = new Stage();
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setTitle("About");

        VBox aboutLayout = new VBox();
        aboutLayout.setAlignment(Pos.CENTER);
        aboutLayout.setSpacing(20);
        aboutLayout.setStyle("-fx-background-color: #3498db;");

        Image companyLogo = new Image("company.png");
        ImageView logoImageView = new ImageView(companyLogo);
        logoImageView.setFitHeight(100);
        logoImageView.setFitWidth(100);

        Label nameLabel = new Label("Devang Vaghasiya");
        nameLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");

        aboutLayout.getChildren().addAll(logoImageView, nameLabel);

        Scene aboutScene = new Scene(aboutLayout, 300, 250);
        aboutStage.setScene(aboutScene);
        aboutStage.showAndWait();
    }

    public BorderPane getRoot()
    {
        return root;
    }


}
