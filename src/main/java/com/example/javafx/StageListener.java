package com.example.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
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

        Person bucky = new Person();
        bucky.firstNameProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Name changed to new value: " + newValue );
            System.out.println("firstName :" +bucky.firstNameProperty());
            System.out.println("getFirstName: " + bucky.getFirstName());
        });

        Button button = new Button("Submit");
        button.setOnAction(e -> bucky.setFirstName("Pork"));

        StackPane layout = new StackPane();
       layout.getChildren().add(button);

        Scene scene = new Scene(layout, 700, 500);
        scene.getStylesheets().add("viper.css");
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
