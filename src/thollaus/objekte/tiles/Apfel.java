package thollaus.objekte.tiles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Apfel extends Tile{

      protected final double radius;

      protected Apfel(int x, int y, double radius) {
            super(x, y);
            this.radius = radius;
      }
      public Apfel(int x, int y){
            this(x, y, 0.4);
      }

      public void render(Graphics2D g, int tileSize){
            double centerXOnScreen = getCenterX() * tileSize;
            double centerXApfel = (getCenterX() + 0.4) * tileSize;
            double centerYOnScreen = getCenterY() * tileSize;
            double centerYApfel = (getCenterY() - 0.15) * tileSize;

            double radiusOnScreen = radius * tileSize;
            double diameterOnScreen = radiusOnScreen * 2.0;

            g.setColor(Color.RED);
            g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
            g.setColor(Color.green.darker().darker());
            g.fill(new Ellipse2D.Double(centerXApfel - radiusOnScreen, centerYApfel - radiusOnScreen, diameterOnScreen / 3, diameterOnScreen / 3));
      }

      public double getCenterX(){
            return x + 0.5;
      }
      public double getCenterY(){
            return y + 0.5;
      }

      public double getRadius() {
            return radius;
      }
}
