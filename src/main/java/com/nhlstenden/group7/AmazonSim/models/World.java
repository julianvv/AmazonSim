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
    private List<Robot> reservedRobots;
    private List<Stellage> stellageList;
    private List<Stellage> reservedStellages;
    private List<Truck> truckList;
    private int[][] stellagePositions;
    private int[][] robotPositions;
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public World(){
        this.dockNode = new Node(97,0);
        this.reservedStellages = new ArrayList<>();
        this.reservedRobots =  new ArrayList<>();
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
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
        this.robotList.add(new Robot());
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
        Truck truck = truckList.get(0);
        stellageList.addAll(truckList.get(0).getStellages());
        stellagePositions = new int[][]{
                {31, 91, 0}, {31, 88, 0}, {31, 85, 0}, {31, 82, 0}, {31, 79, 0},
                { 44, 91, 0}, { 44, 88, 0}, { 44, 85, 0}, { 44, 82, 0}, { 44, 79, 0},
                { 57, 91, 0}, { 57, 88, 0}, { 57, 85, 0}, { 57, 82, 0}, { 57, 79, 0},
                { 70, 91, 0}, { 70, 88, 0}, { 70, 85, 0}, { 70, 82, 0}, { 70, 79, 0},
                { 83, 91, 0}, { 83, 88, 0}, { 83, 85, 0}, { 83, 82, 0}, { 83, 79, 0},
                { 99, 91, 0}, { 99, 88, 0}, { 99, 85, 0}, { 99, 82, 0}, { 99, 79, 0},
        };

        robotPositions = new int[][]{
                {125, 79,0}, {125, 82,0}, {125, 85,0}, {125, 88,0},
                {138, 79,0}, {138, 82,0}, {138, 85,0}, {138, 88,0},
                {151, 79,0},{151, 82,0}, {151, 85,0}, {151, 88,0},
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
                            stellage.setX(stellagePositions[i][0] - 89);
                            stellage.setZ(stellagePositions[i][1] - 10);
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
                            robot.setX(robotPositions[i][0] - 89);
                            robot.setZ(robotPositions[i][1] - 10);
                            robotPositions[i][2] = 1;
                            robot.setStorePosition(new Node(robotPositions[i][0], robotPositions[i][1]));
                            break;
                        }
                    }
                }
            }
        }

        Truck truck = truckList.get(0);
        switch(truck.getStatus()){
            case "unloading":
                //Logics
                if(truck.getStellageCount() >= 1){
                    for(Robot robot : robotList){
                        switch(robot.getStatus()){
                            case "idle":
                                if(truck.getStellageCount() > reservedRobots.size()){
                                    robot.setStatus("unloading");
                                    freeRobotTarget(robot);
                                    reservedRobots.add(robot);
                                    robot.setTarget(dockNode);
                                }
                                break;

                            case "unloading":
                                if(robot.getIsAtTarget()){
                                    robot.setStatus("addingStellages");
                                    robot.setStellage(truck.getStellage());
                                    robot.getStellage().setTruck(null);
                                    robot.setTarget(this.getFirstStellageTarget());
                                }
                                break;

                            case "addingStellages":
                                checkRobotTarget(robot);
                                break;

                            default:
                                System.out.println("Non-valid robot status encountered.");
                                break;
                        }
                    }
                }else{
                    truck.setStatus("loading");
                }
                break;
            case "loading":
                //Logics
                for(Robot robot : robotList){
                    switch (robot.getStatus()){
                        case "idle":

                            break;
                        case "addingStellages":
                            checkRobotTarget(robot);
                            break;
                    }
                }
                System.out.println("Loading");
                break;
        }
    }

    private void checkRobotTarget(Robot robot){
        if(robot.getIsAtTarget()){
            robot.getStellage().setStatus("stored");
            robot.getStellage().setRobot(null);
            robot.setStellage(null);
            reservedRobots.remove(robot);
            robot.setStatus("idle");
            robot.setTarget(this.getFirstRobotTarget());
            System.out.println("Target set to FirstRobotTarget");
        }
    }

    private Node getFirstStellageTarget(){
        for(int i = 0; i < stellagePositions.length; i ++){
            if(stellagePositions[i][2] == 0){
                stellagePositions[i][2] = 1;
                return new Node(stellagePositions[i][0], stellagePositions[i][1]);
            }
        }
        //Default Node
        return new Node(99, 99);
    }

    private Node getFirstRobotTarget(){
        for(int i = 0; i < robotPositions.length; i ++){
            if(robotPositions[i][2] == 0){
                robotPositions[i][2] = 1;
                return new Node(robotPositions[i][0], robotPositions[i][1]);
            }
        }
        //Default Node
        return new Node(151, 100);
    }

    private void freeRobotTarget(Robot robot){
        for(int i = 0; i < robotPositions.length; i ++){
            Node currentPos = new Node(robotPositions[i][0], robotPositions[i][1]);
            if(robot.getStorePosition().equals(currentPos)){
                robotPositions[i][2] = 0;
            }
        }
    }
}
