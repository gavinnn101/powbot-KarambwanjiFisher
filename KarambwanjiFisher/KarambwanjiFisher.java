package KarambwanjiFisher;

import KarambwanjiFisher.Tasks.*;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(
        name = "KarambwanjiFisher",
        description = "Fishes karambwanji. Tries to run away if attacked (lv56 and lower).",
        version = "0.0.1",
        author = "Gavin101"
)


public class KarambwanjiFisher extends AbstractScript {
    private Constants c = new Constants();
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static String currentState = "null";

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("KarambwanjiFisher", "main", "127.0.0.1:5585", true, true);
    }


    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    break;
                }
            }
        }
    }

    @Override
    public void onStart() {
        state("Starting Gavin101's karambwanji fisher...");
        Condition.wait(() -> Players.local().valid(), 500, 50);
        state("Checking Camera");
        Util.cameraCheck();
        // Check account for activity requirements
        checkRequirements();
        // Build task list
        taskList.add(new StayAlive(this));
        if (Players.local().getCombatLevel() < 57) { // hobgoblins are aggressive to anyone lower than lv57
            taskList.add(new RunAway(this));
        }
        taskList.add(new FishKarambwanji(this));
        taskList.add(new StayLoggedIn(this));

        Paint paint = new PaintBuilder()
                .addString(() -> currentState)
                .trackInventoryItem(Constants.rawKarambwanji)
                .build();
        addPaint(paint);
    }

    public void checkRequirements() {
        state("Checking activity requirements");
        // Feels weird to repeat this code but can't think of a good way to refactor atm
        Util.changeGameTab(Game.Tab.STATS);
        if (Skills.realLevel(org.powbot.api.rt4.Constants.SKILLS_FISHING) < 5) {
            state("You don't have the required fishing level(5). Stopping script.");
            if (Game.logout()) {
                Condition.wait(() -> !Players.local().valid(), 500, 20);
                ScriptManager.INSTANCE.stop();
            }
        }
        Util.changeGameTab(Game.Tab.INVENTORY);
        if (Inventory.stream().name("Small fishing net").isEmpty()) {
            state("Didn't find Small fishing net in inventory. Stopping script.");
            if (Game.logout()) {
                Condition.wait(() -> !Players.local().valid(), 500, 20);
                ScriptManager.INSTANCE.stop();
            }
        }
    }

    public static void state(String s) {
        currentState = s;
        System.out.println(s);
    }

}