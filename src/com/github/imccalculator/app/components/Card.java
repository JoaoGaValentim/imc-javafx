package com.github.imccalculator.app.components;

import com.github.imccalculator.app.models.Icons;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Card extends VBox {
    public Card(String title, String text) {
        Label titleLabel = new Label(title);
        Label textLabel = new Label(text);

        titleLabel.getStyleClass().add("card-title");
        textLabel.getStyleClass().add("card-text");

        getStyleClass().add("card");
        getChildren().addAll(titleLabel, textLabel);
        setAlignment(Pos.CENTER);
        setSpacing(20);
    }

    public Card(Icons imgUrl, String title, String text) {
        Label titleLabel = new Label(title);
        Label textLabel = new Label(text);
        Image image = new Image(imgUrl.getIconUrl());
        ImageView view = new ImageView(image);
        view.setPreserveRatio(true);
        view.setFitHeight(70);
        view.setFitWidth(70);

        titleLabel.getStyleClass().add("card-title");
        textLabel.getStyleClass().add("card-text");

        getStyleClass().add("card");
        getChildren().addAll(view, titleLabel, textLabel);
        setAlignment(Pos.CENTER);
        setSpacing(20);
    }
}
