package com.nhlstenden.group7.AmazonSim.base;

import com.nhlstenden.group7.AmazonSim.controllers.Controller;
import com.nhlstenden.group7.AmazonSim.controllers.SimulationController;
import com.nhlstenden.group7.AmazonSim.controllers.StellageController;
import com.nhlstenden.group7.AmazonSim.models.Robot;
import com.nhlstenden.group7.AmazonSim.models.Object3D;
import com.nhlstenden.group7.AmazonSim.models.Stellage;
import com.nhlstenden.group7.AmazonSim.models.World;
import com.nhlstenden.group7.AmazonSim.views.DefaultWebSocketView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class App extends SpringBootServletInitializer implements WebSocketConfigurer {

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    private World world;
    private Controller controller;

    public static List<Object3D> queue = new ArrayList<>();
    public static List<Object3D> robotList = new ArrayList<>();

    public App() {
        this.world = new World();
        for(Object3D object: world.getWorldObjectsAsList()) {
            if(object.getType().equals("robot")){
                robotList.add(object);
            }
        }

        this.controller = new SimulationController(world);
        this.controller.start();
    }

    public static List<Object3D> getRobotList(){
        return robotList;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new DefaultWebSocketHandler(), "/connectToSimulation");
    }

    private class DefaultWebSocketHandler extends TextWebSocketHandler {

        @Override
        public void afterConnectionEstablished(WebSocketSession session) {
            controller.addView(new DefaultWebSocketView(session));
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) {
            var clientMessage = message.getPayload();
            System.out.println(clientMessage);
            JSONParser parser = new JSONParser();
            JSONObject obj;
            try{
                obj = (JSONObject) parser.parse(clientMessage);
                String uuid = (String) obj.get("uuid");
                StellageController stellageController = new StellageController(uuid, world);
            }catch (ParseException e){
                System.out.println(e);
            };

        }

        @Override
        public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
            session.close(CloseStatus.SERVER_ERROR);
        }
    }
}
