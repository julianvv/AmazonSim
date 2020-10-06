package com.nhlstenden.group7.AmazonSim.controllers;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.nhlstenden.group7.AmazonSim.models.Model;
import com.nhlstenden.group7.AmazonSim.views.View;

public abstract class Controller implements  Runnable, PropertyChangeListener {
    private List<View> views;
    private Model model;

    public Controller(Model model){
        this(model, new ArrayList<View>());
    }

    public Controller(Model model, List<View> views){
        this.model = model;
        this.model.addObserver(this);
        this.views = new ArrayList<>(views);
    }

    public void addView(View view){
        this.views.add(view);
        this.onViewAdded(view);
    }

    protected  abstract void onViewAdded(View view);

    protected List<View> getViews(){
        return  this.views;
    }

    protected void removeView(View view){
        this.views.remove(view);
    }

    protected Model getModel(){
        return this.model;
    }

    public final void start(){
        new Thread(this).start();
    }

    public abstract void run();
}
