package game.Actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Actions.AttackAction;
import game.ActorsBehaviour.AttackBehaviour;
import game.ActorsBehaviour.Status;
import game.ActorsBehaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing the Huntsman Spider actor within the game.
 * This spider is an enemy actor that can attack with its intrinsic weapon and perform wandering behavior.
 */
public class HuntsmanSpider extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private IntrinsicWeapon weapon;

    /**
     * Constructor for Huntsman Spider. Initializes its name, display character, hit points, and behaviors.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1);
        this.weapon = new IntrinsicWeapon(1,"with one of its long legs hit",25);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Returns the intrinsic weapon of the spider, which is used in combat.
     *
     * @return the intrinsic weapon with specific damage and description.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.weapon;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The huntsman spider can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

}
