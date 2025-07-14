package game.Terrain;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Items.BigFruit;
import game.Items.SmallFruit;

import java.util.List;

/**
 * Class representing the Inheritree, a type of tree in the game that matures over time and can produce fruit.
 * This tree starts as a sapling and grows into a full tree, with different probabilities and types of fruit produced at each stage.
 */
public class Inheritree extends Ground {
    private int age = 0;

    /**
     * Constructs an Inheritree, initializing it with a character representing a sapling.
     */
    public Inheritree() {
        super('t'); // Start as a sapling
    }

    /**
     * This method is called every game tick to perform operations related to the tree's growth and fruit production.
     * The tree ages each tick, and after a certain age, it changes its display character to represent a mature tree.
     * Depending on its stage (sapling or tree), it has a chance to produce fruit, which is then randomly placed at adjacent locations.
     *
     * @param location the location of this tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;
        if (age == 5) {
            this.setDisplayChar('T');
        }
        // Produce fruit based on the tree's stage
        if (Math.random() < (getDisplayChar() == 't' ? 0.30 : 0.20)) {
            Item fruit = getDisplayChar() == 't' ? new SmallFruit() : new BigFruit();
            List<Exit> exits = location.getExits();
            // Randomly select an exit to drop the fruit, if the exit leads to a valid location
            if (!exits.isEmpty()) {
                Exit exit = exits.get((int) (Math.random() * exits.size()));
                Location destination = exit.getDestination();
                // Check if the destination is valid for item placement
                if (destination.canActorEnter(null)) {
                    destination.addItem(fruit);
                }
            }
        }
    }
}

