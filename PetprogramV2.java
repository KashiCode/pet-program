import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Pet Game!");
        String[] pet = createNewPet(scanner);
        playGame(pet, scanner);

        scanner.close();
    }

    public static String[] createNewPet(Scanner scanner) {
        // {name, type, hunger, happiness, health, alive}
        String[] pet = new String[6];

        System.out.print("Please enter your pet's name: ");
        pet[0] = scanner.nextLine();

        System.out.print("Please enter your pet's species: ");
        pet[1] = scanner.nextLine();

        pet[2] = "50";  // hunger
        pet[3] = "50";  // happiness
        pet[4] = "100"; // health
        pet[5] = "true";  // alive

        return pet;
    }

  public static void playGame(String[] pet, Scanner scanner) {
      boolean quit = false;
      while (!quit && Boolean.parseBoolean(pet[5])) {
          System.out.println("1. Feed " + pet[0]);
          System.out.println("2. Play with " + pet[0]);
          System.out.println("3. Take " + pet[0] + " to the vet");
          System.out.println("4. Quit");
          System.out.print("Enter your choice: ");

          try {
              int choice = Integer.parseInt(scanner.nextLine());

              if (choice == 1) {
                  feedPet(pet);
              } else if (choice == 2) {
                  playWithPet(pet);
              } else if (choice == 3) {
                  takeToVet(pet);
              } else if (choice == 4) {
                  quit = true;
                  System.out.println("Thanks for Playing!");
              } else {
                  System.out.println("Invalid choice.");
              }

              petStatus(pet);

          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a number.");
          }
      }
  }

    public static void feedPet(String[] pet) {
        int newHunger = Math.min(100, Integer.parseInt(pet[2]) + 10);
        int newHappiness = Math.min(100, Integer.parseInt(pet[3]) + 5);
        pet[2] = String.valueOf(newHunger);
        pet[3] = String.valueOf(newHappiness);
        System.out.println(pet[0] + " was fed and is now full and happy!");
    }

    public static void playWithPet(String[] pet) {
        int newHunger = Math.max(0, Integer.parseInt(pet[2]) - 10);
        int newHappiness = Math.min(100, Integer.parseInt(pet[3]) + 5);
        pet[2] = String.valueOf(newHunger);
        pet[3] = String.valueOf(newHappiness);
        System.out.println("You played with " + pet[0] + "!");
    }

    public static void takeToVet(String[] pet) {
        int newHealth = Math.min(100, Integer.parseInt(pet[4]) + 10);
        int newHappiness = Math.min(100, Integer.parseInt(pet[3]) + 5);
        pet[4] = String.valueOf(newHealth);
        pet[3] = String.valueOf(newHappiness);
        System.out.println("You took " + pet[0] + " to the vet. Health and happiness increased!");
    }

      public static void petStatus(String[] pet) {
          int hunger = Integer.parseInt(pet[2]);
          int happiness = Integer.parseInt(pet[3]);
          int health = Integer.parseInt(pet[4]);

          if (hunger <= 0 || happiness <= 0 || health <= 0) {
              System.out.println(pet[0] + " has died");
              pet[5] = "false";
          }
      }
  }