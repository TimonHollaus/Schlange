package thollaus.objekte.tiles;

import java.awt.*;

public class Randblock extends Tile{


      public Randblock(int x, int y) {
            super(x, y);
      }

      @Override
      public void render(Graphics2D g, int tileSize) {
            g.setColor(Color.green.darker().darker().darker());
            g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
      }
}
