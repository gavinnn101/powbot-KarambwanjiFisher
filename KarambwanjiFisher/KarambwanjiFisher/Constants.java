package KarambwanjiFisher;

import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Player;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;

import java.util.ArrayList;

public class Constants {

    public Constants() {
        super();
    }

    public ArrayList<String> userTaskList = new ArrayList<String>();

    public Player p() {
        return Players.local();
    }


    // my stuff
    public String currentState = "null";
    public static final int rawKarambwanji = 3150;
}
