/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.commands;

import winterbot.network.irc.IrcServer;

/**
 *
 * @author Fred
 */
public abstract class Command {
    private String name;
    public Command(String name)
    {
        this.name=name;
    }
    public abstract void handle(IrcServer server,String channel,String[] args);
}
