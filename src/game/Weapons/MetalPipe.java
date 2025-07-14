package game.Weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actions.AttackAction;
import game.ActorsBehaviour.Status;

/**
 * Class representing a Metal Pipe weapon in the game.
 * This weapon item can be used to attack actors, with specific functionality to automatically target hostile actors in adjacent locations.
 */
public class MetalPipe extends WeaponItem {

    /**
     * Constructs a Metal Pipe, setting its name, display character, damage delay, hit verb, and damage amount.
     */
    public MetalPipe() {
        super("Metal Pipe", '!', 1, "hits", 20);
    }

    /**
     * Provides a list of actions that can be performed with this Metal Pipe when wielded by an actor at a specific location.
     * This includes creating an attack action if a hostile actor is found in an adjacent location.
     *
     * @param otherActor the actor wielding the Metal Pipe
     * @param location   the current location of the actor
     * @return an ActionList containing all allowable actions for this weapon at the given location
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        GameMap map = location.map();
        Exit exit = track(location);

        // If an exit leading to a hostile actor is found, create an attack action.
        if (exit != null) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor target = map.getActorAt(destination);
                if (target != null && target != otherActor) {
                    actions.add(new AttackAction(target, exit.getName(), this));
                }
            }
        }

        return actions;
    }

    /**
     * Tracks the nearest enemy around the location and returns the exit leading to them.
     * This method is used to identify potential targets for attacks using the Metal Pipe.
     *
     * @param location the location from which to check for nearby enemies
     * @return the Exit that leads to a detected hostile actor, or null if no such actor is found
     */
    private Exit track(Location location) {
        GameMap map = location.map();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actorAtDestination = map.getActorAt(destination);
                if (actorAtDestination != null && actorAtDestination.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return exit;
                }
            }
        }
        return null;
    }
}
