package com.nhlstenden.group7.AmazonSim.models;

import java.util.UUID;

public class Truck implements Object3D, Updatable {
    private UUID uuid;

    private String status = "idle";
    private double lerp = 0;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Truck(){
        this.uuid = UUID.randomUUID();
    }

    public boolean update(){

        this.x = 10;
        this.y = 1;
        this.z = 6;
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Truck.class.getSimpleName().toLowerCase();
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
    public String setStatus(String status) {
        this.status = status;
        return "done";
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public void setLerp(double lerp) {
        this.lerp = lerp;
    }

    @Override
    public double getLerp() {
        return this.lerp;
    }
}
