import java.util.Random;

public class SpreadBot extends Robot
{
    static Random random = new Random();

    //Constructor for setting up values
    public SpreadBot(int productionNo, boolean isRedTeam)
    {
        super(random.nextDouble(2, 3), random.nextDouble(0.5, 1), random.nextDouble(0.5, 1.5), "K" + productionNo, isRedTeam);
    }

    //Implementation of attack method
    @Override
    public void attack(Simulation s)
    {
        Robot[] target = s.getLowestSpeed3(!isRedTeam());
        System.out.println(getName() + " attacks the following targets: ");
        for(int i = 0; i < target.length; i++)
            System.out.print(target[i].getName() + " ");

        System.out.println();

        for(int i = 0; i < target.length; i++)
        {
            double damage = getAttack();
            double curHealth = target[i].getHealth() -damage;
            if(curHealth < 0)   
                curHealth = 0;
    
            System.out.printf( "%s receives %.3f damage -> remaining health: %.3f %n",target[i].getName(), damage, curHealth);

            if (target[i].getHitAndIsDestroyed(damage)) 
            {
                System.out.println(target[i].getName() + " destroyed.");
                s.removeRobot(target[i]);
            }
        }
    }
}
