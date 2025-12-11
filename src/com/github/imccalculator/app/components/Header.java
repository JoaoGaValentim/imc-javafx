package com.github.imccalculator.app.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This component is a application/header
 * </p>
 * 
 * @author João Gabriel Valentim Theodoro
 * @version 1.0
 */
public class Header extends HBox {
    public Header() {
        Label titleIcon = new Label("IMC");
        Label title = new Label("powered by JavaFX");

        titleIcon.getStyleClass().add("icon");
        title.getStyleClass().add("title");

        getChildren().addAll(titleIcon, title);
        setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);
    }

    public Header(String caption) {
        Label titleIcon = new Label(caption);
        Label title = new Label("powered by JavaFX");

        titleIcon.getStyleClass().add("icon");
        title.getStyleClass().add("title");

        getChildren().addAll(titleIcon, title);
        setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);
    }
}
