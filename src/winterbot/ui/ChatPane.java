/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import winterbot.irc.events.IrcMessageEvent;
import winterbot.irc.events.IrcMessageListener;

/**
 *
 * @author Fred
 */
public class ChatPane extends ScrollPane implements IrcMessageListener{
    private MainPane main;
    private boolean scrollBot=false;
    private boolean names=false;
    private VBox chat;
        public ChatPane(MainPane main, VBox chat)
        {
            super(chat);
            this.chat=chat;
            chat.setPrefWidth(Double.MAX_VALUE);
            this.getStyleClass().add("chatScroll");
            chat.getStyleClass().add("chatPane");
            this.main=main;
            main.getWinter().getIrc().registerListener(this);
            chat = new VBox();
            this.getChildren().add(chat);
            setFitToWidth(true);
            new Thread(new updater()).start();
 
        }
        
    @Override
    public void handle(IrcMessageEvent event) {

        
        if(names)
        {
           
        Platform.runLater(new Thread(new Runnable() {

            @Override
            public void run() {
               chat.getChildren().add(new ChatLine(event.user,event.message));
               
               while(chat.getChildren().size()>50)chat.getChildren().remove(0);
               scrollBot=true;
               
            }
        }));
        }
        
        if(event.message.contains("End of /NAMES list"))
        {
            names=true;
        }
    }
    
    private class updater implements Runnable
    {

        @Override
        public void run() {
            while(true)
            {
                if(scrollBot)
                {
                    
                    Platform.runLater(new Thread(new Runnable() {
                        

                        @Override
                        public void run() {
                            setVvalue(getVmax());
                            scrollBot=false;
                        }
                    }));
                   
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChatPane.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } 
    }
}
