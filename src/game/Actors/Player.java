package game.Actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.ActorsBehaviour.Status;
import game.Utilities.FancyMessage;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Wong Wei Jian
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Returns the intrinsic weapon of the player, which is used in combat situations.
     *
     * @return an instance of IntrinsicWeapon representing the player's basic attack
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(1,"punches",5);
    }

    /**
     * Applies damage to the player and checks if the player's health drops to zero or below.
     * If the player's health reaches zero, a special message is displayed.
     *
     * @param points the amount of damage to apply to the player
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);  // Call the superclass method to actually apply the damage.

        // After applying damage, check if the player's hit points are 0 or less.
        if (getAttribute(BaseActorAttributes.HEALTH) <= 0) {
            // If the player has no hit points left, print the "You Are Fired" message.
            System.out.println(FancyMessage.YOU_ARE_FIRED);
        }
    }

    /**
     * Determines the player's action each turn, handling multi-turn actions and presenting action options through a menu.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction the Action this Actor took last turn
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the next Action the player chooses to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }
}
