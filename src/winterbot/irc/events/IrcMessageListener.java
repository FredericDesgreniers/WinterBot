/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.irc.events;

/**
 *
 * @author Fred
 */
public interface IrcMessageListener {
        public void handle(IrcMessageEvent event);
}
