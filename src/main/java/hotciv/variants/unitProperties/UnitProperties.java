package hotciv.variants.unitProperties;

import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;
import java.util.Map;

public class UnitProperties {

    private int defense;
    private int attack;
    private int moveCount;
    private ArrayList<TileImpl> validTiles;
    private int cost;




    public UnitProperties(int cost, int defense, int attack, int moveCount, ArrayList<TileImpl> validTiles) {
     this.defense = defense;
     this.attack = attack;
     this.moveCount = moveCount;
     this.validTiles = validTiles;
     this.cost = cost;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<TileImpl> getValidTiles() {
        return validTiles;
    }
}
