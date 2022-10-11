package KarambwanjiFisher.Tasks;

import KarambwanjiFisher.KarambwanjiFisher;
import KarambwanjiFisher.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;

public class StayLoggedIn extends Task {

    KarambwanjiFisher main;


    public StayLoggedIn(KarambwanjiFisher main) {
        super();
        super.name = "StayLoggedIn";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        KarambwanjiFisher.state("Moving camera to prevent logout");
        if (Camera.angle(Random.nextInt(1,200))) {
            Condition.sleep(Random.nextInt(30_000, 60_000));
        }
    }
}
