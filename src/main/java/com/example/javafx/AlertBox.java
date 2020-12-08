package com.example.javafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);

        Label l = new Label();
        l.setText(message);

        Button b = new Button("Close the window");
        b.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(l, b);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
