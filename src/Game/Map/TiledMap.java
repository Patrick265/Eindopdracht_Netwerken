package Game.Map;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TiledMap extends JPanel {
    private int height;
    private int width;

    private ArrayList<TiledLayer> layers = new ArrayList<>();

    public TiledMap(String filename)
    {
        JsonReader reader;
        JsonObject objectReader = null;
        try
        {
            reader = Json.createReader(new FileInputStream(filename));
            objectReader = (JsonObject) reader.read();
            JsonArray tilesets = objectReader.getJsonArray("tilesets");

            BufferedImage image = ImageIO.read(new FileInputStream("res/map/tilesheet.png"));
            height = image.getHeight() / 32;
            width = image.getWidth() / 32;

            for (int i = 0; i < tilesets.size(); i++)
            {
                JsonObject tileset = tilesets.getJsonObject(i);

                int tileWidth = objectReader.getInt("tilewidth");
                int tileHeight = objectReader.getInt("tileheight");
                int index = tileset.getInt("firstgid");

            }
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }

        JsonArray jsonLayers = objectReader.getJsonArray("layers");
        JsonArray jsontarget = null;
        for (int i = 0; i < jsonLayers.size(); i++)
        {
            if (jsonLayers.getJsonObject(i).getString("type").equals("tilelayer"))
            {
                layers.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
            } else if (jsonLayers.getJsonObject(i).getString("type").equals("objectgroup"))
            {
                jsontarget = jsonLayers.getJsonObject(i).getJsonArray("objects");
            }
        }

    }

    public void debugDraw(Graphics2D g2d, AffineTransform tx)
    {
        for(int i = 0; i < layers.size(); i++)
        {
            System.out.println(layers.get(i).getData());
        }
        for (TiledLayer l : layers)
        {
            if (l.isVisible())
                g2d.drawImage(l.getImage(), tx, null);
        }
    }

}