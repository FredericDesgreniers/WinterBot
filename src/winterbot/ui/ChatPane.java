/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import winterbot.irc.events.IrcMessageEvent;
import winterbot.irc.events.IrcMessageListener;

/**
 *
 * @author Fred
 */
public class ChatPane extends ListView implements IrcMessageListener{
    private MainPane main;
    private boolean names=false;
        public ChatPane(MainPane main)
        {
            this.getStyleClass().add("chatPane");
            this.main=main;
            main.getWinter().getIrc().registerListener(this);

        }

    @Override
    public void handle(IrcMessageEvent event) {

        if(names)
        {
        Platform.runLater(new Thread(new Runnable() {

            @Override
            public void run() {
                getItems().add(new ChatLine(event.user,event.message));
                
               // if(getItems().size()>50)getItems().remove(0);
            }
        }));
        }
        
        if(event.message.contains("End of /NAMES list"))
        {
            names=true;
        }
    }
}
