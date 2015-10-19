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
public class CommandWinter extends Command{

    public CommandWinter(String name) {
        super(name);
    }

    @Override
    public void handle(IrcServer server,String channel,String[] args){
        if(args.length>1)
        {
            if(args[1].equalsIgnoreCase("info"))
            {
                server.sendMessage(channel, "Here is my info: Github:https://github.com/WinterSquirrel/WinterBot/ Twitter:https://twitter.com/WinterSquirrel_  ");
            }
        }else
        {
            server.sendMessage(channel, "Hi, I'm a bot created by Winter_Squirrel. For more info, type !info");
        }
    }
    
}
