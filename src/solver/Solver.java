package solver;

import java.util.*;

/**
 * This class contains a universal algorithm to find a path from a starting
 * configuration to a solution, if one exists
 *
 * @author Ming Creekmore
 */
public class Solver {
    private static int total_config;
    private static int unique_config;

    public static int getTotal_config() {
        return total_config;
    }

    public static int getUnique_config() {
        return unique_config;
    }

    public static List<Configuration> solve(Configuration start) {
        //initializing variables and collections
        total_config = 0;
        unique_config = 0;
        Configuration goal = null;
        Map<Configuration, Configuration> predecessor = new HashMap<>();
        List<Configuration> queue = new LinkedList<>();
        //putting first value in collections
        queue.add(start);
        predecessor.put(start, start);

        //making the predecessor map with set of all configuratios generated
        while(!queue.isEmpty()){
            unique_config += 1;
            Configuration current = queue.remove(0);
            if(current.isSolution()){
                goal = current;
                break;
            }
            for(Configuration neighbours: current.getNeighbours()){
                total_config+=1;
                if(!predecessor.containsKey(neighbours)){
                    predecessor.put(neighbours, current);
                    queue.add(neighbours);
                }
            }
        }

        List<Configuration> path = new LinkedList<>();
        if(goal!=null){
            Configuration current = goal;
            while(!current.equals(start)){
                path.add(0, current);
                current = predecessor.get(current);
            }
            path.add(0, start);
        }

        return path;
    }
}
