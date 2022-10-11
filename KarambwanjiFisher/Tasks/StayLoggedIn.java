package KarambwanjiFisher.Tasks;

import KarambwanjiFisher.KarambwanjiFisher;
import KarambwanjiFisher.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Camera;

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
        Camera.angle(Random.nextInt(20,120));
        Condition.sleep(Random.nextInt(30000, 60000));
    }
}
