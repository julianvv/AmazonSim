package com.nhlstenden.group7.AmazonSim.controllers;

import com.nhlstenden.group7.AmazonSim.base.Command;
import com.nhlstenden.group7.AmazonSim.models.Model;
import com.nhlstenden.group7.AmazonSim.models.Object3D;
import com.nhlstenden.group7.AmazonSim.views.View;

import java.beans.PropertyChangeEvent;

public class SimulationController extends Controller {

    public SimulationController(Model model){
        super(model);
    }

    @Override
    public void run(){
        while(true){
            this.getModel().update();

            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onViewAdded(final View view){
        final Controller t = this;

        view.onViewClose(new Command(){

            @Override
            public void execute(){
                t.removeView(view);
            }
        });

        for(Object3D object : this.getModel().getWorldObjectsAsList()){
            view.update(Model.UPDATE_COMMAND, object);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        for(int i = 0; i < this.getViews().size(); i++){
            View currentView = this.getViews().get(i);

            if(currentView != null){
                currentView.update(evt.getPropertyName(), (Object3D)evt.getNewValue());
            }
        }
    }
}
