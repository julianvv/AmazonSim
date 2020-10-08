package com.nhlstenden.group7.AmazonSim.models;
import java.util.UUID;

public class Robot implements Object3D, Updatable {
    private UUID uuid;

    private String status= "idle";

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
        if(status.equals("idle")){
            return true;
        }

        if(status.equals("toDock")){

            this.x = 20;
            this.rotationY += (Math.PI * 2)/60;
        }
        if(x <15){
            this.x += 0.5;
        }else{
            this.rotationY += (Math.PI * 2)/60;
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
