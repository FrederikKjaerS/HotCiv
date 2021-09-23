package hotciv.variants.alphaCiv;

import hotciv.framework.Position;
import hotciv.framework.SettlerActionStrategy;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class NoSettlerActionStrategy implements SettlerActionStrategy {

    @Override
    public HashMap<Position, UnitImpl> performAction(Position p, HashMap<Position, UnitImpl> units) {
        return units;
    }
}