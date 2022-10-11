package KarambwanjiFisher;

import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import static KarambwanjiFisher.KarambwanjiFisher.state;

public class Util {

    public static void cameraCheck() {
//        System.out.println("Zoom: " +Camera.getZoom());
        if (Camera.getZoom() >= 10) {
            state("Zooming camera out");
            Camera.moveZoomSlider(9);
        }
        if (Camera.pitch() < 90) {
            state("Changing camera angle");
            Camera.pitch(true);
        }
    }

    public static void changeGameTab(Game.Tab tabToUse) {
        if (!Game.tab(tabToUse)) {
            Game.tab(tabToUse);
            Condition.wait(() -> Game.tab(tabToUse), 100, 10);
        }
    }

    public static void walkTo(Area area) {
        // Walks to a random tile in the given location
        // Questionable if this wrapper is really helpful or not
        if (!area.contains(Players.local().tile())) {
            Movement.moveTo(area.getRandomTile());
            // I think webwalking is blocking but add a check just in case
            Condition.wait(() -> area.contains(Players.local().tile()), 250, 50);
        }
    }

    public static void turnTo(Npc npc) {
        if (!npc.inViewport()) {
            Camera.turnTo(npc);
            Condition.wait(npc::inViewport, 100, 10);
        }
    }

    public static void turnTo(GameObject gameobject) {
        if (!gameobject.inViewport()) {
            Camera.turnTo(gameobject);
            Condition.wait(gameobject::inViewport, 100, 10);
        }
    }

    public static void turnTo(GroundItem groundItem) {
        if (!groundItem.inViewport()) {
            Camera.turnTo(groundItem);
            Condition.wait(groundItem::inViewport, 100, 10);
        }
    }
}
