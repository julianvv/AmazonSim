package com.nhlstenden.group7.AmazonSim.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class World implements Model{

    private List<Object3D> worldObjects;

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public World(){
        this.worldObjects = new ArrayList<>();
        this.worldObjects.add(new Robot());
        this.worldObjects.add(new Robot());
        this.worldObjects.add(new Stellage());
        this.worldObjects.add(new Building());
    }

    @Override
    public void update(){
        for(Object3D object : this.worldObjects){
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

        for(Object3D object : this.worldObjects){
            returnList.add(new ProxyObject3D(object));
        }
        return returnList;
    }
}
