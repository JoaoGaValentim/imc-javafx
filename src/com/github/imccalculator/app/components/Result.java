package com.github.imccalculator.app.components;

import com.github.imccalculator.app.models.Icons;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This component show result
 * </p>
 * 
 * @author João Gabriel Valentim Theodoro
 * @version 1.0
 */
public class Result extends VBox {
    public Result(String alertText, Icons icon, String classCss, Stage stage, Scene main) {
        getStyleClass().add(classCss);

        Image img = new Image(icon.getIconUrl());
        ImageView view = new ImageView(img);
        view.setFitHeight(70);
        view.setFitWidth(70);

        Label resultTextLabel = new Label("Atenção");
        Label alertTextLabel = new Label(alertText);

        Button back = new Button("Calcular novamente");

        back.setOnAction(e -> {
            stage.setScene(main);
        });

        resultTextLabel.getStyleClass().add("card-title");
        alertTextLabel.getStyleClass().add("card-text");
        getChildren().addAll(view, resultTextLabel, alertTextLabel, back);
        setSpacing(20);
        setAlignment(Pos.CENTER);
    }

    public Result(double bmi, String alertText, Icons icon, String classCss, Stage stage, Scene main) {
        getStyleClass().add(classCss);

        Image img = new Image(icon.getIconUrl());
        ImageView view = new ImageView(img);
        view.setFitHeight(70);
        view.setFitWidth(70);

        Label resultTextLabel = new Label(String.format("Seu IMC é %.2f", bmi));
        Label alertTextLabel = new Label(alertText);

        Button back = new Button("Calcular novamente");

        back.setOnAction(e -> {
            stage.setScene(main);
        });

        resultTextLabel.getStyleClass().add("card-title");
        alertTextLabel.getStyleClass().add("card-text");
        getChildren().addAll(view, resultTextLabel, alertTextLabel, back);
        setSpacing(20);
        setAlignment(Pos.CENTER);
    }
}
