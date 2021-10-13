package hotciv.factories;

import hotciv.variants.actionStrategy.AlphaActionStrategy;
import hotciv.variants.actionStrategy.UnitActionStrategy;
import hotciv.variants.agingStrategy.AgingStrategy;
import hotciv.variants.agingStrategy.HundredYearStrategy;
import hotciv.variants.attackStrategy.AlgoAttackStrategy;
import hotciv.variants.attackStrategy.AttackStrategy;
import hotciv.variants.attackStrategy.dieDecisionStrategy.SixSidedDieStrategy;
import hotciv.variants.UnitAndTileStrategy.UnitAndTileStrategy;
import hotciv.variants.UnitAndTileStrategy.NormalUnitAndTileStrategy;
import hotciv.variants.winnerStrategy.Alternating20RoundWinnerStrategy;
import hotciv.variants.winnerStrategy.WinnerStrategy;
import hotciv.variants.worldStrategy.AlphaCivLayoutStrategy;
import hotciv.variants.worldStrategy.WorldLayoutStrategy;

public class ZetaCivFactory implements HotCivFactory{
    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new AlphaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new HundredYearStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new Alternating20RoundWinnerStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AlgoAttackStrategy(new SixSidedDieStrategy());
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaCivLayoutStrategy();
    }

    @Override
    public UnitAndTileStrategy createMovingStrategy() {
        return new NormalUnitAndTileStrategy();
    }
}
