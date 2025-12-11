package com.github.imccalculator.app.components;

import com.github.imccalculator.app.models.Icons;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public void loadActionsOn(Stage stage, Scene scene, String css) {
        calculateBMIButton.setOnAction(e -> {
            var height = Double.parseDouble(fieldHeight.getText().replace(",", "."));
            var weight = Double.parseDouble(fieldWeight.getText().replace(",", "."));

            var bmi = weight / (height * height);

            System.out.println(height);
            System.out.println(weight);

            if (bmi < 18.5) {
                Result resultLow = new Result(bmi,
                        "Você está abaixo do peso",
                        Icons.WARNING,
                        "low",
                        stage,
                        scene);
                Scene lowScene = new Scene(resultLow, 1000, 630);
                stage.setScene(lowScene);

                lowScene.getStylesheets().add(css);
            }

            if (bmi >= 18.5 && bmi <= 25) {
                Result resultLow = new Result(bmi,
                        "Peso normal",
                        Icons.SUCCESS,
                        "normal",
                        stage,
                        scene);
                Scene lowScene = new Scene(resultLow, 1000, 630);
                stage.setScene(lowScene);

                lowScene.getStylesheets().add(css);
            }

            if (bmi >= 25 && bmi < 30) {
                Result resultLow = new Result(bmi,
                        "Sobrepeso",
                        Icons.WARNING,
                        "high",
                        stage,
                        scene);
                Scene lowScene = new Scene(resultLow, 1000, 630);
                stage.setScene(lowScene);

                lowScene.getStylesheets().add(css);
            }

            if (bmi >= 30) {
                Result resultLow = new Result(bmi,
                        "Obesidade",
                        Icons.ERROR,
                        "vary-high",
                        stage,
                        scene);
                Scene lowScene = new Scene(resultLow, 1000, 630);
                stage.setScene(lowScene);

                lowScene.getStylesheets().add(css);
            }
        });

        cancelCalculateBMIButton.setOnAction(e -> {
            fieldHeight.setText("");
            fieldWeight.setText("");
        });
    }
}
