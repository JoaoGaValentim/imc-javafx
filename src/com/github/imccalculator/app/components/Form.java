package com.github.imccalculator.app.components;

import java.net.URL;

import com.github.imccalculator.app.models.Bmi;
import com.github.imccalculator.app.models.Icons;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This component create a form to calculate BMI
 * </p>
 * 
 * @author João Gabriel Valentim Theodoro
 * @version 1.0
 */
public class Form extends VBox {
    private final Label formTitle;
    private final Label formInstructions;
    private final TextField fieldHeight;
    private final TextField fieldWeight;
    private final Button calculateBMIButton;
    private final Button cancelCalculateBMIButton;
    private final HBox buttons;

    public Form() {
        formTitle = new Label("Calcule seu IMC");
        formInstructions = new Label(
                """
                        IMC é a sigla para Índice de Massa Corpórea, parâmetro adotado pela OMS para calcular
                        o peso ideal de cada pessoa.
                        """);

        fieldHeight = new TextField();
        fieldWeight = new TextField();
        calculateBMIButton = new Button("Calcular IMC");
        cancelCalculateBMIButton = new Button("Cancelar");
        buttons = new HBox();

        formTitle.getStyleClass().add("form-title");
        formInstructions.getStyleClass().add("form-instructions");
        calculateBMIButton.getStyleClass().add("button");
        cancelCalculateBMIButton.getStyleClass().add("button");
        cancelCalculateBMIButton.getStyleClass().add("error");
        fieldHeight.getStyleClass().add("field");
        fieldWeight.getStyleClass().add("field");
        fieldHeight.setFocusTraversable(false);
        fieldWeight.setFocusTraversable(false);
        fieldHeight.setPromptText("Informe sua altura em Métros (ex.: 1,80)");
        fieldWeight.setPromptText("Informe seu peso em Kg (ex.: 78,2)");

        buttons.setSpacing(20);
        buttons.getChildren().addAll(calculateBMIButton, cancelCalculateBMIButton);

        getChildren().addAll(formTitle, formInstructions, fieldHeight, fieldWeight, buttons);
        setAlignment(Pos.CENTER_LEFT);
        setPrefWidth(800);
        setSpacing(20);
    }

    public void initializeActionHandlers(Stage stage, Scene scene, URL css) {
        calculateBMIButton.setOnAction(e -> {
            if (fieldHeight.getText().isEmpty() || fieldWeight.getText().isEmpty()) {
                Result validationResult = new Result(
                        "Favor preencher todos os campos!",
                        Icons.WARNING,
                        "low",
                        stage,
                        scene);

                Scene validationScene = new Scene(validationResult, 1000, 630);
                validationResult.getStylesheets().add(css.toExternalForm());
                stage.setScene(validationScene);
                return;
            }

            var height = Double.parseDouble(fieldHeight.getText().replace(",", "."));
            var weight = Double.parseDouble(fieldWeight.getText().replace(",", "."));
            Bmi bmi = new Bmi(height, weight);
            bmi.showBmiResult(stage, scene, css);
        });

        cancelCalculateBMIButton.setOnAction(e -> {
            fieldHeight.setText("");
            fieldWeight.setText("");
        });
    }
}
