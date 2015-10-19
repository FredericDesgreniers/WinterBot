/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.irc.events;

import winterbot.events.Event;
import winterbot.events.EventType;

/**
 *
 * @author Fred
 */
public class IrcMessageEvent extends IrcEvent{


    public final String user;
    public final String message;
    public IrcMessageEvent(EventType type,String channel,String user,String message) {
        super(type,channel);
        this.user=user;
        this.message=message;
    }
    
}
