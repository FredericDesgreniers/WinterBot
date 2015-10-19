/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.test;

import winterbot.events.EventType;
import winterbot.irc.events.IrcFullListener;
import winterbot.irc.events.IrcMessageEvent;
import winterbot.irc.events.IrcMessageListener;
import winterbot.irc.events.IrcStatusEvent;
import winterbot.irc.events.IrcStatusListener;
import winterbot.irc.events.IrcSubscriberEvent;
import winterbot.irc.events.IrcSubscriberListener;

/**
 *
 * @author Fred
 */
public class Debug implements IrcFullListener{

    @Override
    public void handle(IrcMessageEvent event) {

    }

    @Override
    public void handle(IrcStatusEvent event) {
        if(event.type == EventType.IRC_STATE_CONNECT)
        event.server.joinChannel("#iwilldominate ");
    }

    @Override
    public void handle(IrcSubscriberEvent event) {
        if(event.type == EventType.IRC_STATE_CONNECT)
        System.out.println(event.subscriberName+" has just subscribed to "+event.channel+"!");
    }
    
}
