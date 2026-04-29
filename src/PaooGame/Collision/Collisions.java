package PaooGame.Collision;

import PaooGame.Camera.Camera;
import PaooGame.Items.*;
import PaooGame.Items.Character;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;

public class Collisions {
    private RefLinks refLink;

    private static final int TILE_SIZE = 80;

    public Collisions(RefLinks refLink) {
        this.refLink = refLink;

    }

    // Check collision with solid tiles, based on predicted position
    public boolean checkTileCollision(Character h, float xOffset, float yOffset) {
        Rectangle bounds = h.GetBounds(xOffset, yOffset);
        Camera c = refLink.GetCamera();

        int leftTile = (bounds.x - c.getX()) / TILE_SIZE;
        int rightTile = (bounds.x + bounds.width - c.getX()) / TILE_SIZE;
        int topTile = (bounds.y - c.getY()) / TILE_SIZE;
        int bottomTile = (bounds.y + bounds.height - c.getY()) / TILE_SIZE;

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (refLink.GetMap().GetTile(x, y).IsSolid()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWaterCollision(Character h, float xOffset, float yOffset) {
        Rectangle bounds = h.GetBounds(xOffset, yOffset);
        Camera c = refLink.GetCamera();

        int leftTile = (bounds.x - c.getX()) / TILE_SIZE;
        int rightTile = (bounds.x + bounds.width - c.getX()) / TILE_SIZE;
        int topTile = (bounds.y - c.getY()) / TILE_SIZE;
        int bottomTile = (bounds.y + bounds.height - c.getY()) / TILE_SIZE;

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (refLink.GetMap().GetTile(x, y).IsWater()) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkVineCollision(Character h, float xOffset, float yOffset) {
        Rectangle bounds = h.GetBounds(xOffset, yOffset);
        Camera c = refLink.GetCamera();

        int leftTile = (bounds.x - c.getX()) / TILE_SIZE;
        int rightTile = (bounds.x + bounds.width - c.getX()) / TILE_SIZE;
        int topTile = (bounds.y - c.getY()) / TILE_SIZE;
        int bottomTile = (bounds.y + bounds.height - c.getY()) / TILE_SIZE;

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (refLink.GetMap().GetTile(x, y).IsVine()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWebCollision(Character h, float xOffset, float yOffset) {
        Rectangle bounds = h.GetBounds(xOffset, yOffset);
        Camera c = refLink.GetCamera();

        int leftTile = (bounds.x - c.getX()) / TILE_SIZE;
        int rightTile = (bounds.x + bounds.width - c.getX()) / TILE_SIZE;
        int topTile = (bounds.y - c.getY()) / TILE_SIZE;
        int bottomTile = (bounds.y + bounds.height - c.getY()) / TILE_SIZE;

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (refLink.GetMap().GetTile(x, y).IsWeb()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSpikeCollision(Character h, float xOffset, float yOffset) {
        Rectangle bounds = h.GetBounds(xOffset, yOffset);
        Camera c = refLink.GetCamera();

        int leftTile = (bounds.x - c.getX()) / TILE_SIZE;
        int rightTile = (bounds.x + bounds.width - c.getX()) / TILE_SIZE;
        int topTile = (bounds.y - c.getY()) / TILE_SIZE;
        int bottomTile = (bounds.y + bounds.height - c.getY()) / TILE_SIZE;

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (refLink.GetMap().GetTile(x, y).IsSpike()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLavaCollision(Character h, float xOffset, float yOffset) {
        Rectangle bounds = h.GetBounds(xOffset, yOffset);
        Camera c = refLink.GetCamera();

        int leftTile = (bounds.x - c.getX()) / TILE_SIZE;
        int rightTile = (bounds.x + bounds.width - c.getX()) / TILE_SIZE;
        int topTile = (bounds.y - c.getY()) / TILE_SIZE;
        int bottomTile = (bounds.y + bounds.height - c.getY()) / TILE_SIZE;

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (refLink.GetMap().GetTile(x, y).IsLava()) {
                    return true;
                }
            }
        }
        return false;
    }




}
