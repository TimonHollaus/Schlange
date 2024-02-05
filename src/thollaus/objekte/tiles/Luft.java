package thollaus.objekte.tiles;

import javax.swing.*;
import java.awt.*;

public class Luft extends Tile {


      public Luft(int x, int y) {
            super(x, y);
      }
      @Override
      public void render(Graphics2D g, int tileSize){
            g.setColor(Color.black);
      }
}
