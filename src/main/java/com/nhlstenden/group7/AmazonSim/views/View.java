package com.nhlstenden.group7.AmazonSim.views;

import com.nhlstenden.group7.AmazonSim.base.Command;
import com.nhlstenden.group7.AmazonSim.models.Object3D;

public interface View {
    void update(String event, Object3D data);
    void onViewClose(Command command);
}
