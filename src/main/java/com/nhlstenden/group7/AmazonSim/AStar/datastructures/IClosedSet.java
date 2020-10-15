package com.nhlstenden.group7.AmazonSim.AStar.datastructures;

import com.nhlstenden.group7.AmazonSim.AStar.ISearchNode;

public interface IClosedSet {

    public boolean contains(ISearchNode node);
    public void add(ISearchNode node);
    public ISearchNode min();

}
