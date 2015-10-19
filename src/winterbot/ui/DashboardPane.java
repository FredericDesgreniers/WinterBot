package winterbot.ui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
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
public class DashboardPane extends GridPane implements IrcSubscriberListener {
    MainPane main;
    String lastSub;
    VBox lastSubsList;
    public DashboardPane(MainPane main)
    {
        
        this.getStyleClass().add("dashboard");
        this.main=main;
        main.getWinter().getIrc().registerListener(this);
        lastSub="";
        lastSubsList=new VBox();

        this.add(lastSubsList,0,0);
        
    }
    @Override
    public void handle(IrcSubscriberEvent event) {
        
        System.out.println("NEW SUB "+event.subscriberName+" for "+event.monthsSubbed);

        switch(event.type)
        {
            case IRC_NEWSUB:
                lastSub = event.subscriberName;
                break;
            case IRC_RESUB:
                lastSub = event.subscriberName+"("+event.monthsSubbed+")";
                break;
                 
        }

        updateSub(lastSub);

        
    }
    private void updateSub(String s)
    {
                Platform.runLater(new Thread(new Runnable() {

            @Override
            public void run() {
                
                    lastSubsList.getChildren().add(0,new Label(s));
                    while(lastSubsList.getChildren().size()>10)lastSubsList.getChildren().remove(10);
            }
        }));
        
    }
}
