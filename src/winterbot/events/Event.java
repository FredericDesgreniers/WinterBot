/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.events;

/**
 *
 * @author Fred
 */
public abstract class Event {

    public final EventType type;
    public Event(EventType type)
    {
        this.type=type;
    }
    
}
