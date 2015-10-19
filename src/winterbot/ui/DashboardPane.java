package winterbot.ui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import winterbot.events.EventType;
import winterbot.irc.events.IrcSubscriberEvent;
import winterbot.irc.events.IrcSubscriberListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class DashboardPane extends VBox implements IrcSubscriberListener {
    MainPane main;
    String lastSub;
    Label lastSubLabel;
    public DashboardPane(MainPane main)
    {
        
        this.getStyleClass().add("dashboard");
        this.main=main;
        main.getWinter().getIrc().registerListener(this);
        lastSub="";
        lastSubLabel = new Label("No subscriber this session");
        lastSubLabel.getStyleClass().add("subscriberLabel");
        this.getChildren().add(lastSubLabel);
        
    }
    @Override
    public void handle(IrcSubscriberEvent event) {
        if(event.type==EventType.IRC_NEWSUB)
        {
            lastSub = event.subscriberName;
            updateLastSub();
        }
    }
    private void updateLastSub()
    {
                Platform.runLater(new Thread(new Runnable() {

            @Override
            public void run() {
                

            }
        }));
        lastSubLabel.setText("Lastest subscriber: "+lastSub);
    }
}
