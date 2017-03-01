package shu.ppa.cw13.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 28/02/2017.
 */
public class Game {
    private Character[] chars;
    private Character farmer;
    private Character beans;
    private Character fox;
    private Character goose;
    private Boat boat;
    private boolean gameFailed;
    private List<Character> leftGrass = new ArrayList<>();
    private List<Character> rightGrass = new ArrayList<>();


    //initialize game with every character on the rightGrass
    public Game(){
        chars = new Character[4];
        farmer = new Character();
        beans = new Character();
        fox = new Character();
        goose = new Character();
        boat = new Boat();
        chars[0] = farmer;
        chars[1] = beans;
        chars[2] = fox;
        chars[3] = goose;
        for(Character c : chars) {
            rightGrass.add(c);
        }
    }

    //returns success
    public boolean getOnBoat(Character c){
        if(boat.getPassengers().size()==boat.getCapacity()){
            return false; //the boat is full
        }
        int pos = boat.getPosition();
        if(pos == 1 && leftGrass.contains(c)) {
            boat.addPassenger(c);
            leftGrass.remove(c);
            updateCharacterPosition(c);
            return true; //the character moved from the leftGrass to the boat
        }
        if(pos == 2 && rightGrass.contains(c)){
            boat.addPassenger(c);
            rightGrass.remove(c);
            updateCharacterPosition(c);
            return true; //the character moved from the rightGrass to the boat
        }
        return false; //the character wasn't next to the boat or was already on it.
    }

    //returns success
    public boolean getOffBoat(Character c){
        if(!boat.containsPassenger(c)){
            return false; //character wasn't on the boat
        }
        int pos = boat.getPosition();
        if(pos==1){
            leftGrass.add(c);
            boat.removePassenger(c);
            updateCharacterPosition(c);
            return true; //character moved from boat to leftGrass
        }
        if(pos==2){
            rightGrass.add(c);
            boat.removePassenger(c);
            updateCharacterPosition(c);
            return true; //character moved from boat to rightGrass
        }
        return false; //something went wrong
    }

    public boolean moveCharacterLeft(Character c){
        if(boat.containsPassenger(c) && boat.getPosition()==1){
            return getOffBoat(c); //character was on boat next to leftGrass and moved to leftGrass
        }
        if(rightGrass.contains(c)){
            return getOnBoat(c); //character was on rightGrass and tried boarding the boat
        }
        return false;
    }

    public boolean moveCharacterRight(Character c){
        if(boat.containsPassenger(c) && boat.getPosition()==2){
            return getOffBoat(c); //character was on boat next to rightGrass and moved to rightGrass
        }
        if(leftGrass.contains(c)){
            return getOnBoat(c); //character was on leftGrass and tried boarding the boat
        }
        return false;
    }

    public boolean moveBoatLeft(){
        if(!boat.containsPassenger(farmer)){
            return false; //farmer wasn't on the boat
        }
        if(boat.getPosition()==2){
            boat.setPosition(1);
            for(Character c : boat.getPassengers()){
                updateCharacterPosition(c); //update character positions of passengers
            }
            gameFailed(); //game might fail at this point
            return true; //boat moved left
        }
        return false; //boat couldn't move left
    }

    public boolean moveBoatRight(){
        if(!boat.containsPassenger(farmer)){
            return false; //farmer wasn't on the boat
        }
        if(boat.getPosition()==1){
            boat.setPosition(2);
            for(Character c : boat.getPassengers()){
                updateCharacterPosition(c); //update character positions of passengers
            }
            gameFailed(); //game might fail at this point
            return true; //boat moved left
        }
        return false; //boat couldn't move left
    }

    public void updateCharacterPosition(Character c){
        if(c.isOnBoat()){
            c.setPosition(boat.getPosition());
        } else if(leftGrass.contains(c)){
            c.setPosition(0);
        } else{
            c.setPosition(3);
        }
    }

    public void updateCharacterPositions(){
        for(Character c : chars){
            updateCharacterPosition(c);
        }
    }

    public int getCharacterPosition(Character c){
        updateCharacterPosition(c);
        return c.getPosition();
    }

    public int getBoatPosition(){
        return boat.getPosition();
    }

    public void gameFailed(){
        if(leftGrass.contains(goose) && !leftGrass.contains(farmer)){
            if (leftGrass.contains(fox) || leftGrass.contains(beans)){
                gameFailed = true;
            }
        } else
        if(rightGrass.contains(goose) && !rightGrass.contains(farmer)){
            if (rightGrass.contains(fox) || rightGrass.contains(beans)){
                gameFailed = true;
            }
        }
    }



    /*
    might need these methods to use them with the gui buttons
     */

    public void moveFarmerLeft(){
        moveCharacterLeft(farmer);
    }
    public void moveBeansLeft(){
        moveCharacterLeft(beans);
    }
    public void moveFoxLeft(){
        moveCharacterLeft(fox);
    }
    public void moveGooseLeft(){
        moveCharacterLeft(goose);
    }
    public void moveFarmerRight(){
        moveCharacterRight(farmer);
    }
    public void moveBeansRight(){
        moveCharacterRight(beans);
    }
    public void moveFoxRight(){
        moveCharacterRight(fox);
    }
    public void moveGooseRight(){
        moveCharacterRight(goose);
    }
}
