package game.ActorsBehaviour;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.AttackAction;
import game.Actors.Player;

/**
 * Attack behaviour for the Huntsman Spider.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * This method checks adjacent locations for the Intern and initiates an attack if found.
     * @param actor the Actor acting, presumably the HuntsmanSpider in this case.
     * @param map the GameMap containing the Actor.
     * @return an Action that the actor can perform, or null if no valid action is found.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location there = exit.getDestination();
            if (there.containsAnActor() && there.getActor() instanceof Player) {
                // Assuming AttackAction is properly defined to handle the attack
                return new AttackAction(there.getActor(), exit.getName());
            }
        }
        return null;  // No Intern found nearby, no action to perform
    }
}
