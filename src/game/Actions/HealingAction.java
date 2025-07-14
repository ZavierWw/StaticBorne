package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class that represents an action for an actor to heal by consuming an item. This action allows actors to recover health points
 * by consuming a specific item from their inventory, typically some form of consumable like food or a potion.
 */
public class HealingAction extends Action {

    /**
     * The item to be consumed for healing.
     */
    private Item fruit;

    /**
     * The amount of health points to be restored by consuming the item.
     */
    private int healPoints;

    /**
     * Constructs a HealingAction using a specified item and amount of health points to restore.
     *
     * @param fruit      the item to be consumed
     * @param healPoints the amount of health points the item restores when consumed
     */
    public HealingAction(Item fruit, int healPoints) {
        this.fruit = fruit;
        this.healPoints = healPoints;
    }

    /**
     * Executes the healing action by consuming the specified item. The item is removed from the actor's inventory, and a specified
     * number of health points are restored to the actor.
     *
     * @param actor the actor performing the healing action
     * @param map   the game map where the action is being performed
     * @return a string describing the action that was performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(healPoints);
        actor.removeItemFromInventory(fruit);
        return menuDescription(actor);
    }

    /**
     * Provides a string describing the healing action for display in the UI, detailing the item consumed and the amount of health
     * restored.
     *
     * @param actor the actor performing the healing action
     * @return a string describing the healing action, including the actor, the item consumed, and the amount of health restored
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + fruit + " for " + healPoints + " HP";
    }
}
