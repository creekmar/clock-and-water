package solver;
/**
 * ClockConfiguration implements Configuration for the clock puzzle
 *
 * @param maxhours: the number of hours on the clock
 * @param goal: the hour we want to reach
 * @param pointer: where we currently are
 */

import java.util.ArrayList;

public class ClockConfiguration implements Configuration{
    private static int maxhours;
    private int pointer;
    private static int goal;

    //Constructor to be called when doing a new clock puzzle
    //resets hours, goal, and pointer depending on the new puzzle
    public ClockConfiguration(int hours, int pointer, int goal) {
        this.maxhours = hours;
        this.pointer = pointer;
        this.goal = goal;
    }

    //constructs new ClockConfiguration based on the the change in the pointer
    public ClockConfiguration(int pointer){
        this.pointer = pointer;
    }

    //Is the pointer pointing to the desired hour?
    @Override
    public boolean isSolution() {
        if(this.pointer==goal)
            return true;
        else
            return false;
    }

    //return where the pointer is
    private int getPointer(){
        return pointer;
    }

    //Compare hashcodes
    @Override
    public boolean equals(Object o) {
        if(o instanceof ClockConfiguration){
            if(this.hashCode()==o.hashCode())
                return true;
        }
        return false;
    }

    //hashcode is where the pointer is
    @Override
    public int hashCode() {
        return this.pointer;
    }

    //return the possible next steps the pointer can go
    @Override
    public ArrayList<Configuration> getNeighbours() {
        ArrayList<Configuration> neighbours = new ArrayList<Configuration>();
        if(pointer==maxhours)
            neighbours.add(new ClockConfiguration(1));
        else
            neighbours.add(new ClockConfiguration(pointer+1));
        if(pointer==1)
            neighbours.add(new ClockConfiguration(maxhours));
        else
            neighbours.add(new ClockConfiguration(pointer-1));
        return neighbours;
    }

    //return where pointer currently is
    @Override
    public String toString() {
        return "" + pointer;
    }
}
