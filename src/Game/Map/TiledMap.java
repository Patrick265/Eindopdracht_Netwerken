package Game.Map;

import agenda.Act;
import agenda.Stage;

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
import java.io.IOException;
import java.util.*;

public class TiledMap extends JPanel {
    private int height;
    private int width;

    private ArrayList<TiledTile> tiles = new ArrayList<>();
    private ArrayList<TiledLayer> layers = new ArrayList<>();
    private ArrayList<TiledLayer> houseLayer = new ArrayList<>();
    private ArrayList<TiledTarget> targets = new ArrayList<>();
    private CollisionLayer collisionLayer;
    private SpawnPoint spawnPoint;
    private TiledTarget spawnTarget;


    public TiledMap(String filename) {
        JsonReader reader;
        try {
            reader = Json.createReader(getClass().getResourceAsStream(filename));
            JsonObject objectReader = (JsonObject) reader.read();
            JsonArray tilesets = objectReader.getJsonArray("tilesets");

            BufferedImage image = ImageIO.read(getClass().getResource("/terrain.png"));
            height = image.getHeight() /32;
            width = image.getWidth() / 32;

            for (int i = 0; i < tilesets.size(); i++) {
                JsonObject tileset = tilesets.getJsonObject(i);

                int tileWidth = objectReader.getInt("tilewidth");
                int tileHeight = objectReader.getInt("tileheight");
                int index = tileset.getInt("firstgid");

                while (tiles.size() < index + (height * width))
                    tiles.add(new TiledTile());

                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        tiles.get(index).tile = image.getSubimage(32 * (x / tileWidth), 32 * (y / tileHeight), 32, 32);
                        index++;
                    }
                }
            }

            JsonArray jsonLayers = objectReader.getJsonArray("layers");
            JsonArray jsontarget = null;
            for (int i = 0; i < jsonLayers.size() ; i++)
            {
                if (jsonLayers.getJsonObject(i).getString("type").equals("tilelayer"))
                {
                    if(jsonLayers.getJsonObject(i).getString("name").equals("collision"))
                        collisionLayer = new CollisionLayer(jsonLayers.getJsonObject(i), this);
                    else if(jsonLayers.getJsonObject(i).getString("name").equals("Entry") || jsonLayers.getJsonObject(i).getString("name").equals("Stages")){
                        houseLayer.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
                    }
                    else {
                        layers.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
                    }
                }
                else if (jsonLayers.getJsonObject(i).getString("type").equals("objectgroup"))
                {
                    jsontarget = jsonLayers.getJsonObject(i).getJsonArray("objects");
                }
            }

            //saves places of targets.
            for (int i = 0; i < Objects.requireNonNull(jsontarget).size() ; i++)
            {
                JsonObject targetObject = jsontarget.getJsonObject(i);
                if(targetObject.getString("type").equals("spawn")){
                    spawnPoint = new SpawnPoint(targetObject);
                    targets.add(new TiledTarget(targetObject));
                }else{
                    targets.add(new TiledTarget(targetObject));
                }
            }

            //sorts form the max value to the min value.
            Collections.sort(allActs, new Comparator<Act>() {
                @Override
                public int compare(Act o1, Act o2) {
                    return  o2.getStage().getLength() * o2.getStage().getWidth() - o1.getStage().getWidth() * o1.getStage().getLength();
                }
            });

            //sorts form the max value to the min value.
            Collections.sort(targets, new Comparator<TiledTarget>() {
                @Override
                public int compare(TiledTarget o1, TiledTarget o2) {
                    return o2.getWidth() * o2.getHeight() - o1.getWidth() * o1.getHeight();
                }
            });

            HashSet<Stage> stages = new HashSet<>();
            for(Act act : allActs){
                stages.add(act.getStage());
            }

            for(TiledTarget target : targets){
                if(target.getType().equals("spawn")){
                    spawnTarget = target;
                }
            }

            Iterator<TiledTarget> targetIterator1 = targets.iterator();
            while (targetIterator1.hasNext()){
                if (targetIterator1.next().getType().equals("spawn")){
                    targetIterator1.remove();
                }
            }

            int index = 0;
            for(Stage stage : stages){
                    targets.get(index).linkAgendaStage(stage);
                    index++;
            }

            Iterator<TiledTarget> targetIterator2 = targets.iterator();
            while (targetIterator2.hasNext()){
                if (targetIterator2.next().getStage() == null){
                    targetIterator2.remove();
                }
            }

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void debugDraw(Graphics2D g2d, AffineTransform tx) {
        for (TiledLayer l : layers) {
            if (l.isVisible())
                g2d.drawImage(l.getImage(), tx, null);
        }

        }

    public void drawHouse(Graphics2D g2d, AffineTransform te)
    {
        for(TiledLayer l : houseLayer) {
            if (l.isVisible())
                g2d.drawImage(l.getImage(), te,null);
        }
    }


    public ArrayList<CollisionTile> getCollisionTiles(){
        return collisionLayer.getCollisionTiles();
    }

    public ArrayList<TiledTarget> getTargets() {
        return targets;
    }

    public ArrayList<TiledTile> getTiledSize() {return tiles; }

    public SpawnPoint getSpawnPoint() {
        return spawnPoint;
    }

    public TiledTarget getSpawnTarget() {
        return spawnTarget;
    }
}
