package KarambwanjiFisher.Tasks;

import KarambwanjiFisher.KarambwanjiFisher;
import KarambwanjiFisher.Task;
import KarambwanjiFisher.Util;
import org.powbot.api.*;
import org.powbot.api.rt4.*;

import java.time.Instant;

public class StayLoggedIn extends Task {

    KarambwanjiFisher main;

    public static long sleepTime = 0;
    public static long endTime = 0;

    public StayLoggedIn(KarambwanjiFisher main) {
        super();
        super.name = "StayLoggedIn";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Instant.now().getEpochSecond() >= endTime + sleepTime;
    }

    @Override
    public void execute() {
        KarambwanjiFisher.state("Clicking fishing spot (afk)");
        if (Players.local().interacting().interact("Net")) {
            sleepTime = Random.nextInt(30, 60);
            endTime = Instant.now().getEpochSecond();
        }
    }
}