package com.nhlstenden.group7.AmazonSim.AStar;

import com.nhlstenden.group7.AmazonSim.models.Object3D;
import com.nhlstenden.group7.AmazonSim.models.Stellage;

import java.util.*;

public class ClosestRobotPath {

    public static ArrayList<ISearchNode> SearchNodeTest2D(Stellage stellage, Object3D robot) {

        GoalNode2D goalNode = new GoalNode2D((int)stellage.getX(), (int)stellage.getZ());
        SearchNode2D initialNode = new SearchNode2D((int)robot.getX(), (int)robot.getZ(), null, goalNode);
        ArrayList<ISearchNode> path = new AStar().shortestPath(initialNode, goalNode);
        return path;
    }
}