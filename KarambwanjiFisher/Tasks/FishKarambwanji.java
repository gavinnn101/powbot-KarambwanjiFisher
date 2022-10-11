package KarambwanjiFisher.Tasks;

import KarambwanjiFisher.Task;
import KarambwanjiFisher.KarambwanjiFisher;
import KarambwanjiFisher.Util;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;

public class FishKarambwanji extends Task {
    KarambwanjiFisher main;
    public static final int fishingAnimation = 621;
    Area fishingArea = new Area(new Tile(2796, 3026, 0), new Tile(2811, 3008, 0));

    public FishKarambwanji(KarambwanjiFisher main) {
        super();
        super.name = "FishKarambwanji";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return (Players.local().animation() == -1 && !Players.local().interacting().healthBarVisible());
    }

    @Override
    public void execute() {
        // Get to the karambwanji fishing spot if needed
        if (!fishingArea.contains(Players.local().tile())) {
            KarambwanjiFisher.state("Going to fishing spot");
            if (Movement.moveTo(fishingArea.getRandomTile()).getSuccess()) {
                Condition.wait(() -> fishingArea.contains(Players.local().tile()), 250, 50);
            }
        }
        // Start fishing as long as the spot is valid
        Npc karambwanjiSpot = Npcs.stream().name("Fishing spot").nearest().first();
        if (karambwanjiSpot.valid()) {
            KarambwanjiFisher.state("Fishing karambwanji");
            if (!karambwanjiSpot.inViewport()) {
                Camera.turnTo(karambwanjiSpot);
            } else if (karambwanjiSpot.interact("Net")) {
                Condition.wait(() -> Players.local().animation() == fishingAnimation, 250, 40);
            }
        } else { // Couldn't find a valid fishing spot. We probably just need to move a bit to find one.
            KarambwanjiFisher.state("Moving to new spot in fishing area");
            Tile newTile = fishingArea.getRandomTile();
            if (Movement.walkTo(newTile)) {
                Condition.wait(() -> Players.local().tile() == newTile, 250, 50);
            }
        }
    }
}
