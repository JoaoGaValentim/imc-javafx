package com.github.imccalculator.app.models;

import java.net.URL;

import com.github.imccalculator.app.components.Result;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class aggroup all BMI logic
 * </p>
 * 
 * @author João Gabriel Valentim Theodoro
 * @version 1.0
 */
public class Bmi {
    private final double height;
    private final double weight;

    public Bmi(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    private double getBMI() {
        return weight / (height * height);
    }

    public void showBmiResultScreen(Stage stage, Scene scene, URL css) {
        double bmi = getBMI();
        if (bmi < 18.5) {
            Result resultLow = new Result(bmi,
                    "Você está abaixo do peso",
                    Icons.WARNING,
                    "low",
                    stage,
                    scene);
            Scene lowScene = new Scene(resultLow, 1000, 630);
            stage.setScene(lowScene);

            lowScene.getStylesheets().add(css.toExternalForm());
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

            lowScene.getStylesheets().add(css.toExternalForm());
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

            lowScene.getStylesheets().add(css.toExternalForm());
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

            lowScene.getStylesheets().add(css.toExternalForm());
        }
    }
}
