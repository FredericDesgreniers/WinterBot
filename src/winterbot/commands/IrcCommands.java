/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.commands;

import java.util.HashMap;
import winterbot.WinterBot;
import winterbot.irc.events.IrcMessageEvent;
import winterbot.irc.events.IrcMessageListener;

/**
 *
 * @author Fred
 */
public class IrcCommands implements IrcMessageListener
{   
    private WinterBot winter;
    private HashMap<String,Command> commands;
    public IrcCommands(WinterBot winter)
    {
        this.winter=winter;
        commands = new HashMap();
        commands.put("!winter", new CommandWinter("winter"));
    }

    @Override
    public void handle(IrcMessageEvent event) {
        String[] commandArgs = event.message.trim().split(" ");
        String command = commandArgs[0].toLowerCase();
        if(commands.containsKey(command))
        {
            commands.get(command).handle(event.server, event.channel, commandArgs);
        }
        

    }
    
}
