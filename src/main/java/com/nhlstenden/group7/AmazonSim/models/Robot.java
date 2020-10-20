package com.nhlstenden.group7.AmazonSim.models;
import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;

import java.util.List;
import java.util.UUID;

public class Robot implements Object3D, Updatable {
    private UUID uuid;
    private String status= "idle";
    private double lerp = 0;

    private double x = 0;
    private double y = 1;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Robot(){
        this.uuid = UUID.randomUUID();
    }

    public boolean update(){
        if(status.equals("idle") || status.equals("busy")){
            return true;
        }
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Robot.class.getSimpleName().toLowerCase();
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
        this.rotationX = x;
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

    @Override
    public void setLerp(double lerp) {
        this.lerp = lerp;
    }

    @Override
    public double getLerp() {
        return this.lerp;
    }
}
