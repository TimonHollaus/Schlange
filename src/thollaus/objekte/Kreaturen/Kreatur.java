package thollaus.objekte.Kreaturen;

import thollaus.ControllerSchlange;
import thollaus.Spielfeld;
import thollaus.objekte.SpielObjekt;

import javax.swing.*;
import java.awt.*;

public abstract class Kreatur extends SpielObjekt {

      protected final ControllerSchlange spiel;
      protected double centerX;
      protected double centerY;
      protected final double radius;
      protected final double speed;
      protected Color color;

      protected int preferredDirextionX;
      protected int preferredDirextionY;
      protected int movingDirextionX;
      protected int movingDirextionY;

      public Kreatur(ControllerSchlange spiel, double centerX, double centerY, double radius, double speed, Color color) {
            this.spiel = spiel;
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;
            this.speed = speed;
            this.color = color;
      }

      public void snapX(){
            centerX = (int) centerX + 0.5;
            movingDirextionX = 0;
      }
      public void snapY(){
            centerY = (int)centerY + 0.5;
            movingDirextionY  = 0;
      }

      private void tickWallColision() {
            Spielfeld spielfeld = spiel.getSpielfeld();

            if (movingDirextionX == 1 && !spielfeld.istFrei((int) (centerX + 0.5), (int) centerX)
                    || movingDirextionX == -1 && !spielfeld.istFrei((int)(centerX - 0.5), (int) centerX)){
                  snapX();
                  verloren();
            } else if (movingDirextionY == 1 && !spielfeld.istFrei((int) (centerY + 0.5), (int) centerY)
                    || movingDirextionY == -1 && !spielfeld.istFrei((int) (centerY - 0.5), (int) centerY)) {
                  snapY();
                  verloren();
            }
      }

      public void verloren(){
            JOptionPane.showMessageDialog(null, "Game over du gui!\n\nScore: " +
                    (spiel.getSchlange().getSchlangenlaenge() - 1));
            System. exit(0);
      }
      private void tickMovingDirection() {
            if (movingDirextionX == 0 && movingDirextionY == 0){
                  movingDirextionX = preferredDirextionX;
                  movingDirextionY = preferredDirextionY;
            } else if (movingDirextionX == 1 && preferredDirextionX != 0) {
                  movingDirextionX = 1;
            } else if (movingDirextionX == -1 && preferredDirextionX != 0) {
                  movingDirextionX = -1;
            } else if (movingDirextionY == 1 && preferredDirextionY != 0) {
                  movingDirextionY = 1;
            } else if (movingDirextionY == -1 && preferredDirextionY != 0) {
                  movingDirextionY = -1;
            }
      }

      private void tickTurn(boolean crossedCenterX, boolean crossedCenterY) {

            boolean turnXToY = crossedCenterX && movingDirextionX != 0 && preferredDirextionY != 0 && spiel.getSpielfeld().istFrei((int)centerX, (int) (centerY + preferredDirextionY));
            boolean turnYtoX = crossedCenterY && movingDirextionY != 0 && preferredDirextionX != 0 && spiel.getSpielfeld().istFrei((int) (centerX + preferredDirextionX), (int) centerY);
            if(turnXToY){
                  snapX();
                  movingDirextionY = preferredDirextionY;
            }else if (turnYtoX){
                  snapY();
                  movingDirextionX = preferredDirextionX;
            }
      }

      public void tick() {
            tickMovingDirection();

            double newX = centerX + movingDirextionX * speed;
            double newY = centerY + movingDirextionY * speed;

            boolean crossedCenterX = Math.abs((centerX - 0.5) % 1.0 - ( newX - 0.5) % 1.0) > 0.5;
            boolean crossedCenterY = Math.abs((centerY - 0.5) % 1.0 - ( newY - 0.5) % 1.0) > 0.5;

            centerX = newX;
            centerY = newY;

            tickTurn(crossedCenterX, crossedCenterY);
            tickWallColision();
      }




      public double getCenterX() {
            return centerX;
      }

      public double getCenterY() {
            return centerY;
      }

      public double getRadius() {
            return radius;
      }
}
