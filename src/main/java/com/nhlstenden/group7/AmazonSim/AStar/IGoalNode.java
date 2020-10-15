package com.nhlstenden.group7.AmazonSim.AStar;
import com.nhlstenden.group7.AmazonSim.AStar.ISearchNode;

/**
 * GoalNodes don't need as much Information
 * as SearchNodes.
 */
public interface IGoalNode{
    public boolean inGoal(ISearchNode other);
}
