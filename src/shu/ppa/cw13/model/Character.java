package shu.ppa.cw13.model;

/**
 * Created by victo on 01/03/2017.
 */
public class Character {
    private boolean onBoat;
    private int position;

    public Character(boolean onBoat){
        this.onBoat = onBoat;
    }

    public Character() {
        onBoat = false;
    }

    public boolean isOnBoat() {
        return onBoat;
    }

    public void setOnBoat(boolean b){
        onBoat = b;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
