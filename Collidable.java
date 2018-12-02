package sample;

/**
 * Interface for power ups and collidable elements
 */
public interface Collidable
{
    public boolean checkCollision();
    public void onCollisionDo();
    int[] randomCoord();
    boolean validateCoords();
    void spawn(int x, int y);
}
