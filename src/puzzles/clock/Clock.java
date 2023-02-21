package puzzles.clock;

import solver.ClockConfiguration;
import solver.Configuration;
import solver.Solver;

import java.util.List;

/**
 * Main class for the "clock" puzzle.
 *
 * @author YOUR NAME HERE
 */
public class Clock {

    /**
     * Run an instance of the clock puzzle.
     * @param args [0]: number of hours on the clock;
     *             [1]: starting time on the clock;
     *             [2]: goal time to which the clock should be set.
     */
    public static void main( String[] args ) {
        if ( args.length != 3 ) {
            System.out.println( "Usage: java Clock hours start end" );
        }
        else {
            System.out.println("Hours: " + args[0] + ", Start: " + args[1] + ", End: " + args[2]);
            //create new clock configuration from input and ask solver to solve
            ClockConfiguration clock = new ClockConfiguration(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            List<Configuration> solved = Solver.solve(clock);
            System.out.println("Total Configurations: " + Solver.getTotal_config());
            System.out.println("Unique Configurations: " + Solver.getUnique_config());
            if(solved.size()>0) {
                int i = 0;
                for (Configuration con : solved) {
                    System.out.println("Step " + i + ": " + con);
                    i++;
                }
            }
            else{
                System.out.println("No solution");
            }
        }
    }
}
