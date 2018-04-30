package game.map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TiledLayer {
    private JsonArray data;
    private int height;
    private int width;
    private boolean visible;
    private TiledMap map;
    private int[][] indices;
    private BufferedImage image;

    public TiledLayer(JsonObject layer, TiledMap tiledMap) {
            this.map = tiledMap;
            data = layer.getJsonArray("data");
            height = layer.getInt("height");
            width = layer.getInt("width");
            visible = layer.getBoolean("visible");

            indices = new int[height][width];

            int i = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    indices[y][x] = data.getInt(i);
                    i++;
                }
            }

        image = createImage();
    }

    public BufferedImage createImage()
    {
        BufferedImage img = new BufferedImage(64*width, 64*height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                int tileIndex = indices[y][x];
                if(tileIndex <= map.getTiles().size() && tileIndex > 0)
                {
                    AffineTransform tx = new AffineTransform();
                    tx.translate(x*64, y*64);
                    g2.drawImage(map.getTiles().get(tileIndex).tile, tx, null);
                }
            }
        }
        return img;
    }

    public boolean isVisible() { return visible;}

    public BufferedImage getImage() {return image; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
