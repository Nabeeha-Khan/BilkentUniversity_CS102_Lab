public abstract class Robot 
{
    //Variables
    private double health, attack, speed;
    private String name;
    private boolean isRedTeam;

    //Contructor to set values
    public Robot(double health, double attack, double speed, String name, boolean isRedTeam)
    {
        this.health = health;
        this.attack = attack;
        this.speed = speed;
        this.name = name;
        this.isRedTeam = isRedTeam;
    }

    //Abstract class
    public abstract void attack(Simulation s);

    //Checks if health is less than or equal to zero after hit (as it gets destroyed)
    public boolean getHitAndIsDestroyed(double damage)
    {
        this.health -= damage;
        return this.health <= 0;
    }

    //Returns health of bot
    public double getHealth()
    {
        return this.health;
    }

    //Returns attack of bot
    public double getAttack()
    {
        return this.attack;
    }

    //Returns speed of bot
    public double getSpeed()
    {
        return this.speed;
    }

    //Returns name of bot
    public String getName()
    {
        return this.name;
    }

    //Returns if team is red or not
    public boolean isRedTeam()
    {
        return this.isRedTeam;
    }
}