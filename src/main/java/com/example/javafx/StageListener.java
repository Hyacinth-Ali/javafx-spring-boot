package com.example.javafx;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<JavafxApplication.StageReadyEvent> {

    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    Stage window;
    Scene scene, scene2;

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
        Group root = new Group();
        Text text =  new Text(50, 100, "Here's a Text String");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
        text.setFill(Color.LIGHTSALMON);
        text.setStroke(Color.DARKBLUE);
        text.setStrokeWidth(2);
        text.setUnderline(true);

        root.getChildren().add(text);
        Scene scene = new Scene(root, 600, 600);
        window.setScene(scene);
        window.setTitle(this.applicationTitle);
        window.show();

//        try {
//            // original codes
//            Stage window = stageReadyEvent.getStage();
////            URL url = fxml.getURL();
////            FXMLLoader fxmlLoader = new FXMLLoader(url);
////            fxmlLoader.setControllerFactory(applicationContext::getBean);
////            Parent root = fxmlLoader.load();
//
//            Group root = new Group();
//            Text text =  new Text(50, 100, "Here's a Text String");
//            text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
//            text.setFill(Color.LIGHTSALMON);
//            text.setStroke(Color.DARKBLUE);
//            text.setStrokeWidth(2);
//            text.setUnderline(true);
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

    public ObservableList<Product> getProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();

        products.add(new Product("Laptop", 859, 20));
        products.add(new Product("Bouncy", 145, 78));
        products.add(new Product("Toilet", 435, 56));
        products.add(new Product("The Notebook DVD", 567, 456));
        products.add(new Product("Corn", 2345, 5));

        return products;
    }

    private void handleOption(CheckBox box1, CheckBox box2) {
        String message = "User oder: \n";
        if (box1.isSelected()) {
            message += "Bacon selected \n";
        }
        if (box2.isSelected()) {
            message += "Tuna selected";
        }

        System.out.println(message);
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Title", "Are sure you want to close the program?");
        if (answer) {
            window.close();
        }

    }
}
