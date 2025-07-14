package game.Items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Actions.HealingAction;

/**
 * Class representing a Big Fruit item in the game.
 * This item can be consumed by actors to heal themselves, granting a specific amount of health points upon consumption.
 */
public class BigFruit extends Item {

    /**
     * Constructs a Big Fruit item, setting its name, display character, and whether it can be picked up.
     */
    public BigFruit() {
        super("Big Fruit", 'O', true);
    }

    /**
     * Extends the base class's method to add a specific healing action that can be performed by the owner of this item.
     * This healing action allows the owner to consume the fruit and gain health points.
     *
     * @param owner the actor who owns this item
     * @return an ActionList containing all actions that can be performed with this item, including healing
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = super.allowableActions(owner);
        actions.add(new HealingAction(this, 2));
        return actions;
    }
}

