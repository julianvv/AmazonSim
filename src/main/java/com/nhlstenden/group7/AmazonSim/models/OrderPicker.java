package com.nhlstenden.group7.AmazonSim.models;

import java.util.UUID;

public class OrderPicker implements Object3D, Updatable {
    private UUID uuid;
    private String status;

    private double x = -95;
    private double y = 0.85;
    private double z = 80;

    private double rotationX = 0;
    private double rotationY = 1.55;
    private double rotationZ = 0;

    public OrderPicker(){
        this.uuid = UUID.randomUUID();
        this.status = "requesting";
    }

    public boolean update() {
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return OrderPicker.class.getSimpleName().toLowerCase();
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
    public String getStatus() {
        return this.status;
    }
}
