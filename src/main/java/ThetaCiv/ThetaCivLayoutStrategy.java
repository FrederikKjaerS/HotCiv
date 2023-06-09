package ThetaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.unitProperties.UnitPropertiesStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

import java.util.HashMap;
import java.util.Map;

public class ThetaCivLayoutStrategy implements WorldLayoutStrategy {

    @Override
    public Map<Position, Tile> setupTileLayout() {
        String[] layout = new String[]{
                "...oododdoo.....",
                "..ohhdddddddddd.",
                ".oddddMddd...dd.",
                ".odMMddododdddoo",
                "...ofodddddddo..",
                ".ofddddoddddddo.",
                "...odd..dddddd..",
                ".oddddddddddoM..",
                ".oddddddddddddf.",
                "offddddoddddddoo",
                "oodddodo...oddoo",
                ".ooMMdddd...ddd.",
                "..oodddddfoddd..",
                "....oddddMMdo...",
                "..ooddddddMd....",
                ".....oddddddoo..",
        };
        Map<Position, Tile> theWorld = new HashMap<>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }
                if (tileChar == 'd') {
                    type = ThetaCivGameConstants.DESERT;
                }
                Position p = new Position(r, c);
                theWorld.put(p, new TileImpl(type));
            }
        }
        return theWorld;
    }

    @Override
    public Map<Position, UnitImpl> setupUnitLayout(UnitPropertiesStrategy strategy) {
        HashMap<Position, UnitImpl> units = new HashMap<>();
        units.put(new Position(3, 8), new UnitImpl(GameConstants.ARCHER, Player.RED,
                strategy.getProperties(GameConstants.ARCHER)));
        units.put(new Position(4, 4), new UnitImpl(GameConstants.LEGION, Player.BLUE,
                strategy.getProperties(GameConstants.LEGION)));
        units.put(new Position(5, 5), new UnitImpl(GameConstants.SETTLER, Player.RED,
                strategy.getProperties(GameConstants.SETTLER)));
        units.put(new Position(9, 6), new UnitImpl(ThetaCivGameConstants.SANDWORM, Player.BLUE,
                strategy.getProperties(ThetaCivGameConstants.SANDWORM)));
        return units;
    }

    @Override
    public Map<Position, CityImpl> setupCityLayout() {
        HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
        cities.put(new Position(8, 12), new CityImpl(Player.RED));
        cities.put(new Position(4, 5), new CityImpl(Player.BLUE));
        return cities;
    }
}
