package com.github.imccalculator.app;

import java.net.URL;

import com.github.imccalculator.app.components.CardContainer;
import com.github.imccalculator.app.components.Form;
import com.github.imccalculator.app.components.Header;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This project aims to teach Java and Object-Oriented Programming in Java.
 * </p>
 * 
 * @author João Gabriel Valentim Theodoro
 * @version 1.0
 */
public class App extends Application {
    @Override
    public void start(Stage primarStage) throws Exception {
        primarStage.setTitle("Calculadora IMC");

        BorderPane container = new BorderPane();
        Header header = new Header();
        CardContainer cards = new CardContainer();

        HBox app = new HBox();
        Form form = new Form();

        app.getChildren().addAll(form, cards);
        app.setSpacing(20);

        container.getStyleClass().add("container");
        container.setTop(header);
        container.setCenter(app);

        Scene scene = new Scene(container, 1000, 630);

        final URL CSS_URL = getClass().getResource("resources/style.css");

        if (CSS_URL == null) {
            System.err.println("Fatal Error: style.css not found in resources.");
            throw new RuntimeException("Stylesheet not found.");
        }

        form.initializeActionHandlers(primarStage, scene, CSS_URL);

        scene.getStylesheets().add(CSS_URL.toExternalForm());
        primarStage.setScene(scene);
        primarStage.setResizable(false);
        primarStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
