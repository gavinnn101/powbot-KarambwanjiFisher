package KarambwanjiFisher.Tasks;

import KarambwanjiFisher.KarambwanjiFisher;
import KarambwanjiFisher.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.World;
import org.powbot.api.rt4.Worlds;

public class RunAway extends Task {

    Area safeArea = new Area(new Tile(2820, 3039, 0), new Tile(2825, 3034, 0));
    public static final int walkingAnimation = 1205;

    KarambwanjiFisher main;

    public RunAway(KarambwanjiFisher main) {
        super();
        super.name = "FishKarambwanji";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Players.local().healthBarVisible() && Players.local().movementAnimation() < walkingAnimation;
    }

    @Override
    public void execute() {
        KarambwanjiFisher.state("Running to safe area");
        Movement.moveTo(safeArea.getRandomTile());
        Condition.wait(() -> safeArea.contains(Players.local().tile()), 500, 50);
        // how to world hop?
        // or wait out the hobgoblin but idk how long that'd take
    }
}
