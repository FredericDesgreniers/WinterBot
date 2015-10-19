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
public abstract class IrcEvent extends Event{
    
    public final String channel;
    public IrcEvent(EventType type,String channel)
    {
        super(type);
        this.channel=channel;
    }
}
