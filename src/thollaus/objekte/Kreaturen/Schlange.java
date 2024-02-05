package thollaus.objekte.Kreaturen;

import thollaus.ControllerSchlange;
import thollaus.objekte.tiles.Apfel;
import thollaus.objekte.tiles.Luft;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Schlange extends Kreatur implements KeyListener{
      public int Schlangenlaenge;

      public Schlange(ControllerSchlange spiel, double centerX, double centerY, double radius, double speed) {
            super(spiel, centerX, centerY, radius, speed, Color.blue);
            this.Schlangenlaenge = 1;
      }

      private void tickApfelCollision() {
            int x = (int) centerX;
            int y = (int) centerY;

            if(spiel.getSpielfeld().getTile(x, y) instanceof Apfel){
                        spiel.getSpielfeld().setTile(x, y, new Luft(x, y));

                        int rdm1 = (int)(Math.random() * 13) + 1;
                        int rdm2 = (int)(Math.random() * 13) + 1;
                        spiel.getSpielfeld().setTile(rdm1, rdm2, new Apfel(rdm1, rdm2));

                        grow();
                        spiel.getView().updateScore();
            }
      }

      @Override
      public void tick(){
            super.tick();
            tickApfelCollision();
      }

      @Override
      public void render(Graphics2D g, int tileSize) {
            double previousX;
            double previousY;
            double centerXOnScreen = centerX * tileSize;
            double centerYOnScreen = centerY * tileSize;
            double radiusOnScreen = radius * tileSize;
            double diameterOnScreen = radiusOnScreen * 2;

            g.setColor(color);
            g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));

            for (int i = 1; i < Schlangenlaenge; i++) {
                  previousX = centerXOnScreen - i * this.movingDirextionX * radiusOnScreen * 2;
                  previousY = centerYOnScreen - i * this.movingDirextionY * radiusOnScreen * 2;
                  g.fill(new Ellipse2D.Double(previousX - radiusOnScreen + 0.5, previousY - radiusOnScreen + 0.5, diameterOnScreen, diameterOnScreen));

            }
      }


      public void grow(){
            this.Schlangenlaenge++;

      }
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            switch (e.getKeyCode()){
                  case KeyEvent.VK_W -> {
                        preferredDirextionX = 0;
                        preferredDirextionY = -1;
                  }
                  case KeyEvent.VK_S -> {
                        preferredDirextionX = 0;
                        preferredDirextionY = 1;
                  }
                  case KeyEvent.VK_A -> {
                        preferredDirextionX = -1;
                        preferredDirextionY = 0;
                  }
                  case KeyEvent.VK_D -> {
                        preferredDirextionX = 1;
                        preferredDirextionY = 0;
                  }
            }
      }
      @Override
      public void keyReleased(KeyEvent e) {

      }

      public int getSchlangenlaenge(){
            return this.Schlangenlaenge;
      }


}
