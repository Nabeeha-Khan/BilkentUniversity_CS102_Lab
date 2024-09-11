import java.util.ArrayList;
import java.util.Random;

public class Simulation 
{
    private ArrayList<Robot> redTeam;
    private ArrayList<Robot> blueTeam;

    //Constructor that initializes Array Lists
    public Simulation(int teamSize) 
    {
        redTeam = createTeam(teamSize, true);
        blueTeam = createTeam(teamSize, false);
    }

    //Creates team
    public ArrayList<Robot> createTeam(int teamSize, boolean isRedTeam) 
    {
        ArrayList<Robot> team = new ArrayList<>();
        Random random = new Random();

        //For assigning production number
        int productionNumber;
        for (int i = 0; i < teamSize; i++) 
        {
            if(isRedTeam)
                productionNumber = i;
            else 
                productionNumber = teamSize + i;

            //For assigning robot type
            int robotType = random.nextInt(6); // Number of robot types
            Robot robot;
            if(robotType == 0)
                robot = new SimpleBot(productionNumber, isRedTeam);
            else if(robotType == 1)
                robot = new PredatorBot(productionNumber, isRedTeam);
            else if(robotType == 2)
                robot = new DefenceBot(productionNumber, isRedTeam);
            else if(robotType == 3)
                robot = new SpeedBot(productionNumber, isRedTeam);
            else if(robotType == 4)
                robot = new SpreadBot(productionNumber, isRedTeam);
            else 
                robot = new OneBot(productionNumber, isRedTeam);

            team.add(robot);
        }

        //Bubble sorting based on highest to lowest speed
        for (int i = 0; i < team.size() - 1; i++) 
        {
            for (int j = 0; j < team.size() - i - 1; j++) 
            {
                Robot robot1 = team.get(j);
                Robot robot2 = team.get(j + 1);
                if (robot1.getSpeed() < robot2.getSpeed()) 
                {
                    // Swap robot1 and robot2
                    team.set(j, robot2);
                    team.set(j + 1, robot1);
                }
            }
        }

        return team;
    }

    //Displaying elements of each bot in team
    public void displayTeams(ArrayList<Robot> team)
    {
        for(Robot bot: team)
        {
            System.out.printf("%s Health: %.3f Attack: %.3f Speed: %.3f", bot.getName(), bot.getHealth(), bot.getAttack(), bot.getSpeed());
            System.out.println();
        } 

        System.out.println();
    }

    //Simulates the game
    public void simulate() 
    {
        //Display prompt
        System.out.println("Red Team: ");
        displayTeams(redTeam);
        System.out.println("Blue Team:");
        displayTeams(blueTeam);

        //Calculates and prints speed
        double redSpeedSum = calculateSpeedSum(redTeam);
        double blueSpeedSum = calculateSpeedSum(blueTeam);
        System.out.printf("Speed sum of Red: %.3f %n", redSpeedSum);
        System.out.printf("Speed sum of Blue: %.3f %n", blueSpeedSum);

        //Sets order based on sum of speed
        ArrayList<Robot> first, second;
        if (redSpeedSum >= blueSpeedSum) 
        {
            first = redTeam;
            second = blueTeam;
            System.out.println("Red starts first.");
        }
        else 
        {
            first = blueTeam;
            second = redTeam;
            System.out.println("Blue starts first.");
        }
        System.out.println();

        //Simulates the game until any one of the two team is empty
        int firstIndex = 0, secondIndex = 0;
        while (!first.isEmpty() && !second.isEmpty()) 
        {
            //To check if index is within range
            if(firstIndex >= first.size())
                firstIndex = firstIndex % first.size();

            first.get(firstIndex).attack(this);
            firstIndex++;

            if(!second.isEmpty())
            {
                //To check if index is within range
                if(secondIndex >= second.size())    
                    secondIndex = secondIndex % second.size();

                second.get(secondIndex).attack(this);
                secondIndex++;
            }
        }
        System.out.println();
        
        //Display winning team
        ArrayList<Robot> winningTeam;
        if(first.isEmpty())
            winningTeam = second;
        else
            winningTeam = first;

        String winningTeamName;
        if(winningTeam.get(0).isRedTeam()) 
            winningTeamName = "Red";
        else
            winningTeamName = "Blue";

        System.out.println(winningTeamName + " team wins, remaining robots: ");
        displayTeams(winningTeam);
    }

