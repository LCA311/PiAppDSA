package de.slg.gianni.piapp;

public abstract class VectorHandler {

    private static final Queue<Vector> directionQueue;

    static {
        directionQueue = new Queue<>();
    }

    static void addVector(Direction d, int number) {

        if(directionQueue.isEmpty())
            directionQueue.enqueue(new Vector(d, number));

        Direction last = getLastVector().direction;
        directionQueue.enqueue(new Vector(Direction.values()[(last.ordinal()+d.ordinal())%4], number));

    }

    static Vector getLastVector() {
        return directionQueue.front();
    }

    static boolean vectorsEmpty() {

        return directionQueue.isEmpty();

    }

}
