/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import winterbot.WinterBot;

/**
 *
 * @author Fred
 */
public class MainPane extends BorderPane{
    private WinterBot winter;
    private MenuPane menu;
    private ChatPane chat;
    private DashboardPane dashboard;
    public MainPane(WinterBot winter)
    {
        this.getStyleClass().add("mainPane"); 
        if(ClassLoader.getSystemResource("/winterbot/ui/style.css") == null)System.out.println("dssssadas");
        this.getStylesheets().add(this.getClass().getResource("/winterbot/ui/style.css").toString());
        this.winter = winter;
        menu = new MenuPane(this);
        this.setLeft(menu);
    

        chat = new ChatPane(this,new VBox());
        dashboard = new DashboardPane(this);
        
        this.setCenter(dashboard);
        menu.getDashboard().getStyleClass().add("selectedMenuBtn");
       
        
    }
    public WinterBot getWinter()
    {
        return winter;
    }
    public void displayChat()
    {
        Platform.runLater(new Thread(new Runnable() {

            @Override
            public void run() {
                setCenter(chat);

               // if(getItems().size()>50)getItems().remove(0);
            }
        }));
        
    }
    public void displayDashboard()
    {
        Platform.runLater(new Thread(new Runnable() {

            @Override
            public void run() {
                setCenter(dashboard);

            }
        }));
        
    }
    
}
