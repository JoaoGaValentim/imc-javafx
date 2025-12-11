package com.github.imccalculator.app.components;

import com.github.imccalculator.app.models.Icons;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class CardContainer extends GridPane {
    public CardContainer() {
        Card low = new Card(Icons.WARNING, "Abaixo do peso", "IMC de 0 a 18.4");
        low.getStyleClass().add("low");

        Card normal = new Card(Icons.SUCCESS, "Normal", "IMC de 18.5 a 25");
        normal.getStyleClass().add("normal");

        Card high = new Card(Icons.WARNING, "Sobrepeso", "IMC de 25 a 30");
        high.getStyleClass().add("high");

        Card varyHigh = new Card(Icons.ERROR, "Obesidade", "IMC de 30 e superior");
        varyHigh.getStyleClass().add("vary-high");

        add(low, 0, 0);
        add(normal, 1, 0);
        add(high, 0, 1);
        add(varyHigh, 1, 1);

        setAlignment(Pos.CENTER_RIGHT);
        setHgap(20);
        setVgap(20);
        setHgap(20);
        getStyleClass().add("card-box");
    }
}
