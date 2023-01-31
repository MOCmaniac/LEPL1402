import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * With the winter at our doorstep, it is essential for us to heat our
 * houses. However, with the increase of the energy prices, this
 * represents a huge cost for the families.
 *
 * In the city of Louvain-la-Neuve, the local authorities have decided
 * to help them by refilling their fuel tank before the winter. In
 * order to evaluate the total amount of fuel to be purchased, the
 * local authorities have to send some investigators to record how
 * many liters of fuel are needed in each house of the city.
 *
 * In this exercise, we assume that the city is divided as a grid,
 * where each cell is occupied by one single house with its own fuel
 * tank. Each house has four possible neighbor houses: to the left, to
 * the right, to the top, and to the bottom (in other words, our city
 * is a 4-connected grid).
 *
 * We ask you to code the behavior of an investigator. More precisely,
 * you are provided with a path to follow in the city, going from
 * house to house, and you have to sum up all the needs in fuel for
 * each of those houses. Once the investigator has finished her path,
 * you must return the total amount of fuel needed for all the houses
 * that were visited.
 *
 * Beware that the path might not be the optimal one, and that you
 * might visit the same house multiple times!
 *
 * You can add methods or fields in the classes, but you must not
 * modify the signature of the existing methods or constructors.
 */
public class Mazout {

    /**
     * Class that encodes one house.
     **/
    public static class House {
        private final int index;
        private final int capacity;
        private House neighborLeft;
        private House neighborRight;
        private House neighborAbove;
        private House neighborDown;
        
        private House(int index,
                      int capacity) {
            this.index = index;
            this.capacity = capacity;
        }

        /**
         * Get the (unique) index of the house in the city.
         * @return The index.
         **/
        public int getIndex() {
            return index;
        }

        /**
         * Get the capacity of the fuel tank in the house.
         * @return The capacity.
         **/
        public int getCapacity() {
            return capacity;
        }

        /**
         * Get the neighbor house to the left.
         * @return The house.
         **/
        public House getNeighborLeft() {
            return neighborLeft;
        }

        /**
         * Set the neighbor house to the left.
         * @param neighbor The house.
         **/
        public void setNeighborLeft(House neighbor) {
            neighborLeft = neighbor;
        }

        /**
         * Get the neighbor house to the right.
         * @return The house.
         **/
        public House getNeighborRight() {
            return neighborRight;
        }

        /**
         * Set the neighbor house to the right.
         * @param neighbor The house.
         **/
        public void setNeighborRight(House neighbor) {
            neighborRight = neighbor;
        }

        /**
         * Get the neighbor house to the top.
         * @return The house.
         **/
        public House getNeighborAbove() {
            return neighborAbove;
        }

        /**
         * Set the neighbor house to the top.
         * @param neighbor The house.
         **/
        public void setNeighborAbove(House neighbor) {
            neighborAbove = neighbor;
        }

        /**
         * Get the neighbor house to the bottom.
         * @return The house.
         **/
        public House getNeighborDown() {
            return neighborDown;
        }

        /**
         * Set the neighbor house to the bottom.
         * @param neighbor The house.
         **/
        public void setNeighborDown(House neighbor) {
            neighborDown = neighbor;
        }
    }
    

    /**
     * Class that encodes a city composed of houses. Each house is
     * associated with a unique index.
     **/
    public static class City {
        private final List<House> houses = new LinkedList<>();

        /**
         * Add a new house in the city, with a fuel tank of given capacity.
         * @param capacity The capacity of the tank.
         * @return The newly created house.
         **/
        public House addHouse(int capacity) {
            House house = new House(houses.size(), capacity);
            houses.add(house);
            return house;
        }

        /**
         * Return the total number of houses in the city. It is
         * guaranteed that the index of each house in the city is
         * strictly less than this total number.
         * @return The number of houses.
         **/
        public int getNumberOfHouses() {
            return houses.size();
        }
    }
    

    /**
     * This method must compute the total amount of fuel needed to
     * entirely fill the fuel tanks of all the houses on the path
     * starting from `firstHouse` and following the directions stored
     * in `path`.
     * 
     * If an investigator visits a house more than once, its fuel
     * demand is only counted once. During the exploration, you can
     * use the (unique) index of the houses to mark which houses have
     * already been visited.
     *
     * An exception `IllegalArgumentException` must be thrown is the
     * path is invalid (i.e. if it leads to a house outside the
     * city, which corresponds to the `null` value), or if the path
     * contains an invalid direction.
     *
     * @param city The city to be explored.
     * @param firstHouse The first house to be visited.
     * @param path The path to follow. It is an array of strings
     *             containing the four possible directions: "left",
     *             "right", "above" and "down".
     * @return The total amount of fuel.
     */
    public static int getTotalDemand(City city, House firstHouse, String[] path) {
        if(firstHouse==null || !city.houses.contains(firstHouse)){
            throw new IllegalArgumentException("Pas de première maison");
        }
        int total_cost = firstHouse.getCapacity();

        //On regarde par où on est passé
        ArrayList<Integer> marked_house = new ArrayList<Integer>();
        marked_house.add(firstHouse.getIndex());

        House next_house = firstHouse;
        for(String direction : path){
            switch(direction) {
                case "right":
                    next_house = next_house.getNeighborRight();
                    break;
                case "left":
                    next_house = next_house.getNeighborLeft();
                    break;
                case "above":
                    next_house = next_house.getNeighborAbove();
                    break;
                case "down":
                    next_house = next_house.getNeighborDown();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid path");
            }
            if(next_house==null || !city.houses.contains(next_house)){
                throw new IllegalArgumentException("Chemin est sorti de la ville");
            }
            if(!marked_house.contains(next_house.getIndex())){ //Check si déjà passé
                total_cost+=next_house.getCapacity();
                marked_house.add(next_house.getIndex());
            }
        }
        return total_cost;
    }
}
