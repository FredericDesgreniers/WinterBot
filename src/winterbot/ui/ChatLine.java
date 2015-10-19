/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winterbot.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Fred
 */
public class ChatLine extends HBox{
    Label user,message;
    public ChatLine(String user,String message)
    {
        this.getStyleClass().add("chatLine");
        this.user=new Label(user+":  ");
        this.user.setWrapText(true);
        this.user.setPrefWidth(200);
        this.user.setAlignment(Pos.CENTER);
        this.user.getStyleClass().add("chatUser");
        this.message=new Label(message);
        this.message.setMaxWidth(550);
        this.message.setWrapText(true);
        this.message.getStyleClass().add("chatMessage");
        getChildren().addAll(this.user,this.message);

        
        
    }
}
