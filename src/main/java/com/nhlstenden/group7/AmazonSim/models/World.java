package com.nhlstenden.group7.AmazonSim.models;

import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class World implements Model{
    private Node dockNode;
    private Node orderPicker;
    private List<Building> buildingList;
    private boolean isBuildingLoaded;
    private List<Robot> robotList;
    private List<Stellage> stellageList;
    private List<Truck> truckList;

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public World(){
        this.dockNode = new Node(40,5);
        this.buildingList = new ArrayList<>();
        this.robotList = new ArrayList<>();
        this.stellageList = new ArrayList<>();
        this.truckList = new ArrayList<>();
        this.isBuildingLoaded = false;
        this.buildingList.add(new Building());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.truckList.add(new Truck());
    }

    @Override
    public void update(){
        SimulationLoop();
        if(!isBuildingLoaded){
            for(Object3D object : buildingList){
                if(object instanceof Updatable){
                    if(((Updatable)object).update()){
                        pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                    }
                }
            }
            this.isBuildingLoaded = true;
        }

        for(Object3D object : robotList){
            if(object instanceof Updatable){
                if(((Updatable)object).update()){
                    pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                }
            }
        }

        for(Object3D object : stellageList){
            if(object instanceof Updatable){
                if(((Updatable)object).update()){
                    pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                }
            }
        }

        for(Object3D object : truckList){
            if(object instanceof Updatable){
                if(((Updatable)object).update()){
                    pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                }
            }
        }
    }

    @Override
    public void addObserver(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    @Override
    public List<Object3D> getWorldObjectsAsList() {
        ArrayList<Object3D> returnList = new ArrayList<>();
        for(Object3D object : buildingList){
            returnList.add(new ProxyObject3D(object));
        }
        for(Object3D object : robotList){
            returnList.add(new ProxyObject3D(object));
        }
        for(Object3D object : stellageList){
            returnList.add(new ProxyObject3D(object));
        }
        for(Object3D object : truckList){
            returnList.add(new ProxyObject3D(object));
        }
        return returnList;
    }

    public void SimulationLoop(){
        Truck truck = truckList.get(0);

        if(truck.getStatus().equals("unloading")){
            for(Robot robot : robotList){
                if(!robot.getHasStellage() && robot.getStatus().equals("idle")){
                    if(truck.getStellageCount() > 0){
                        Node currentNode = new Node((int) robot.getX() - 89, (int) robot.getZ() - 10);
                        if(currentNode.equals(dockNode)){
                            robot.setStellage(truck.getStellage());
                        }else{
                            robot.setTarget(dockNode);
                        }
                    }
                }
            }
        }

        if(truck.getStatus().equals("loading")){
            for(Robot robot : robotList){
                if(robot.getStatus().equals("idle")){
                    Node currentNode = new Node((int) robot.getX() - 89, (int) robot.getZ() - 10);
                    if(robot.getHasStellage()){
                        if(currentNode.equals(dockNode)){
                            truck.addStellage(robot.getStellage());
                            robot.setStellage(null);
                        }else{
                            robot.setTarget(dockNode);
                        }
                    }else{
                        double closestHeuristix = Double.MAX_VALUE;
                        Stellage closestStellage = null;
                        for(Stellage stellage : stellageList){
                            double currentDist = Math.abs(robot.getX() - stellage.getX()) + Math.abs(robot.getZ() - stellage.getZ());
                            if(currentDist < closestHeuristix){
                                closestHeuristix = currentDist;
                                closestStellage = stellage;
                            }
                        }
                        robot.setTarget(new Node((int) closestStellage.getX() - 89, (int) closestStellage.getZ() - 10));
                    }
                }
            }
        }
    }
}
