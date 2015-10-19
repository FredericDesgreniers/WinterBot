/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Fred
 */
public class MenuPane extends VBox{
    private MainPane main;
    private Button dashBtn,chatBtn;
    public MenuPane(MainPane main)
    {
        this.getStyleClass().add("menuPane");
        this.main = main;
        dashBtn = new Button("Dashboard");
        dashBtn.getStyleClass().add("menuButton");
        dashBtn.setMaxWidth(Double.MAX_VALUE);
        dashBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //if(event.isPrimaryButtonDown())
                main.displayDashboard();
                resetSelection();
                dashBtn.getStyleClass().add("selectedMenuBtn");
            }
        });
        chatBtn = new Button("Chat");
        chatBtn.getStyleClass().add("menuButton");
        chatBtn.setMaxWidth(Double.MAX_VALUE);
        chatBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               // if(event.isPrimaryButtonDown())
                main.displayChat();
                resetSelection();
                chatBtn.getStyleClass().add("selectedMenuBtn");
            }
        });
       
        this.getChildren().addAll(dashBtn,chatBtn);
    }
    public void resetSelection()
    {
        dashBtn.getStyleClass().remove("selectedMenuBtn");
        chatBtn.getStyleClass().remove("selectedMenuBtn");
    }
}
