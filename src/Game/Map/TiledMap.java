package game.map;

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

    private ArrayList<TiledTile> tiles = new ArrayList<>();
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
            height = image.getHeight() / 64;
            width = image.getWidth() / 64;

            for (int i = 0; i < tilesets.size(); i++)
            {
                JsonObject tileset = tilesets.getJsonObject(i);

                int tileWidth = objectReader.getInt("tilewidth");
                int tileHeight = objectReader.getInt("tileheight");
                int index = tileset.getInt("firstgid");

                while(tiles.size() < index + (height * width)) {
                    tiles.add(new TiledTile());
                }

                for(int y = 0; y < image.getHeight(); y+= tileHeight)
                {
                    for(int x = 0; x < image.getWidth(); x += tileWidth)
                    {
                        tiles.get(index).tile = image.getSubimage(64*(x/tileWidth), 64 *(y/tileHeight), 64, 64);
                        index++;
                    }
                }

            }
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }

        JsonArray jsonLayers = objectReader.getJsonArray("layers");
        for (int i = 0; i < jsonLayers.size(); i++)
        {
            if (jsonLayers.getJsonObject(i).getString("type").equals("tilelayer"))
            {
                layers.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
            }
        }

    }

    public void debugDraw(Graphics2D g2d)
    {
        AffineTransform tx = new AffineTransform();
        for (TiledLayer l : layers)
        {
            if (l.isVisible())
                g2d.drawImage(l.getImage(), tx, null);
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public ArrayList<TiledTile> getTiles() {
        return tiles;
    }

    public ArrayList<TiledLayer> getLayers() {
        return layers;
    }
}