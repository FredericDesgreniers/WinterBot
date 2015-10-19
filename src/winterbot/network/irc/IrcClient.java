/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.network.irc;

import java.util.ArrayList;
import winterbot.WinterBot;
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
public class IrcClient {
    private ArrayList<IrcStatusListener> statusListeners;
    private ArrayList<IrcMessageListener> messageListeners;
    private ArrayList<IrcSubscriberListener> subscriberListeners;
    /**
     * WinterBot instance that created the client
     */
    private WinterBot winter;
    /**
     * list of servers the client should be connected to.
     */
    private ArrayList<IrcServer> servers;
    /**
     * Irc Client used to connect to servers
     * @param winter WinterBot instance
     */
    public IrcClient(WinterBot winter)
    {
        this.winter = winter;
        iniListeners();
        servers=new ArrayList();
    }
    private void iniListeners()
    {
        statusListeners = new ArrayList();
        messageListeners = new ArrayList();
        subscriberListeners = new ArrayList();
    }
    /**
     * connects to all irc servers in servers.
     */
    public void connect()
    {
        for(IrcServer server:servers)
        {
            server.connect();
            System.out.println("Trying to connect to "+server);
        }
    }
    /**
     * disconnects from all irc servers in servers
     */
    public void disconnect()
    {
        
    }
    public void addServer(IrcServer server)
    {
        servers.add(server);
    }
    
    public void registerListener(IrcStatusListener hnd)
    {
        statusListeners.add(hnd);
    }
    public void registerListener(IrcMessageListener hnd)
    {
        messageListeners.add(hnd);
    }
    public void registerListener(IrcSubscriberListener hnd)
    {
        subscriberListeners.add(hnd);
    }
    public void sendEvent(IrcStatusEvent ev)
    {
        for(IrcStatusListener hnd:statusListeners)
        {
            hnd.handle(ev);
        }
    }
    public void sendEvent(IrcMessageEvent ev)
    {
        for(IrcMessageListener hnd:messageListeners)
        {
            hnd.handle(ev);
        }
    }
    public void sendEvent(IrcSubscriberEvent ev)
    {
        for(IrcSubscriberListener hnd:subscriberListeners)
        {
            hnd.handle(ev);
        }
    }
    public IrcServer getServerByName(String name)
    {
        for(IrcServer server:servers)
        {
            if(server.getName().equalsIgnoreCase(name))
            {
                return server;
            }
        }
        return null;
    }
}
