package thollaus;

import thollaus.objekte.SpielObjekt;
import thollaus.objekte.tiles.Apfel;
import thollaus.objekte.tiles.Luft;
import thollaus.objekte.tiles.Randblock;
import thollaus.objekte.tiles.Tile;

import java.awt.*;

public class Spielfeld extends SpielObjekt {
      private static final int[][] MAP = {
              {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
              {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
      };

      private final int tileSize;
      private final Tile[][] tiles;

      public Spielfeld(int tileSize){
            this.tileSize = tileSize;
            tiles = new Tile[MAP.length][MAP[0].length];

            for (int spalte = 0; spalte < tiles.length; spalte++) {
                  for (int zeile = 0; zeile < tiles[0].length; zeile++) {

                        int tileID = MAP[spalte][zeile];
                        tiles[spalte][zeile] = switch(tileID){

                              case 1 ->
                                    new Randblock(spalte, zeile);
                              default ->
                                    new Luft(spalte, zeile);
                        };
                  }
            }
            int rdm1 = (int)(Math.random() * 13) + 1;
            int rdm2 = (int)(Math.random() * 13) + 1;
            tiles[rdm1][rdm2] = new Apfel(rdm1, rdm2);
      }

      @Override
      public void render(Graphics2D g, int tileSize) {
            for (Tile[] row : tiles) {
                  for(Tile tile : row){
                        tile.render(g, tileSize);
                  }
            }
      }

      public boolean istFrei(int spalte, int zeile){
           if(spalte < 0 || zeile < 0 || spalte >= tiles[0].length || zeile >= tiles.length )
                 return false;

            return !(tiles[zeile][spalte] instanceof Randblock);
      }

      public int getTileSize() {
            return this.tileSize;
      }

      public Tile getTile(int x, int y) {
            return tiles[x][y];
      }
      public void setTile(int x, int y, Tile tile){
            this.tiles[x][y] = tile;
      }
}
