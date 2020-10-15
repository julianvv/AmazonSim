package com.nhlstenden.group7.AmazonSim.models;

import com.nhlstenden.group7.AmazonSim.AStar.ClosestRobotPath;
import com.nhlstenden.group7.AmazonSim.base.App;
import com.nhlstenden.group7.AmazonSim.controllers.StellageController;

import java.util.UUID;

public class Stellage implements Object3D, Updatable{
    private UUID uuid;
    private String status = "idle";
    private ProxyObject3D closestRobot;
    private boolean robot;
    private int distance;

    private double x = 0;
    private double y = 1;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Stellage(){
        this.uuid = UUID.randomUUID();
    }

    public boolean update(){
        if (status.equals("idle")){
            return true;
        }
        if(status.equals("toDock")){
            for(int i = 0; i < App.getRobotList().size(); i++){

                System.out.println(ClosestRobotPath.SearchNodeTest2D(this, App.getRobotList().get(i)));
            }

            this.x = 20;
            this.status = "idle";
        }
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Stellage.class.getSimpleName().toLowerCase();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }

    @Override
    public String setStatus(String status) {
        this.status = status;
        return "done";
    }

    public String getStatus(){
        return this.status;
    }
}
