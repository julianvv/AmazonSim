package com.nhlstenden.group7.AmazonSim.models;

import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;

import java.util.UUID;

public class Stellage implements Object3D, Updatable{
    private UUID uuid;
    private String status;
    private Robot robot;
    private Truck truck;
    private boolean isNew;
    private boolean isReserved;

    private double x = 5;
    private double y = -0.84;
    private double z = -5;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Stellage(){
        this.uuid = UUID.randomUUID();
        this.status = "start";
        this.isNew = false;
        this.isReserved = false;

    }

    public Stellage(Truck truck){
        this.uuid = UUID.randomUUID();
        this.status = "truck";
        this.isNew = true;
        this.isReserved = false;
    }

    public boolean update(){
        if(robot != null){
            this.x = this.robot.getX();
            this.y = this.robot.getY() - 0.15;
            this.z = this.robot.getZ();
            this.rotationX = this.robot.getRotationX();
            this.rotationY = this.robot.getRotationY();
            this.rotationZ = this.robot.getRotationZ();
        }

        if(truck != null){
            this.x = this.truck.getX();
            this.y = this.truck.getY() - 0.15;
            this.z = this.truck.getZ();
        }
        return true;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Stellage.class.getSimpleName().toLowerCase();
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

    public String setStatus(String status) {
        this.status = status;
        return "done";
    }

    public String getStatus(){
        return this.status;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Truck getTruck(){
        return this.truck;
    }

    public void setRobot(Robot robot){
        this.robot = robot;
    }

    public Node getCurrentNode(){
        return new Node((int) this.x + 89, (int) this.z + 10);
    }

    public boolean getIsReserved(){
        return this.isReserved;
    }

    public void setIsReserved(boolean value){
        this.isReserved = value;
    }
}
