package shu.ppa.cw13.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 01/03/2017.
 */
public class Boat {
    private int position;
    private int capacity;
    private List<Character> passengers;

    public Boat(){
        position = 2;
        capacity = 2;
        passengers = new ArrayList<>();
    }

    public void addPassenger(Character c){
        if(!containsPassenger(c) && passengers.size()<capacity){
            passengers.add(c);
            c.setOnBoat(true);
        }
    }

    public boolean containsPassenger(Character c){
        return passengers.contains(c);
    }

    public void removePassenger(Character c){
        if(containsPassenger(c)){
            passengers.remove(c);
            c.setOnBoat(false);
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Character> getPassengers() {
        return passengers;
    }

    public int getCapacity() {
        return capacity;
    }
}
