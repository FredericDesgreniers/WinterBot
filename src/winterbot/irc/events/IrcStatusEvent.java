/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.irc.events;

import winterbot.events.Event;
import winterbot.events.EventType;
import winterbot.network.irc.IrcServer;

/**
 *
 * @author Fred
 */
public class IrcStatusEvent extends Event{

    public final IrcServer server;
    public IrcStatusEvent(EventType type,IrcServer server)
    {
        super(type);

        this.server=server;
    }
}
