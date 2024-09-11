import java.util.Random;

public class DefenceBot extends Robot
{
    static Random random = new Random();

    //Constructor for setting up values
    public DefenceBot(int productionNo, boolean isRedTeam)
    {
        super(random.nextDouble(3, 6), random.nextDouble(0.5, 1), random.nextDouble(0.5, 1), "D" + productionNo, isRedTeam);
    }

    //Implementation of attack method
    @Override
    public void attack(Simulation s)
    {
        Robot target = s.getLowestSpeed(!isRedTeam());
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
