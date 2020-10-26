package com.nhlstenden.group7.AmazonSim.models;

public interface Object3D {
    String getUUID();
    String getType();

    double getX();
    double getY();
    double getZ();

    double getRotationX();
    double getRotationY();
    double getRotationZ();

    public String getStatus();
}
