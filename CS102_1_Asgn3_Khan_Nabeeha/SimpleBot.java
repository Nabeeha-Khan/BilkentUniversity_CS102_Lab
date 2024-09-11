import java.util.Random;

public class SimpleBot extends Robot
{
    static Random random = new Random();

    //Constructor for setting up values
    public SimpleBot(int productionNo, boolean isRedTeam)
    {
        super(random.nextDouble(2, 3), random.nextDouble(1, 2), random.nextDouble(1, 2), "S" + productionNo, isRedTeam);
    }

    //Implementation of attack method
    @Override
    public void attack(Simulation s)
    {
        Robot target = s.getRandomTarget(!isRedTeam());
        System.out.println(getName() + " attacks " + target.getName());
        double damage = getAttack();
        double curHealth = target.getHealth() -damage;
        if(curHealth < 0)   
            curHealth = 0;

        System.out.printf( "%s receives %.3f damage -> remaining health: %.3f %n",target.getName(), damage, curHealth);
        
        if (target.getHitAndIsDestroyed(damage)) 
        {
            System.out.println(target.getName() + " destroyed.");
            s.removeRobot(target);
        }
    }
}
