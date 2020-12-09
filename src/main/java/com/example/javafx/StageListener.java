package com.example.javafx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StageListener implements ApplicationListener<JavafxApplication.StageReadyEvent> {

    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    Stage window;
    Scene scene, scene2;

    Button button;
    //private Label;

    public StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
                         @Value("classpath:/ui.fxml") Resource fxml, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.fxml = fxml;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(JavafxApplication.StageReadyEvent stageReadyEvent) {

        window = stageReadyEvent.getStage();
        window.setTitle("Hyacinth - JavaFX!");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // name label
        Label l = new Label("Username");
        GridPane.setConstraints(l, 0, 0);

        // name input
        TextField input = new TextField("Bucky");
        GridPane.setConstraints(input, 1, 0);

        // Password Label
        Label pw = new Label("Password");
        GridPane.setConstraints(pw, 0, 1);

        // Password input
        TextField pwInput = new TextField();
        pwInput.setPromptText("Password");
        GridPane.setConstraints(pwInput, 1, 1);

        Button login = new Button("Log In");
        GridPane.setConstraints(login, 1, 2);

        grid.getChildren().addAll(l, input, pw, pwInput, login);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();


        window.show();

//        try {
//            // original codes
//            Stage window = stageReadyEvent.getStage();
//            URL url = fxml.getURL();
//            FXMLLoader fxmlLoader = new FXMLLoader(url);
//            fxmlLoader.setControllerFactory(applicationContext::getBean);
//            Parent root = fxmlLoader.load();
//            Scene scene = new Scene(root, 600, 600);
//            window.setScene(scene);
//            window.setTitle(this.applicationTitle);
//            window.show();
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Title", "Are sure you want to close the program?");
        if (answer) {
            window.close();
        }

    }
}
