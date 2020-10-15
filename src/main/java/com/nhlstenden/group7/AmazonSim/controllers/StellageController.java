package com.nhlstenden.group7.AmazonSim.controllers;

import com.nhlstenden.group7.AmazonSim.models.Object3D;
import com.nhlstenden.group7.AmazonSim.models.ProxyObject3D;
import com.nhlstenden.group7.AmazonSim.models.World;

import java.util.ArrayList;
import java.util.List;

public class StellageController {
    private List<Object3D> objects;

    public StellageController(String uuid, World world){
        objects = world.getWorldObjectsAsList();
        int index = getModelIndex(uuid);
        if(index != -1){
            objects.get(index).setStatus("toDock");
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
}
