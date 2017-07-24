package de.slg.gianni.piapp;

public class Vector {
//Hi
    
    Direction direction;
    int number;

    Vector(Direction d, int n) {

        direction = d;
        number = n;

    }

    @Override
    public String toString() {
        return super.toString()+direction.toString()+"-"+number;
    }

}
