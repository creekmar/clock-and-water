package solver;
/**
 * Ming Creekmore mec5765
 * WaterConfiguration implements Configuration for the water puzzle
 *
 * @param goal: the target amount of water to be filled
 * @param buckets: arraylist of the max amount of water the buckets can hold
 * @param current: the current amount of water in each bucket
 */

import puzzles.water.Water;

import java.util.ArrayList;
import java.util.Objects;

public class WaterConfiguration implements Configuration{
    private static Integer goal;
    private static ArrayList<Integer> buckets = new ArrayList();
    private ArrayList<Integer> current = new ArrayList();

    //constructor that makes a new water configuration with a new goal and different buckets
    //current is reset since the problem is a new one
    public WaterConfiguration(int goal, ArrayList buckets){
        this.goal = goal;
        this.buckets = buckets;
        for(int i=0; i<buckets.size(); i++){
            this.current.add(0);
        }
    }

    //constructor to make a new configuration based on a change to the amount of water in current
    public WaterConfiguration(ArrayList current){
        this.current = current;
    }

    //Checks to see if any of the buckets holds the desired amount of water
    @Override
    public boolean isSolution() {
        for(int i: current){
            if(i==goal)
                return true;
        }
        return false;

    }

    //returns current amount of water in the buckets
    private ArrayList getCurrent() {
        return current;
    }

    //if the given water configuration equals another object
    @Override
    public boolean equals(Object o) {
        if(o instanceof WaterConfiguration){
            if(this.hashCode()==(o.hashCode()))
                return true;
        }
        return false;
    }

    //hashcode is the hashcode of current
    @Override
    public int hashCode() {
        return Objects.hash(current.hashCode());
    }

    //returns all possible next steps in the problem
    //neighbours: arraylist of configurations to return
    //temp: arraylist of integers (the specific new values of the buckets)
    @Override
    public ArrayList<Configuration> getNeighbours() {
        ArrayList<Configuration> neighbours = new ArrayList();
        ArrayList<Integer> temp;
        //loop through all the buckets to perform each action
        for(int i = 0; i<buckets.size(); i++){
            //fills bucket at i
            temp = fill(i, new ArrayList(this.current));
            if(temp!=null){
                neighbours.add(new WaterConfiguration(new ArrayList(temp)));
            }

            //empties bucket at i
            temp = empty(i, new ArrayList(this.current));
            if(temp!=null) {
                neighbours.add(new WaterConfiguration(new ArrayList(temp)));
            }

            //loops through entire list of buckets again to specify a pouree
            for(int j = 0; j< buckets.size(); j++){
                temp = pour(i, j, new ArrayList<>(this.current));
                if(temp!=null) {
                    neighbours.add(new WaterConfiguration(new ArrayList(temp)));
                }
            }
        }
        return neighbours;
    }

    /**
     * @param pouring index of the bucket that will be pouring into another
     * @param poured index of the bucket that will be poured into
     * @param stuff list of the currect volumes of water in the bucket
     * @return arraylist after the change
     */
    private ArrayList<Integer> pour(int pouring, int poured, ArrayList<Integer> stuff){
        //setting up values
        int max = this.buckets.get(poured);
        int valueofpour = stuff.get(pouring);
        int valueofpouree = stuff.get(poured);

        //if the bucket to pour is not empty and the bucket to pour into is not max
        //if the bucket to pour is not the same as the bucket to be poured in
        if(valueofpour!=0 && valueofpouree!=max &&(pouring!=poured)){
            int space_left = max-valueofpouree;
            //if there is less space than there is water to pour
            //water still left in pouring bucket
            if((space_left)<valueofpour){
                valueofpour = valueofpour-space_left;
                valueofpouree = max;
            }
            else{
                //if there is more space than there is water to pour
                //empty water in pouring bucket into pouree
                valueofpouree += valueofpour;
                valueofpour = 0;
            }
            //updating the old values to the values after getting poured
            stuff.set(pouring, valueofpour);
            stuff.set(poured, valueofpouree);
            return stuff;
        }
        //if it cannot be poured
        return null;
    }

    /**
     * @param empty index of bucket of empty
     * @param stuff list of current values of water in bucket
     * @return arraylist of the updated values
     */
    private ArrayList<Integer> empty(int empty, ArrayList stuff){
        //if the bucket is not already empty
        if(!stuff.get(empty).equals(0)){
            stuff.set(empty, 0);
            return stuff;
        }

        //if there is no change (bucket already empty)
        return null;
    }

    /**
     * @param fill index of bucket to be filled
     * @param stuff list of current values in buckets
     * @return arraylist of updated values after filling
     */
    private ArrayList<Integer> fill(int fill, ArrayList stuff){
        int max = this.buckets.get(fill);
        //if the bucket is not already full
        if(!stuff.get(fill).equals(max)){
            stuff.set(fill, max);
            return stuff;
        }

        //there is no change (bucket already full)
        return null;
    }

    //values of current
    @Override
    public String toString() {
        return "" + current.toString();
    }
}
