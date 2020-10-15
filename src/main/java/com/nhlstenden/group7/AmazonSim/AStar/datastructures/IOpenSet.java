package com.nhlstenden.group7.AmazonSim.AStar.datastructures;


import com.nhlstenden.group7.AmazonSim.AStar.ISearchNode;

public interface IOpenSet {

    public void add(ISearchNode node);
    public void remove(ISearchNode node);
    public ISearchNode poll();
    //returns node if present otherwise null
    public ISearchNode getNode(ISearchNode node);
    public int size();

}
