package com.nhlstenden.group7.AmazonSim.models;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface Model {

    static final String UPDATE_COMMAND = "object_update";

    void update();
    void addObserver(PropertyChangeListener pcl);
    List<Object3D> getWorldObjectsAsList();
}
