package com.example.javafx;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class SimpleUiController {

    private final HostServices hostServices;

    @FXML
    public Label label;

    @FXML
    public Button button;


    public SimpleUiController(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    public void initialize () {
        //this.button.setOnAction(actionEvent -> this.label.setText(this.hostServices.getDocumentBase()));
        System.out.println("Loading user data ..");
    }

    public void handleButtonClick() {
        System.out.println("Button clicked");
        button.setText("Stop!");
    }
}
