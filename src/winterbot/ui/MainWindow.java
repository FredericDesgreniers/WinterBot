/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import winterbot.WinterBot;
import winterbot.irc.events.IrcMessageEvent;
import winterbot.irc.events.IrcMessageListener;
import winterbot.irc.events.IrcStatusEvent;
import winterbot.irc.events.IrcStatusListener;

/**
 *
 * @author Fred
 */
public class MainWindow extends Application{
    WinterBot winter;
    public MainWindow()
    {
        winter = WinterBot.getWinter();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Winter Squirrel's twitch bot");
        
        MainPane pane = new MainPane(winter);
        stage.setScene(new Scene(pane,1000,600));
        stage.setOnCloseRequest(new EventHandler() {

            @Override
            public void handle(Event event) {
                System.exit(0);
            }
        });
        stage.show();
    }
    

    
}
