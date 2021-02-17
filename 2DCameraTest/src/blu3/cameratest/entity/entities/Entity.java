package blu3.cameratest.entity.entities;

public class Entity {

    private final String name;
    private final int defaultHealth;
    private int health;
    private boolean isAlive, isInvulnerable;
    private float posX, posY, motionX, motionY;

    //-----------------------------------------------------------------------
    // This is the constructor class for all entities. 
    // A few things here will only be used a few times, but still need them
    // nonetheless. While this may seem easy to cheat, I'll get good stuff 
    // going server-side whenever I get that running.
    //
    // Most methods here are fairly self-explanatory, hopefully being named
    // well enough to be easily understood at first glance.
    //
    //-----------------------------------------------------------------------
    public Entity(String name, int defaultHealth, float startPosX, float startPosY) {
        
        if (name == null || defaultHealth == null || startPosX == null || startPosY == null) {
            RuntimeException up = new RuntimeException("Entity parameters cannot be null");
            throw up;
        }
        
        this.name = name;
        this.defaultHealth = defaultHealth;
        this.posX = startPosX;
        this.posY = startPosY;
        this.isAlive = true;
        this.isInvulnerable = false;
    }
    
    public void tick() {
        // This must be overridden for every entity other than the player. How would you give it an AI otherwise?
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
    
    public boolean isInvulnerable() {
        return this.isInvulnerable;
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
    
    public void setInvulnerable(boolean invulnerable) {
        this.isInvulnerable = invulnerable;
    }
}
