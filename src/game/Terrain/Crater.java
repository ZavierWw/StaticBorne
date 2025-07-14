package game.Terrain;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Actors.HuntsmanSpider;

/**
 * Class representing a Crater terrain feature in the game.
 * Craters are special types of ground that have a chance to spawn a Huntsman Spider during each game tick.
 */
public class Crater extends Ground {

    /**
     * Constructs a Crater terrain feature, setting its display character.
     */
    public Crater() {
        super('u');
    }


    /**
     * This method is called every game tick to perform operations related to the crater.
     * It includes functionality to possibly spawn a Huntsman Spider at this location with a 5% chance each tick.
     *
     * @param location the location of this piece of ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (Math.random() < 0.05) {
            location.addActor(new HuntsmanSpider());
        }
    }
}

