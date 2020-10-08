package com.nhlstenden.group7.AmazonSim.models;

public interface Object3D {
    public String getUUID();
    public String getType();

    public double getX();
    public abstract double getY();
    public abstract double getZ();

    public double getRotationX();
    public double getRotationY();
    public  double getRotationZ();

    public String setStatus(String status);
    public String getStatus();
}
