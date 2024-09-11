public class Main 
{
    public static void main(String[] args) 
    {
        int teamSize = 5; 
        System.out.println("Team Size: " + teamSize + "\r\n");
        Simulation simulation = new Simulation(teamSize);

        simulation.simulate();
    }
}
