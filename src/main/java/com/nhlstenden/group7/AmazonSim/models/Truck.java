package com.nhlstenden.group7.AmazonSim.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Truck implements Object3D, Updatable {
    private UUID uuid;
    private List<Stellage> stellages;
    private String status;
    private int maxStellages;

    private double x = 0;
    private double y = 0.85;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Truck(){
        this.uuid = UUID.randomUUID();
        this.status = "unloading";
        stellages = new ArrayList<>();
        maxStellages = 8;
        addStellages(maxStellages);
    }

    public boolean update(){
        for(Stellage stellage : stellages){
            stellage.setX(this.x - 1);
            stellage.setY(this.y + 2);
            stellage.setZ(this.z);
        }
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    private void addStellages(int amount){
        for(int i = 0; i < amount; i ++){
            stellages.add(new Stellage(this));
        }
    }

    public Stellage getStellage(){
        Stellage stellage = stellages.get(0);
        stellages.remove(0);
        return stellage;
    }

    public int getStellageCount(){
        return stellages.size();
    }

    public void addStellage(Stellage stellage){
        stellages.add(stellage);
    }

    public List<Stellage> getStellages() {
        return stellages;
    }

    public int getMaxStellages(){
        return this.maxStellages;
    }
}
