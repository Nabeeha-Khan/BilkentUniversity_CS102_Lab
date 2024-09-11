import java.util.Random;

public class OneBot extends Robot
{
    static Random random = new Random();

    //Constructor for setting up values
    public OneBot(int productionNo, boolean isRedTeam)
    {
        super(random.nextDouble(0.5, 1), random.nextDouble(4, 5), random.nextDouble(0.5, 1), "O" + productionNo, isRedTeam);
    }

    //Implementation of attack method
    @Override
    public void attack(Simulation s)
    {
        Robot target = s.getLowestHealth(!isRedTeam());
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
