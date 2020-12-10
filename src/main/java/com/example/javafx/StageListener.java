package com.example.javafx;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
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
    TreeView<String> treeView;
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

        button = new Button("Click me");

        TreeItem<String> root, bucky, megan;
// Root
        root = new TreeItem<>();
        root.setExpanded(true);

// Bucky
        bucky = makeBranch("Bucky", root);
        makeBranch("Hyacinth", bucky);
        makeBranch("YouTube", bucky);
        makeBranch("Chicken", bucky);

        megan = makeBranch("Megan", root);
        makeBranch("NetFlix", megan);
        makeBranch("Prime", megan);
        makeBranch("Amazon", megan);

        treeView = new TreeView<>(root);
        treeView.setShowRoot(false);
        treeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println(newValue.getValue());
            }
        });

        // layout
        StackPane layout = new StackPane();
        //layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(treeView);

        Scene scene = new Scene(layout, 300, 200);
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
