package KarambwanjiFisher.Tasks;

import KarambwanjiFisher.KarambwanjiFisher;
import KarambwanjiFisher.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;


public class StayAlive extends Task {

    KarambwanjiFisher main;

    public static final String[] allowedFood = {"Cake", "Trout", "Salmon", "Bass", "Lobster", "Swordfish", "Shark"};

    public StayAlive(KarambwanjiFisher main) {
        super();
        super.name = "StayAlive";
        this.main = main;
    }

    @Override
    public boolean activate() {
        // We should make this take a variable health % to eat at
        return Players.local().healthPercent() <= 60;
    }

    @Override
    public void execute() {
        KarambwanjiFisher.state("Eating food");
        // We could check for items in our inventory with the "Eat" option, but then we might eat something we don't want to
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 20);
        }
        for (String possibleFood : allowedFood) {
            Item food = Inventory.stream().name(possibleFood).first();
            if (food.valid()) {
                food.interact("Eat");
            }
        }
    }
}
