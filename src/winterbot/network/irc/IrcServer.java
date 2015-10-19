/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.network.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import winterbot.events.Event;
import winterbot.events.EventType;
import winterbot.irc.events.IrcMessageEvent;
import winterbot.irc.events.IrcStatusEvent;
import winterbot.irc.events.IrcSubscriberEvent;
import winterbot.test.Debug;

/**
 *
 * @author Fred
 */
public class IrcServer implements Runnable{

    private boolean connected;
    
    private int port;
    private String ip;
    
    private IrcClient client;
    
    private BufferedWriter writer;
    private BufferedReader reader;
    
    private Socket socket;
    
    private IrcUser user;
    private String name;
    public IrcServer(String name,IrcClient client, IrcUser user, String ip, int port)
    {
        this.name=name;
        this.port=port;
        this.ip=ip;
        this.user=user;
        this.client=client;
        connected=false;
    }
    
    @Override
    public void run() {
        if(socket.isConnected())
        {

                //SEND CONNECTION INFO
                try {
                    writer.write("PASS "+user.getPassword()+"\r\n");
                    writer.write("NICK "+user.getNickName()+"\r\n");
                    writer.write("CAP REQ :twitch.tv/membership\r\n");
                    writer.flush();                                      
                } catch (IOException ex) {
                    System.out.println("Could not writer to server"+this);
                    return;
                }
                //WAIT TO CONNECT
                try{
                String line = null;
                
                while((line=reader.readLine())!=null)
                {
                    System.out.println(line);
                    if(line.indexOf("004")>=0)
                        break;
                    else if(line.indexOf("433")>=0)
                    {
                        System.out.println("nickname already in use "+this);
                        return;
                    }
                }
                }catch(Exception e)
                {
                    System.out.println("Could not read from "+this);
                }
                connected=true;
                client.sendEvent(new IrcStatusEvent(EventType.IRC_STATE_CONNECT,this));
                //CONNECTED, DO STUFF

            while(connected)
            {   
                try{
                    String line = null;
                    while((line=reader.readLine())!=null)
                    {
                        if(line.toLowerCase().startsWith("PING"))
                        {
                            writer.write("PONG "+line.substring(5)+"\r\n");
                        }else
                        {
                            handleMessage(line);
                        }
                    }
                }catch(Exception e)
                {
                    
                }
            }
        }else
            System.out.println("Thread stopped, socket "+this+" not connected!");
        System.out.println("Thread stopped, socket "+this+" not connected!");
    }
    public void handleMessage(String m)
    {
       
        String m1[] = m.split(":");
        //System.out.println(m);
        if(m1[1].contains("tmi.twitch.tv"))
        {
            String[] info = m1[1].split(" ");
            String channel = info[2];
            String message = m1[2];
            String user=info[0].split("!")[0];
            if(user.equalsIgnoreCase("twitchnotify"))
            {
                String name = message.split(" ")[0];
                if(message.contains("months"))
                {
                    System.out.println("RESUB");
                    IrcSubscriberEvent resubEvent = new IrcSubscriberEvent(EventType.IRC_RESUB,channel,name,Integer.valueOf(message.split(" ")[3]));
                    client.sendEvent(resubEvent);
                }else
                {
                    IrcSubscriberEvent subEvent = new IrcSubscriberEvent(EventType.IRC_NEWSUB,channel,name);
                    client.sendEvent(subEvent);
                }
                
                
            }else
            {
                IrcMessageEvent messageEvent =new IrcMessageEvent(EventType.IRC_CHAT,this,channel,user,message);
                client.sendEvent(messageEvent);
                
            }
        }else
        {
             System.out.println(m);
        }
        
    }
    public boolean joinChannel(String name) 
    {
        if(!connected)return false;
        try{
           
                      
        writer.write("JOIN "+name+"\r\n");
         
        writer.flush();
        }catch(Exception e)
        {
            return false;
        }
        return true;
    }
    /**
     * connect to server and starts listening thread
     */
    public void connect() 
    {
        try{
            socket = new Socket(ip,port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e)
        {
            System.out.println("Could not connect to server "+this);
            return;
        }
       
        
        new Thread(this).start();
    }
    public synchronized void sendMessage(String channel,String message)
    {
        if(Debug.PREVENT_INTERACTION)return;
        try {
            writer.write("PRIVMSG "+channel+" :"+message+"\r\n");
            writer.flush();

        } catch (IOException ex) {

        }
    }
    public String toString()
    {
        return "["+ip+":"+port+"]";
    }
    public boolean isConnected()
    {
        return this.connected;
    }
    public String getName()
    {
        
        return this.name;
    }
}
