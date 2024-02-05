package thollaus;

import javax.swing.*;
import java.awt.*;

public class ViewSchlange extends JPanel {
      private final ControllerSchlange spiel;
      private final JLabel scoreLabel;

      public ViewSchlange(ControllerSchlange spiel){
            super();
            this.spiel = spiel;

            JPanel scoreboard = new JPanel();
            scoreboard.setBackground(Color.LIGHT_GRAY);
            scoreLabel = new JLabel("Score: " + (spiel.getSchlange().getSchlangenlaenge() - 1));
            scoreboard.add(scoreLabel);
            spiel.add(scoreboard, BorderLayout.NORTH);

            spiel.add(this);
      }

      public void updateScore() {
            scoreLabel.setText("Score: " + (spiel.getSchlange().getSchlangenlaenge() - 1));
      }

      @Override
      public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            spiel.render(g2);
      }
}
