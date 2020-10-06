package com.nhlstenden.group7.AmazonSim.models;

public class ProxyObject3D implements Object3D{
    private Object3D object;

    public ProxyObject3D(Object3D object){
        this.object = object;
    }

    @Override
    public String getUUID() {
        return this.object.getUUID();
    }

    @Override
    public String getType() {
        return this.object.getType();
    }

    @Override
    public double getX() {
        return this.object.getX();
    }

    @Override
    public double getY() {
        return this.object.getY();
    }

    @Override
    public double getZ() {
        return this.object.getZ();
    }

    @Override
    public double getRotationX() {
        return this.object.getRotationX();
    }

    @Override
    public double getRotationY() {
        return this.object.getRotationY();
    }

    @Override
    public double getRotationZ() {
        return this.object.getRotationZ();
    }
}
