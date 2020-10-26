package com.nhlstenden.group7.AmazonSim.models;

import java.util.UUID;

public class Stellage implements Object3D, Updatable{
    private UUID uuid;
    private String status = "idle";
    private ProxyObject3D closestRobot;

    private double x = 5;
    private double y = 1;
    private double z = -5;

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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

    public String setStatus(String status) {
        this.status = status;
        return "done";
    }

    public String getStatus(){
        return this.status;
    }
}
