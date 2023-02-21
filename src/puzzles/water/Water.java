package puzzles.water;

import solver.Configuration;
import solver.Solver;
import solver.WaterConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the water buckets puzzle.
 *
 * @author Ming Creekmore mec5765
 */
public class Water {

    /**
     * Run an instance of the water buckets puzzle.
     * @param args [0]: desired amount of water to be collected;
     *             [1..N]: the capacities of the N available buckets.
     */
    public static void main( String[] args ) {
        if ( args.length < 2 ) {
            System.out.println(
                    ( "Usage: java Water amount bucket1 bucket2 ..." )
            );
        }
        else {
            //make an arraylist of all buckets with their max values (found in input)
            ArrayList<Integer> buckets = new ArrayList();
            for(int i = 1; i<args.length; i++){
                buckets.add(Integer.parseInt(args[i]));
            }

            //Make a new water configuration to solve
            WaterConfiguration water = new WaterConfiguration(Integer.parseInt(args[0]), buckets);
            List<Configuration> solved= Solver.solve(water);
            System.out.println("Amount: " + args[0] + ", Buckets: " + buckets);

            //how many things went through to solve
            System.out.println("Total Configurations: " + Solver.getTotal_config());
            System.out.println("Unique Configurations: " + Solver.getUnique_config());

            //if there is a solution, print it out
            if(solved.size()>0) {
                int i = 0;
                for (Configuration con : solved) {
                    System.out.println("Step " + i + ": " + con);
                    i++;
                }
            }
            //there is no solution
            else{
                System.out.println("No Solution");
            }
        }
    }
}
