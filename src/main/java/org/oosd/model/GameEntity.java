package org.oosd.model;

public abstract class GameEntity {
    private double x, y;
    private double dx, dy;
    private double size;
    protected long birthTime;
    protected long previousTime;

    protected int life; //-1 if eternal life

    public GameEntity(){
        birthTime = System.nanoTime();
        previousTime = birthTime;
        life = -1;
    }

    private int getRemainLife(){
        if (life == -1) return -1;
        int pastLife = (int) ((System.nanoTime() - birthTime) / 1_000_000_000);
        int remainLife = life - pastLife;
        return Math.max(remainLife, 0);
    }

    boolean isBounce(){return true; }

    void processMove() {
        long currentTime = System.nanoTime();
        if (dx == 0.0 && dy == 0.0) {
            previousTime = currentTime;
            return;
        }
        double steps = (currentTime - previousTime) / 2_000_000_0.0; // so one second has 50 (step) frames
        double mx = steps * dx, my = steps * dy;
        if (isBounce()) {
            if (x + mx < size || x + mx > Game.fieldWidth - size) {
                mx = -mx;
                dx = -dx;
            }
            if (y + my < size || y + my > Game.fieldHeight - size) {
                my = -my;
                dy = -dy;
            }
        }
        x += mx;
        y += my;
        previousTime = currentTime;
    }

    boolean isVisible() {
        if (x < -size || y < -size || x > Game.fieldWidth + size || y > Game.fieldHeight + size) return false;
        return true;
    }

    public boolean isDead() {
        if (getRemainLife() == 0) return true;
        return !isVisible();
    }

    boolean isCollide (GameEntity target) {
        double distance = Math.sqrt(Math.pow(target.x - x, 2) + Math.pow(target.y - y, 2));
        return distance <= target.size + size;
    }

    abstract void process();
    abstract public EntityType getType();

    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}

    public double getDX() {return dx;}
    public void setDX(double dx) {this.dx = dx;}
    public double getDY() {return dy;}
    public void setDY(double dy) {this.dy = dy;}

    public double getSize() {return size;}
    public void setSize(double size) {this.size = size;}
}
