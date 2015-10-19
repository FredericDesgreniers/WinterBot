/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.scene.control.Button;
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
        chatBtn = new Button("Chat");
        chatBtn.getStyleClass().add("menuButton");
        chatBtn.setMaxWidth(Double.MAX_VALUE);
       
        this.getChildren().addAll(dashBtn,chatBtn);
    }
}
