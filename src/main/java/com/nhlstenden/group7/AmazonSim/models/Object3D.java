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

    void setX(double x);
    void setY(double y);
    void setZ(double z);

    void setRotationX(double rotationX);
    void setRotationY(double rotationY);
    void setRotationZ(double rotationZ);

    public String setStatus(String status);
    public String getStatus();

    void setLerp(double lerp);
    double getLerp();
}
