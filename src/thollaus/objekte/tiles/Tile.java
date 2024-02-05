package thollaus.objekte.tiles;

import thollaus.Spielfeld;
import thollaus.objekte.SpielObjekt;

public abstract class Tile extends SpielObjekt {
      protected final int x, y;

      public Tile(int x, int y) {
            this.x = x;
            this.y = y;
      }
}
