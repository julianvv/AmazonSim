package com.nhlstenden.group7.AmazonSim.AStar;

public class GoalNode2D implements IGoalNode {
    private int x;
    private int z;
    public GoalNode2D(int x, int z) {
        this.x = x;
        this.z = z;
    }
    public boolean inGoal(ISearchNode other) {
        if(other instanceof SearchNode2D) {
            SearchNode2D otherNode = (SearchNode2D) other;
            return (this.x == otherNode.getX()) && (this.z == otherNode.getZ());
        }
        return false;
    }
    public int getX() {
        return this.x;
    }
    public int getZ() {
        return this.z;
    }
}
