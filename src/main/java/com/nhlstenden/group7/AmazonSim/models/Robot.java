package com.nhlstenden.group7.AmazonSim.models;
import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Astar;
import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;

import java.util.List;
import java.util.UUID;

public class Robot implements Object3D, Updatable {
    private UUID uuid;
    private String status= "idle";
    private Stellage stellage;
    private double steps = 10;
    private double lerp = 0;
    private Node nextNode;
    private Node target;
    private Astar astar;
    private List<Node> path;

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
        if(nextNode == null){
            if(path == null){
                if(target == null){
                    return true;
                }else{
                    astar = new Astar(174, 140, new Node((int) this.getX() + 89, (int) this.getZ() + 10), target);
                    path = astar.findPath();
                    nextNode = path.get(0);
                    path.remove(0);

                    //TODO: Transport method toepassen.
                    this.x = nextNode.getRow() - 89;
                    this.z = nextNode.getCol() - 10;
                }
            }else if(path.size() > 0){
                nextNode = path.get(0);
                path.remove(0);
            }
        }else{
            //TODO: Transport method toepassen.
            this.x = nextNode.getRow() - 89;
            this.z = nextNode.getCol() - 10;
            nextNode = null;
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
        this.rotationX = x;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public Stellage getStellage(){
        return this.stellage;
    }

    public void setStellage(Stellage stellage){
        this.stellage = stellage;
    }

    public boolean getHasStellage(){
        if (stellage == null){
            return false;
        }
        return true;
    }

    public void setTarget(Node target){
        this.target = target;
    }
}
