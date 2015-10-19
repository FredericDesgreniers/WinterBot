/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot;


import javafx.application.Application;
import winterbot.irc.events.IrcMessageListener;
import winterbot.irc.events.IrcStatusListener;
import winterbot.irc.events.IrcSubscriberListener;
import winterbot.network.irc.IrcClient;

import winterbot.network.irc.IrcServer;
import winterbot.network.irc.IrcUser;
import winterbot.test.Debug;
import winterbot.ui.MainWindow;

/**
 *
 * @author Fred
 */
public class WinterBot{
    /**
     * Javafx Application used for ui. Should we switch to in-broser html5???
     */
    private  static  WinterBot instance;
    public static WinterBot getWinter()
    {
        return instance;
    }
    private IrcClient irc;
    private Debug debug;
    public WinterBot() throws Exception
    { 
        instance=this;
        irc=new IrcClient(this);
        irc.addServer(new IrcServer("twitch",irc,new IrcUser("winter_squirrel",SENSITIVE.TWITCH_AUTH),"irc.twitch.tv",6667));
        new Thread(new Runnable(){

            @Override
            public void run() {
                Application.launch(MainWindow.class);
            }
            
        }).start();
        

         debug = new Debug();
        irc.registerListener((IrcStatusListener) debug);
        irc.registerListener((IrcMessageListener) debug);
        irc.registerListener((IrcSubscriberListener)debug);
        
        irc.connect();
    }
    public IrcClient getIrc()
    {
        return irc;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new WinterBot();
         
    }


    
}
