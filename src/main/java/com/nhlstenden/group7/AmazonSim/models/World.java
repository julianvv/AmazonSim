package com.nhlstenden.group7.AmazonSim.models;

import com.nhlstenden.group7.AmazonSim.AstarAlgorithm.Node;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class World implements Model{
    private Node dockNode;
    private List<Building> buildingList;
    private List<OrderPicker> orderPickerList;
    private boolean isAtStart;
    private List<Robot> robotList;
    private List<Robot> reservedUnloadingRobots;
    private List<Robot> reservedLoadingRobots;
    private List<Robot> reservedOrderRobots;
    private List<Stellage> stellageList;
    private List<Stellage> reservedStellages;
    private List<Truck> truckList;
    private int[][] stellagePositions;
    private int[][] robotPositions;
    private int[][] requestPositions;
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public World(){
        this.dockNode = new Node(97,0);
        this.reservedStellages = new ArrayList<>();
        this.reservedUnloadingRobots =  new ArrayList<>();
        this.reservedLoadingRobots =  new ArrayList<>();
        this.reservedOrderRobots = new ArrayList<>();

        this.buildingList = new ArrayList<>();
        this.buildingList.add(new Building());

        this.orderPickerList = new ArrayList<>();
        this.orderPickerList.add(new OrderPicker());

        this.robotList = new ArrayList<>();
        this.addRobots(15);

        this.stellageList = new ArrayList<>();
        this.addStellages(15);

        this.truckList = new ArrayList<>();
        this.truckList.add(new Truck());
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
                {125, 79,0}, {125, 82,0}, {125, 85,0}, {125, 88,0}, {125, 91, 0},
                {138, 79,0}, {138, 82,0}, {138, 85,0}, {138, 88,0}, {138, 91, 0},
                {151, 79,0},{151, 82,0}, {151, 85,0}, {151, 88,0}, {151, 91, 0},
        };

        requestPositions = new int[][]{
                {0, 88, 0}, {0, 85, 0}, {0, 82, 0}
        };
        this.isAtStart = true;
    }

    @Override
    public void update(){
        simulationLoop();
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
        for(Object3D object : orderPickerList){
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

    public void addRobots(int count){
        for(int i = 0; i < count; i ++){
            this.robotList.add(new Robot());
        }
    }

    public void addStellages(int count){
        for(int i = 0; i < count; i ++){
            this.stellageList.add(new Stellage());
        }
    }

    public void simulationLoop(){
        if(isAtStart){
            for(Stellage stellage : stellageList){
                if(stellage.getStatus().equals("start")){
                    for(int i = 0; i < stellagePositions.length; i ++){
                        if(stellagePositions[i][2] == 0){
                            stellage.setStatus("stored");
                            stellagePositions[i][2] = 1;
                            stellage.setX(stellagePositions[i][0] - 89);
                            stellage.setZ(stellagePositions[i][1] - 10);
                            stellage.setStorePosition(new Node(stellagePositions[i][0], stellagePositions[i][1]));
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
                            robotPositions[i][2] = 1;
                            robot.setX(robotPositions[i][0] - 89);
                            robot.setZ(robotPositions[i][1] - 10);
                            robot.setStorePosition(new Node(robotPositions[i][0], robotPositions[i][1]));
                            break;
                        }
                    }
                }
            }
            isAtStart = true;
        }

        for(Robot robot : robotList){
            if(robot.getStatus().equals("addingStellages")){
                checkUnloadingTarget(robot);
            }
        }


        OrderPicker orderPicker = orderPickerList.get(0);
        if(orderPicker.getStatus().equals("requesting")){
            for(Robot robot : robotList){
                switch(robot.getStatus()){
                    case "idle":
                        if(reservedOrderRobots.size() < requestPositions.length){
                            if(getIsFreeOrderPosition()){
                                reservedOrderRobots.add(robot);
                                freeRobotTarget(robot);
                                robot.setTarget(getClosestStellage(robot, false));
                                robot.setStatus("grabbingStellage");
                            }
                        }
                        break;

                    case "grabbingStellage":
                        if(robot.getIsAtTarget()){
                            robot.setStellage(robot.getTargetStellage());
                            freeStellageTarget(robot.getStellage());
                            robot.getStellage().setStatus("attached");
                            robot.setTarget(getFirstFreeOrderPosition());
                            robot.setStatus("toRequest");
                        }
                        break;

                    case "toRequest":
                        if(robot.getIsAtTarget()){
                            robot.setTimer(System.currentTimeMillis());
                            robot.setStatus("awaitingOrderPicker");
                        }
                        break;

                    case "done":
                        reservedOrderRobots.remove(robot);
                        freeOrderPosition(robot);
                        robot.setTarget(getFirstStellageTarget());
                        robot.setStatus("addingStellages");
                        break;
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
                                if(truck.getStellageCount() > reservedUnloadingRobots.size()){
                                    reservedUnloadingRobots.add(robot);
                                    robot.setStatus("unloading");
                                    freeRobotTarget(robot);
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
                        }
                    }
                }else{
                    truck.setStatus("loading");
                }
                break;
            case "loading":
                //Logics
                if(truck.getStellageCount() < truck.getMaxStellages()){
                    for(Robot robot : robotList){
                        switch (robot.getStatus()){
                            case "idle":
                                if(reservedLoadingRobots.size() < (truck.getMaxStellages() - truck.getStellageCount()) ){
                                    reservedLoadingRobots.add(robot);
                                    freeRobotTarget(robot);
                                    robot.setTarget(getClosestStellage(robot, true));
                                    robot.setStatus("loading");
                                }
                                break;

                            case "loading":
                                if(robot.getIsAtTarget()){
                                    robot.setStellage(robot.getTargetStellage());
                                    freeStellageTarget(robot.getStellage());
                                    robot.getStellage().setStatus("attached");
                                    robot.setTarget(dockNode);
                                    robot.setStatus("removingStellages");
                                }
                                break;

                            case "removingStellages":
                                if(robot.getIsAtTarget()){
                                    truck.addStellage(robot.getStellage());
                                    robot.getStellage().setIsNew(true);
                                    robot.getStellage().setTruck(truck);
                                    robot.getStellage().setRobot(null);
                                    robot.setStellage(null);
                                    robot.setStatus("idle");
                                    robot.setTarget(this.getFirstRobotTarget());
                                    robot.setStorePosition(robot.getTarget());
                                    reservedLoadingRobots.remove(robot);
                                }
                                break;
                        }
                    }
                }else{
                    for(Stellage stellage : stellageList){
                        if(stellage.getTruck() == null){
                            stellage.setIsNew(false);
                        }
                    }
                    truck.setStatus("taking-off");
                }
                break;
        }
    }

    private void checkUnloadingTarget(Robot robot){
        if(robot.getIsAtTarget()){
            robot.getStellage().setStatus("stored");
            robot.getStellage().setStorePosition(robot.getCurrentNode());
            robot.getStellage().setIsReserved(false);
            robot.getStellage().setRobot(null);
            robot.setStellage(null);
            reservedUnloadingRobots.remove(robot);
            robot.setStatus("idle");
            Node storePosition = this.getFirstRobotTarget();
            robot.setTarget(storePosition);
            robot.setStorePosition(storePosition);
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
                break;
            }
        }
    }

    private void freeStellageTarget(Stellage stellage){
        for(int i = 0; i < stellagePositions.length; i ++){
            Node currentPos = new Node(stellagePositions[i][0], stellagePositions[i][1]);
            if(stellage.getStorePosition().equals(currentPos)){
                stellagePositions[i][2] = 0;
                break;
            }
        }
    }

    private void freeOrderPosition(Robot robot){
        for(int i = 0; i < requestPositions.length; i ++){
            Node currentPos = new Node(requestPositions[i][0], requestPositions[i][1]);
            if(robot.getCurrentNode().equals(currentPos)){
                requestPositions[i][2] = 0;
                break;
            }
        }
    }

    private Node getClosestStellage(Robot robot, boolean isOld){
        double closestHeuristix = Double.MAX_VALUE;
        Stellage closestStellage = null;
        for(Stellage stellage : stellageList){
            if(isOld){
                if(stellage.getStatus().equals("stored") && !stellage.getIsReserved() && !stellage.getIsNew()){
                    double currentDist = Math.abs(robot.getX() - stellage.getX()) + Math.abs(robot.getZ() - stellage.getZ());
                    if(currentDist < closestHeuristix){
                        closestHeuristix = currentDist;
                        closestStellage = stellage;
                    }
                }
            }else{
                if(stellage.getStatus().equals("stored") && !stellage.getIsReserved()){
                    double currentDist = Math.abs(robot.getX() - stellage.getX()) + Math.abs(robot.getZ() - stellage.getZ());
                    if(currentDist < closestHeuristix){
                        closestHeuristix = currentDist;
                        closestStellage = stellage;
                    }
                }
            }
        }
        closestStellage.setIsReserved(true);
        robot.setTargetStellage(closestStellage);
        return closestStellage.getCurrentNode();
    }

    private boolean getIsFreeOrderPosition(){
        for(int i = 2; i >= 0; i --){
            if(requestPositions[i][2] == 0){
                return true;
            }
        }
        return false;
    }

    private Node getFirstFreeOrderPosition(){
        for(int i = 0; i < requestPositions.length; i ++){
            if(requestPositions[i][2] == 0){
                requestPositions[i][2] = 1;
                return new Node(requestPositions[i][0], requestPositions[i][1]);
            }
        }
        return new Node(0 , 85);
    }
}
