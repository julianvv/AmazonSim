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

    @Override
    public void setX(double x) {
        this.object.setX(x);
    }

    @Override
    public void setY(double y) {
        this.object.setY(y);
    }

    @Override
    public void setZ(double z) {
        this.object.setZ(z);
    }

    @Override
    public void setRotationX(double rotationX) {
        this.object.setRotationX(rotationX);
    }

    @Override
    public void setRotationY(double rotationY) {
        this.object.setRotationY(rotationY);
    }

    @Override
    public void setRotationZ(double rotationZ) {
        this.object.setRotationZ(rotationZ);
    }

    public String setStatus(String status){
        this.object.setStatus(status);
        return "Status van "+this.object+": "+status;
    }

    public String getStatus(){
        return this.object.getStatus();
    }

    @Override
    public void setLerp(double lerp) {
        this.object.setLerp(lerp);
    }

    @Override
    public double getLerp() {
        return this.object.getLerp();
    }
}
