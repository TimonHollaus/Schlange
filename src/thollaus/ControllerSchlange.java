package thollaus;

import thollaus.objekte.Kreaturen.Schlange;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ControllerSchlange extends JFrame {

      private final ViewSchlange view;
      private final Spielfeld spielfeld;
      private final Schlange schlange;

      public static void main(String[] args) {
            new ControllerSchlange();
      }

      public ControllerSchlange(){
            super("Schlange");


            spielfeld = new Spielfeld(40);
            schlange = new Schlange(this, 3.5, 7.5, 0.375, 0.06);
            view = new ViewSchlange(this);
            addKeyListener(schlange);


            setSize(614,660);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(true);
            setVisible(true);

            startSpielLoop();
      }

      private void startSpielLoop(){
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                  schlange.tick();
                  view.repaint();
                  }, 0L ,1000L / 60L, TimeUnit.MILLISECONDS);
      }

      public void render(Graphics2D g2) {
            final int fieldSize = spielfeld.getTileSize();

            for (int x = 0; x < 15; x++) {
                  for (int y = 0; y < 15; y++) {
                        g2.setColor(x%2==0 ^ y%2==0 ? Color.green : Color.green.darker());
                        g2.fillRect(x * fieldSize,y * fieldSize, fieldSize, fieldSize);
                  }
            }

            spielfeld.render(g2, spielfeld.getTileSize());
            schlange.render(g2, spielfeld.getTileSize());
      }

      public Spielfeld getSpielfeld() {
            return spielfeld;
      }
      public Schlange getSchlange(){
            return schlange;
      }
      public ViewSchlange getView() {
            return this.view;
      }
}

