package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;

import java.util.HashMap;
import java.util.Map;

/** FakeObject implementation for Game. Base your
 * development of Tools and CivDrawing on this test double,
 * and gradually add EVIDENT TEST = simple code
 * to it, to support your development of all features
 * necessary for a complete CivDrawing and your suite
 * of Tools.
 *
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class FakeObjectGame implements Game {

  private Map<Position, Unit> unitMap;
  private Map<Position, CityImpl> cityMap;
  private int age = -4000;
  public Unit getUnitAt(Position p) {
    return unitMap.get(p);
  }

  public boolean moveUnit(Position from, Position to) {
    // Using print statements to aid in debugging and development

    Unit unit = getUnitAt(from);
    if (unit == null) return false;
    boolean isMoveInValidRange = Math.abs(to.getRow() - from.getRow()) <= 1
            && Math.abs(to.getColumn() - from.getColumn()) <= 1;
    if (! isMoveInValidRange) return false;
    System.out.println("-- moveUnit found unit at: " + from);
    // Remember to inform game observer on any change on the tiles
    unitMap.put(from, null);
    gameObserver.worldChangedAt(from);
    unitMap.put(to, unit);

    gameObserver.worldChangedAt(to);

    return true;
  }

  // === Turn handling ===
  private Player inTurn;
  public void endOfTurn() {
    System.out.println( "-- FakeObjectGame / endOfTurn called." );
    inTurn = (getPlayerInTurn() == Player.RED ?
              Player.BLUE :
              Player.RED );
    // no age increments implemented...
    if(inTurn == Player.RED){
      age += 100;
    }
    gameObserver.turnEnds(inTurn, this.age);
  }
  public Player getPlayerInTurn() { return inTurn; }

  // === Observer handling ===
  protected GameObserver gameObserver;
  // observer list is fake code, only having a single
  // one, suffices for development.
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  } 

  public FakeObjectGame() {
    defineWorld();
    // Put some units into play
    unitMap = new HashMap<>();
    cityMap = new HashMap<>();
    unitMap.put(new Position(2,0), new StubUnit( GameConstants.ARCHER, Player.RED ));
    unitMap.put(new Position(3,2), new StubUnit( GameConstants.LEGION, Player.BLUE ));
    unitMap.put(new Position(4,2), new StubUnit( GameConstants.SETTLER, Player.RED ));
    unitMap.put(new Position(6,3), new StubUnit( ThetaConstants.SANDWORM, Player.RED ));
    cityMap.put(new Position(2,2), new CityImpl(Player.RED));
    cityMap.put(new Position(0,1), new CityImpl(Player.RED));
    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  protected Map<Position,Tile> world; 
  public Tile getTileAt( Position p ) { return world.get(p); }

  /** define the world.
   */
  protected void defineWorld() {
    world = new HashMap<Position,Tile>();
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        Position p = new Position(r,c);
        world.put( p, new StubTile(GameConstants.PLAINS));
      }
    }
    // Create a little area around the theta unit of special terrain
    world.put(new Position(5,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,2), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,3), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,5), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,3), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,5), new StubTile(ThetaConstants.DESERT));
  }

  public CityImpl getCityAt( Position p ) { return cityMap.get(p); }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {
    getCityAt(p).setWorkForceFocus(balance);
    if(gameObserver != null) {
      gameObserver.worldChangedAt(p);
    }
  }
  public void changeProductionInCityAt( Position p, String unitType ) {
    getCityAt(p).setProduction(unitType);
    if(gameObserver != null) {
      gameObserver.worldChangedAt(p);
    }
  }
  public void performUnitActionAt( Position p ) {
    if (getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)) {
      cityMap.put(p, new CityImpl(getUnitAt(p).getOwner()));
      unitMap.remove(p);
      gameObserver.worldChangedAt(p);
    }
  }

  public void setTileFocus(Position position) {
    if(position.getRow() < GameConstants.WORLDSIZE && position.getColumn() < GameConstants.WORLDSIZE){
      gameObserver.tileFocusChangedAt(position);
    }
  }
}

class StubUnit implements  Unit {
  private String type;
  private Player owner;
  public StubUnit(String type, Player owner) {
    this.type = type;
    this.owner = owner;
  }

  public String getTypeString() { return type; }
  public Player getOwner() { return owner; }
  public int getMoveCount() { return 1; }
  public int getDefensiveStrength() { return 0; }
  public int getAttackingStrength() { return 0; }

  @Override
  public String getID() {
    return null;
  }
}
