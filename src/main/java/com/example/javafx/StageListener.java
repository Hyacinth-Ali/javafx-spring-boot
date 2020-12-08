package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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

        Stage stage = stageReadyEvent.getStage();
        stage.setTitle("Welcome to JavaFX World!");

        button = new Button("Click me");
        button.setOnAction(e -> System.out.println("Yes, being clicked with lamba technique"));
        StackPane pane = new StackPane();
        pane.getChildren().add(button);

        Scene scene = new Scene ( pane, 300, 250);
        stage.setScene(scene);
        stage.show();

//        try {
//            // original codes
//            Stage stage = stageReadyEvent.getStage();
//            URL url = fxml.getURL();
//            FXMLLoader fxmlLoader = new FXMLLoader(url);
//            fxmlLoader.setControllerFactory(applicationContext::getBean);
//            Parent root = fxmlLoader.load();
//            Scene scene = new Scene(root, 600, 600);
//            stage.setScene(scene);
//            stage.setTitle(this.applicationTitle);
//            stage.show();
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
