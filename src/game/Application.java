package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Actors.HuntsmanSpider;
import game.Actors.Player;
import game.Items.LargeBolt;
import game.Items.MetalSheet;
import game.Terrain.*;
import game.Utilities.FancyMessage;
import game.Weapons.MetalPipe;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Wong Wei Jian
 */
public class Application {
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

        List<String> map = Arrays.asList(
                        "...~~~~.........~~~...........",
                        "...~~~~.......................",
                        "...~~~........................",
                        "..............................",
                        ".............#####............",
                        ".............#___#...........~",
                        ".............#___#..........~~",
                        ".............##_##.........~~~",
                        ".................~~........~~~",
                        "................~~~~.......~~~",
                        ".............~~~~~~~........~~",
                        "......~.....~~~~~~~~.........~",
                        ".....~~~...~~~~~~~~~..........",
                        ".....~~~~~~~~~~~~~~~~........~",
                        ".....~~~~~~~~~~~~~~~~~~~....~~");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        gameMap.at(7, 9).addActor(new HuntsmanSpider());

        Player player = new Player("Intern", '@', 4);
        world.addPlayer(player, gameMap.at(15, 6));

        // Spawn Metal Sheets and Large Bolts
        gameMap.at(11, 10).addItem(new LargeBolt());
        gameMap.at(11, 11).addItem(new MetalSheet());

        // Spawn Inheritree plants
        gameMap.at(8, 3).setGround(new Inheritree());
        gameMap.at(10, 5).setGround(new Inheritree());

        // Spawn craters
        gameMap.at(2, 3).setGround(new Crater());
        gameMap.at(4, 5).setGround(new Crater());

        // Spawn Metal Pipe weapon
        MetalPipe metalPipe = new MetalPipe();
        gameMap.at(13, 8).addItem(metalPipe);

        world.run();
    }
}
