package com.josephvanderzwart.antworld.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WorldMap {
    private HashMap<Integer, Entity> worldMap;
    //WORLD SIZE DEFAULT:
    private final int size = 100;
    private Random rand = new Random();

    public WorldMap()
    {
        worldMap = new HashMap<Integer, Entity>();
    }

    public Entity getEntityAt(int coord)
    {
        if (worldMap.containsKey(coord))
        {
            return worldMap.get(coord);
        }
        else return null;
    }

    public void updateEntityAt(int coord, Entity e)
    {
        worldMap.put(coord, e);
    }

    public boolean removeFromWorld(int coord)
    {
        if (worldMap.containsKey(coord))
        {
            worldMap.remove(coord);
            return true;
        }
        else return false;
    }

    public int addToWorld(Entity e)
    {
        int randomLocation;
        boolean success = false;
        int i = 0;
        do
        {
            randomLocation = rand.nextInt(size);
            if (!worldMap.containsKey(randomLocation))
            {
                e.setLoc(randomLocation);
                worldMap.put(randomLocation, e);
                success = true;
            }
            i++;
            if (i > size)
            {
                break;
            }
        }
        while (!success);
        return randomLocation;
    }

    public boolean addToWorld(Entity e, int coord)
    {
        if (!worldMap.containsKey(coord))
        {
            e.setLoc(coord);
            worldMap.put(coord, e);
            return true;
        }
        return false;
    }

    public void growEveryone()
    {
        for (int i : worldMap.keySet())
        {
            Entity e = worldMap.get(i);
            e.grow();
            this.updateEntityAt(i, e);
        }
    }

    public List getList()
    {
        List<Entity> out = new ArrayList<>();
        for (Entity e : worldMap.values())
        {
            out.add(e);
        }
        return out;
    }
}
