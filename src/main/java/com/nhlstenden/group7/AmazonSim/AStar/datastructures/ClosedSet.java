package com.nhlstenden.group7.AmazonSim.AStar.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.nhlstenden.group7.AmazonSim.AStar.ISearchNode;

public class ClosedSet implements IClosedSet {
    private ArrayList<ISearchNode> list;
    private Comparator<ISearchNode> comp;

    public ClosedSet(Comparator<ISearchNode> comp) {
        this.list = new ArrayList<ISearchNode>();
        this.comp = comp;
    }

    @Override
    public boolean contains(ISearchNode node) {
        return this.list.contains(node);
    }

    @Override
    public void add(ISearchNode node) {
        this.list.add(node);

    }

    @Override
    public ISearchNode min() {
        return Collections.min(this.list, this.comp);
    }

}
