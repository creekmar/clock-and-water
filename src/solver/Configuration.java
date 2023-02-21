package solver;
import java.util.ArrayList;

/**
 * Configuration abstraction for the solver algorithm
 *
 * @author Ming Creekmore
 */
public interface Configuration {
    //Returns whether the configuration has the solution or not
    public boolean isSolution();

    //returns all the neighbours next to the configuration (possible routes to take)
    public ArrayList<Configuration> getNeighbours();
}
