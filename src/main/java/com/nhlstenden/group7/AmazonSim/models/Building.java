package com.nhlstenden.group7.AmazonSim.models;

import java.util.UUID;

public class Building implements Object3D, Updatable {
    private UUID uuid;

    private String status= "Being pretty";

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Building(){
        this.uuid = UUID.randomUUID();
    }

    public boolean update(){
        this.y = 0;
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Building.class.getSimpleName().toLowerCase();
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
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    @Override
    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    @Override
    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

    @Override
    public String setStatus(String status){
        return "Still being pretty";
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public void setLerp(double lerp) {

    }

    @Override
    public double getLerp() {
        return 0;
    }
}
