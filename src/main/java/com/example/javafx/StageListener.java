package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;

@Component
public class StageListener implements ApplicationListener<JavafxApplication.StageReadyEvent> {

    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    Stage window;
    Scene scene1, scene2;

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
        window.setTitle("Welcome to JavaFX World!");

        Label l1 = new Label("Welcome to the first scene.");
        Button b1 = new Button("Close the program");
        //b1.setOnAction(e -> window.setScene(scene2));
        // show alert window
        //b1.setOnAction(e -> AlertBox.display("Alert WIndow", "This is an alert window"));

        // show confirm box
        b1.setOnAction(e -> {
            boolean result = ConfirmBox.display("Confirm your answer", "Are you sure you want to close?");
            System.out.println(result);
        });

        // save some information before closing program
        b1.setOnAction(e -> closeProgram());
        window.setOnCloseRequest(e -> {
            // consume the event, otherwise, the program closes even if you select No
            e.consume();
            closeProgram();
        });
        // layout 1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(l1, b1);
        scene1 = new Scene(layout1, 200, 200);

        // Button 2
        Button b2 = new Button("Go back to scene 1");
        b2.setOnAction(e -> window.setScene(scene1));

        // layout 1
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(b2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        //window.setTitle("Windo title");
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
