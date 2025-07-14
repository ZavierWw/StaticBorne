package game.Items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing a Large Bolt item in the game.
 * This item can be picked up and dropped by the player
 */
public class LargeBolt extends Item {

    /**
     * Constructs a Large Bolt item, setting its name, display character, and whether it can be picked up.
     */
    public LargeBolt() {
        super("Large Bolt", '+', true);
    }
}

