package blu3.cameratest.entity.entities;

public class Entity {

    private final String name;
    private final int defaultHealth;
    private int health;
    private boolean isAlive = true;
    private float posX, posY, motionX, motionY;

    public Entity(String name, int defaultHealth, float startPosX, float startPosY) {
        this.name = name;
        this.defaultHealth = defaultHealth;
        this.posX = startPosX;
        this.posY = startPosY;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return !this.isAlive() ? 0 : this.health;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void heal(int health) {
        if (!this.isAlive()) return;
        if (this.health + health > this.defaultHealth) this.health = this.defaultHealth;
        else this.health += health;
    }

    public void damage(int damage) {
        if (!this.isAlive()) return;
        this.health -= damage;
    }

    public void kill() {
        this.damage(100000);
        this.isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public float getMotionX() {
        return motionX;
    }

    public float getMotionY() {
        return motionY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setMotionX(float motionX) {
        this.motionX = motionX;
    }

    public void setMotionY(float motionY) {
        this.motionY = motionY;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
}
