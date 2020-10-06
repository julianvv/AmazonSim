package com.nhlstenden.group7.AmazonSim.views;

import com.nhlstenden.group7.AmazonSim.base.Command;
import com.nhlstenden.group7.AmazonSim.models.Object3D;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class DefaultWebSocketView implements View {
    private WebSocketSession session;
    private Command onClose;

    public DefaultWebSocketView(WebSocketSession session){
        this.session = session;
    }

    @Override
    public void update(String event, Object3D data){
        try{
            if(this.session.isOpen()){
                this.session.sendMessage(new TextMessage("{"
                + surroundString("command") + ": " + surroundString(event) + ","
                + surroundString("parameters") + ": " + jsonifyObject3D(data)
                +"}"));
            }
            else {
                this.onClose.execute();
            }
        }catch (IOException e){
            this.onClose.execute();
        }
    }

    @Override
    public void onViewClose(Command command){
        onClose = command;
    }

    private String jsonifyObject3D(Object3D object) {
        return "{"
                + surroundString("uuid") + ":" + surroundString(object.getUUID()) + ","
                + surroundString("type") + ":" + surroundString(object.getType()) + ","
                + surroundString("x") + ":" + object.getX() + ","
                + surroundString("y") + ":" + object.getY() + ","
                + surroundString("z") + ":" + object.getZ() + ","
                + surroundString("rotationX") + ":" + object.getRotationX() + ","
                + surroundString("rotationY") + ":" + object.getRotationY() + ","
                + surroundString("rotationZ") + ":" + object.getRotationZ()
                + "}";
    }


    private String surroundString(String s){
        return "\"" + s + "\"";
    }
}
