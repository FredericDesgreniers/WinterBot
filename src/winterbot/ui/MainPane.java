/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;

import javafx.scene.layout.BorderPane;

import winterbot.WinterBot;

/**
 *
 * @author Fred
 */
public class MainPane extends BorderPane{
    private WinterBot winter;
    private MenuPane menu;
    private ChatPane chat;
    public MainPane(WinterBot winter)
    {
        this.getStyleClass().add("mainPane");
        this.getStylesheets().add(this.getClass().getResource("style.css").toString());
        this.winter = winter;
        menu = new MenuPane(this);
        this.setLeft(menu);
    

chat = new ChatPane(this);

        
        this.setCenter(chat);
       
        
    }
    public WinterBot getWinter()
    {
        return winter;
    }
}
