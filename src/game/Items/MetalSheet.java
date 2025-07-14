package game.Items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing a Metal Sheet item in the game.
 * This item can be picked up and dropped by the player
 */
public class MetalSheet extends Item {

    /**
     * Constructs a Metal Sheet item, setting its name, display character, and whether it can be picked up.
     */
    public MetalSheet() {
        super("Metal Sheet", '%', true);
    }
}

