package Game.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TiledLayer {
    private JsonArray data = null;
    private int height;
    private int width;
    private boolean visible;
    private TiledMap map;
    private int[][] indices;
    private BufferedImage image;

    public TiledLayer(String fileName, int layer){
        try {
            JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
            JsonObject root = reader.readObject();

            height = root.getJsonArray("layers").getJsonObject(0).getInt("height");
            width = root.getJsonArray("layers").getJsonObject(0).getInt("width");

            visible = root.getJsonArray("layers").getJsonObject(0).getBoolean("visible");

            for(int y = 0; y < getHeight(); y++) {
                for(int x = 0; x < getWidth(); x++) {
                    data.add(root.getJsonArray("layers").getJsonObject(layer).getJsonArray("data"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        BufferedImage img = new BufferedImage(32*width, 32*height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                int tileIndex = indices[y][x];
                if(tileIndex <= map.getTiledSize().size() && tileIndex > 0)
                {
                    AffineTransform tx = new AffineTransform();
                    tx.translate(x*32, y*32);
                    g2.drawImage(map.getTiledSize().get(tileIndex).tile, tx, null);
                }
            }
        }
        return img;
    }

    public boolean isVisible() { return visible;}

    public BufferedImage getImage() {return image; }

    public void updateImage() {
        image = createImage();
    }

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getIndices() {
        return indices;
    }
}