    //Calculates sum of speed of bots in a team
    public double calculateSpeedSum(ArrayList<Robot> team) 
    {
        double sum = 0;
        for (Robot robot : team) {
            sum += robot.getSpeed();
        }
        return sum;
    }

    //Removes bot from team
    public void removeRobot(Robot robot) 
    {
        if (robot.isRedTeam()) 
        {
            redTeam.remove(robot);
        }
        else
        {
            blueTeam.remove(robot);
        }
    }

    //To select a target randomly
    public Robot getRandomTarget(boolean isRedTeam)
    {
        ArrayList<Robot> team;
        if(isRedTeam)  
            team = redTeam;
        else
            team = blueTeam;

        Random random = new Random();
        return team.get(random.nextInt(team.size()));
    }

    //To select a target with highest health
    public Robot getHighestHealth(boolean isRedTeam)
    {
        ArrayList<Robot> team;
        if(isRedTeam)  
            team = redTeam;
        else
            team = blueTeam;

        Robot largest = team.get(0);
        for(int i = 1; i < team.size(); i++)
        {
            if(largest.getHealth() < team.get(i).getHealth())
                largest = team.get(i);
        }

        return largest;
    }

    //To select a target with lowest health 
    public Robot getLowestHealth(boolean isRedTeam)
    {
        ArrayList<Robot> team;
        if(isRedTeam)  
            team = redTeam;
        else
            team = blueTeam;

        Robot lowest = team.get(0);
        for(int i = 1; i < team.size(); i++)
        {
            if(lowest.getHealth() > team.get(i).getHealth())
                lowest = team.get(i);
        }

        return lowest;
    }

    //To select a target with lowest speed
    public Robot getLowestSpeed(boolean isRedTeam)
    {
        ArrayList<Robot> team;
        if(isRedTeam)  
            team = redTeam;
        else
            team = blueTeam;

        Robot lowest = team.get(0);
        for(int i = 1; i < team.size(); i++)
        {
            if(lowest.getSpeed() > team.get(i).getSpeed())
                lowest = team.get(i);
        }

        return lowest;
    }

    //To select a target with lowest attack
    public Robot getLowestAttack(boolean isRedTeam)
    {
        ArrayList<Robot> team;
        if(isRedTeam)  
            team = redTeam;
        else
            team = blueTeam;

        Robot lowest = team.get(0);
        for(int i = 1; i < team.size(); i++)
        {
            if(lowest.getAttack() > team.get(i).getAttack())
                lowest = team.get(i);
        }

        return lowest;
    }

    //To select three (or less) targets with lowest speed
    public Robot[] getLowestSpeed3(boolean isRedTeam)
    {
        ArrayList<Robot> team  = new ArrayList<>();
        if(isRedTeam)
            for(Robot bot: redTeam) 
                team.add(bot);
        else
            for(Robot bot: redTeam) 
                team.add(bot);

        Robot[] lowest3;
        if (team.size() >= 3) 
        {
            lowest3 = new Robot[3];
            for (int i = 0; i < lowest3.length; i++) 
            {
                Robot lowest = team.get(0);
                for (int j = 1; j < team.size(); j++) 
                {
                    if (lowest.getSpeed() > team.get(j).getSpeed()) 
                    {
                        lowest = team.get(j);
                    }
                }
                lowest3[i] = lowest;
                team.remove(lowest);
            }
        }
        else 
        {
            lowest3 = new Robot[team.size()];
            for (int i = 0; i < team.size(); i++) 
            {
                lowest3[i] = team.get(i);
            }
        }
    
        return lowest3;
    }

}
