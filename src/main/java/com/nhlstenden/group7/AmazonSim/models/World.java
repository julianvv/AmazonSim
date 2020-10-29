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
    private boolean isAtStart;
    private List<Robot> robotList;
    private List<Stellage> stellageList;
    private List<Stellage> reservedStellages;
    private List<Truck> truckList;
    private int[][] stellagePositions;
    private int[][] robotPositions;
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public World(){
        this.dockNode = new Node(97,0);
        reservedStellages = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        this.robotList = new ArrayList<>();
        this.stellageList = new ArrayList<>();
        this.truckList = new ArrayList<>();
        this.isBuildingLoaded = false;
        this.buildingList.add(new Building());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        //this.robotList.add(new Robot());
        //this.robotList.add(new Robot());
        //this.robotList.add(new Robot());
        //this.robotList.add(new Robot());
        //this.robotList.add(new Robot());
        //this.robotList.add(new Robot());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.stellageList.add(new Stellage());
        this.truckList.add(new Truck());
        stellageList.addAll(truckList.get(0).getStellages());
        stellagePositions = new int[][]{
                {-58, 101, 0}, {-58, 98, 0}, {-58, 95, 0}, {-58, 92, 0}, {-58, 89, 0},
                {-45, 101, 0}, {-45, 98, 0}, {-45, 95, 0}, {-45, 92, 0}, {-45, 89, 0},
                {-32, 101, 0}, {-32, 98, 0}, {-32, 95, 0}, {-32, 92, 0}, {-32, 89, 0},
                {-19, 101, 0}, {-19, 98, 0}, {-19, 95, 0}, {-19, 92, 0}, {-19, 89, 0},
                {-6, 101, 0}, {-6, 98, 0}, {-6, 95, 0}, {-6, 92, 0}, {-6, 89, 0},
                {10, 101, 0}, {10, 98, 0}, {10, 95, 0}, {10, 92, 0}, {10, 89, 0},
        };

        robotPositions = new int[][]{
                {36, 89, 0}, {36, 92, 0}, {36, 95, 0}, {36, 98, 0},
                {49, 89, 0}, {49, 92, 0}, {49, 95, 0}, {49, 98, 0},
                {62, 89, 0},{62, 92, 0}, {62, 95, 0}, {62, 98, 0},
        };

        this.isAtStart = true;
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
        if(isAtStart){
            for(Stellage stellage : stellageList){
                if(stellage.getStatus().equals("start")){
                    for(int i = 0; i < stellagePositions.length; i ++){
                        if(stellagePositions[i][2] == 0){
                            stellage.setStatus("stored");
                            stellage.setX(stellagePositions[i][0]);
                            stellage.setZ(stellagePositions[i][1]);
                            stellagePositions[i][2] = 1;
                            break;
                        }
                    }
                }
            }

            for(Robot robot : robotList){
                if(robot.getStatus().equals("start")){
                    for(int i = 0; i < robotPositions.length; i++){
                        if(robotPositions[i][2] == 0){
                            robot.setStatus("idle");
                            robot.setX(robotPositions[i][0]);
                            robot.setZ(robotPositions[i][1]);
                            robotPositions[i][2] = 1;
                            break;
                        }
                    }
                }
            }
        }



        Truck truck = truckList.get(0);

        if(truck.getStatus().equals("unloading")){
            for(Robot robot : robotList){
                if(!robot.getHasStellage() && !robot.getStatus().equals("busy")){
                    if(truck.getStellageCount() > 0){
                        Node currentNode = robot.getCurrentNode();
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
                if(robot.getHasStellage()){
                    if(robot.getCurrentNode().equals(dockNode)){
                        truck.addStellage(robot.getStellage());
                        robot.getStellage().setTruck(truck);
                        robot.getStellage().setStatus("truck");
                        robot.setStellage(null);
                        robot.setTargetStellage(null);
                    }else{
                        robot.setTarget(dockNode);
                    }

                }else{

                    if(robot.getTargetStellage() == null){
                        double closestHeuristix = Double.MAX_VALUE;
                        Stellage closestStellage = null;
                        if(reservedStellages.size() < truck.getMaxStellages()){
                            for(Stellage stellage : stellageList){
                                    if(stellage.getStatus().equals("stored") && !stellage.getIsReserved()){
                                        double currentDist = Math.abs(robot.getX() - stellage.getX()) + Math.abs(robot.getZ() - stellage.getZ());
                                        if(currentDist < closestHeuristix){
                                            closestHeuristix = currentDist;
                                            closestStellage = stellage;
                                        }
                                    }
                            }
                            robot.setTargetStellage(closestStellage);
                            robot.setTarget(closestStellage.getCurrentNode());
                            closestStellage.setIsReserved(true);
                            reservedStellages.add(closestStellage);
                        }else{
                            robot.setStatus("idle");
                        }
                    }else if(robot.getTargetStellage().getCurrentNode().getCol() == robot.getCurrentNode().getCol() && robot.getTargetStellage().getCurrentNode().getRow() == robot.getCurrentNode().getRow()){
                        robot.setStellage(robot.getTargetStellage());
                        robot.setTarget(dockNode);
                    }
                }
            }
        }
    }
}
