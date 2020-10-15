package com.nhlstenden.group7.AmazonSim.AStar;

import java.util.ArrayList;

public class SearchNode2D extends SearchNode {
    private int x;
    private int z;
    private SearchNode2D parent;
    private GoalNode2D goal;

    public SearchNode2D(int x, int z, SearchNode2D parent, GoalNode2D goal){
        this.x = x;
        this.z = z;
        this.parent = parent;
        this.goal = goal;

    }
    public SearchNode2D getParent(){
        return this.parent;
    }
    public ArrayList<ISearchNode> getSuccessors() {
        ArrayList<ISearchNode> successors = new ArrayList<ISearchNode>();
        successors.add(new SearchNode2D(this.x-1, this.z, this, this.goal));
        successors.add(new SearchNode2D(this.x+1, this.z, this, this.goal));
        successors.add(new SearchNode2D(this.x, this.z+1, this, this.goal));
        successors.add(new SearchNode2D(this.x, this.z-1, this, this.goal));
        return successors;
    }
    public double h() {
        return this.dist(goal.getX(), goal.getZ());
    }
    public double c(ISearchNode successor) {
        SearchNode2D successorNode = this.castToSearchNode2D(successor);
        return 1;
    }
    public void setParent(ISearchNode parent) {
        this.parent = this.castToSearchNode2D(parent);
    }
    public boolean equals(Object other) {
        if(other instanceof SearchNode2D) {
            SearchNode2D otherNode = (SearchNode2D) other;
            return (this.x == otherNode.getX()) && (this.z == otherNode.getZ());
        }
        return false;
    }

    public int hashCode() {
        return (41 * (41 + this.x + this.z));
    }
    public double dist(int otherX, int otherZ) {
        return Math.sqrt(Math.pow(this.x-otherX,2) + Math.pow(this.z-otherZ,2));
    }
    public int getX() {
        return this.x;
    }
    public int getZ() {
        return this.z;
    }
    public String toString(){
        return "(" + Integer.toString(this.x) + ";" + Integer.toString(this.z)
                + ";h:"+ Double.toString(this.h())
                + ";g:" +  Double.toString(this.g()) + ")";
    }

    private SearchNode2D castToSearchNode2D(ISearchNode other) {
        return (SearchNode2D) other;
    }

    public Integer keyCode() {
        return null;
    }
}
