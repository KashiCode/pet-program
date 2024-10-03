import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Calls the PetGame method at the start of the program. 
        PetGame();

    }

     //Main game method. Called by Main args statement. 
    public static void PetGame(){

        //Creates a new scanner object and asks the user input their pet name.
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pet Game!");

        System.out.print("Please enter your pet's name: ");
        String petName = scanner.nextLine();

        //Sets the pets stats of hunger and happiness alongside whether its alive. 
        int hunger = 50;
        int happiness = 50;
        boolean alive = true;

        //sets value of quit to false.
        boolean quit = false;
        //Defines a while loop that runs along as the user doesnt quit and the pet is alive. 
        while (!quit && alive) {

            //Outputs a simple menu of options to the user of how they can interact with their pet. 
            System.out.println("1. Feed " + petName);
            System.out.println("2. Play with " + petName);
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            //Accepts the user input.
            int choice = scanner.nextInt();
            scanner.nextLine();

            //Based on the choices made above the if else statements evaluate what procedure to follow. For feeding the pet hunger is decreased etc
            if (choice == 1) {
                hunger += 10;
                happiness += 5;
                System.out.println(petName + " was fed and is now full and happy!");
            } else if (choice == 2) {
                hunger -= 10;
                happiness += 5;
                System.out.println("You played with " + petName + "!");
            } else if (choice == 3) {
                quit = true;
                System.out.println("Thanks for Playing!");
            } else {
                System.out.println("Invalid choice.");
            }

            // Triggers at the end of each round to ensure the pet hasnt died. Calls the PetStatus function. 
            alive = PetStatus(hunger, happiness, petName);
        }

        //Closes the scanner object. 
        scanner.close();
    }
//END OF PETGAME

    // Checks if the pet is still alive. 
    public static boolean PetStatus(int hunger, int happiness, String petName) {
        //If the hunger or happiness is zero then the pet has died. It outputs this to the user. 
        if (hunger <= 0 || happiness <= 0) {
            System.out.println(petName + " has died :(");
            //returns a boolean value of false or true to assign to "alive" variable. 
            return false;
        }
        return true;
    }
}

//END OF PETSTATUS