package com.nhlstenden.group7.AmazonSim.controllers;

import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Astar;
import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;
import com.nhlstenden.group7.AmazonSim.models.Object3D;
import com.nhlstenden.group7.AmazonSim.models.Robot;
import com.nhlstenden.group7.AmazonSim.models.Stellage;

import java.beans.IntrospectionException;
import java.util.List;

public class RobotController {
    private Object3D Robot;
    private Object3D Stellage;
    private Node startNode;
    private Node endNode;
    private Node dockNode;
    private Thread thread = null;

    public RobotController(Object3D robot, Object3D stellage){
        //TODO: Int casts veranderen (Math.abs?)
        this.Robot = robot;
        this.Stellage = stellage;
        thread = new Thread() {
            public void run(){
                System.out.println("Thread initialized...");
                startNode = new Node((int) robot.getX() + 89, (int) robot.getZ() + 10);
                endNode = new Node((int) stellage.getX() + 89, (int) stellage.getZ() + 10);
                dockNode = new Node(-89 + 89, -10 +10);
                Astar astar = new Astar(174, 140, startNode, endNode);
                List<Node> path = astar.findPath();
                robot.setStatus("busy");
                for (Node node : path){
                    robot.setX(node.getRow() -89);
                    robot.setZ(node.getCol() - 10);
                    try {
                        thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("yeet");

                robot.setStatus("attached");
                stellage.setStatus("attached");
                Astar astar1 = new Astar(174, 140, endNode, dockNode);
                List<Node> pathToDock = astar1.findPath();
                for(Node node : pathToDock){
                    robot.setX(node.getRow() -89);
                    robot.setZ(node.getCol() -10);
                    stellage.setX(node.getRow() -89);
                    stellage.setZ(node.getCol() -10);
                    try{
                        thread.sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
        }};
        thread.start();
    }
}
