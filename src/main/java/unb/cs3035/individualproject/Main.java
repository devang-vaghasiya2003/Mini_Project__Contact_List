package unb.cs3035.individualproject;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application {

    public static final Model model = new Model();

    public static final Controller controller = new Controller();

    public static final View view = new View();

    @Override
    public void start(Stage primaryStage) {
        VBox splashLayout = new VBox();
        splashLayout.setStyle("-fx-background-color: #3498db;");
        splashLayout.setPrefSize(300, 500);
        splashLayout.setPadding(new Insets(20));
        splashLayout.setAlignment(Pos.CENTER);

        Image splashImage = new Image("contacts.png");
        ImageView splashImageView = new ImageView(splashImage);
        splashImageView.setFitHeight(100);
        splashImageView.setFitWidth(100);

        Label titleLabel = new Label("Contact List");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");

        splashLayout.getChildren().addAll(splashImageView, titleLabel);

        Scene splashScene = new Scene(splashLayout);
        primaryStage.setScene(splashScene);
        primaryStage.show();

        PauseTransition splashDelay = new PauseTransition(Duration.seconds(1.5));
        splashDelay.play();
        splashDelay.setOnFinished(event -> {
            Scene mainScene = new Scene(view.getRoot(), 300, 500);
            primaryStage.setTitle("Contacts List");
            primaryStage.setScene(mainScene);
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
