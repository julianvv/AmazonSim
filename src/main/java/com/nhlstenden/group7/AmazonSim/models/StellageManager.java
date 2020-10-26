package com.nhlstenden.group7.AmazonSim.models;


import com.nhlstenden.group7.AmazonSim.base.App;

import java.util.List;

public class StellageManager extends Stellage {
    private List<Object3D> objects;
    private Object3D stellage;
    private Object3D closestRobot;

    public StellageManager(String uuid, World world){
        objects = world.getWorldObjectsAsList();
        int index = getModelIndex(uuid);
        if(index != -1){
            stellage = objects.get(index);
            //closestRobot = getClosesRobotHeuristix(stellage);

        }else{
            System.out.println("Stellage niet gevonden...");
        }


    }

    private int getModelIndex(String uuid){
        for(int i = 0; i < objects.size(); i ++){
            String object = objects.get(i).getUUID();
            if(object.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

//    private Object3D getClosesRobotHeuristix(Object3D stellage){
//        double distance = 99999;
//        Object3D closestRobot = null;
//        for(int i = 0; i< App.getRobotList().size(); i ++){
//            Object3D current = App.getRobotList().get(i);
//            if(!(current.getStatus() == "busy" || current.getStatus() == "attached")){
//                double newDist = Math.abs(current.getX() - stellage.getX()) + Math.abs(current.getZ() - stellage.getZ());
//                if(newDist < distance){
//                    distance = newDist;
//                    closestRobot = current;
//                }
//            }
//        }
//        return closestRobot;
//    }
}
