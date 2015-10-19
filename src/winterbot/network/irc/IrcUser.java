/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.network.irc;

/**
 *
 * @author Fred
 */
public class IrcUser {
    private String nickname,password;
    public IrcUser(String nickname,String password)
    {
        this.nickname=nickname;
        this.password=password;
    }
    public String getNickName()
    {
        return this.nickname;
    }
    public String getPassword()
    {
        return this.password;
    }
}
