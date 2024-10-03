import java.io.*; //imports all features from java.io (including serializable).
import java.util.Scanner; //Imports the scanner functionality.


//Creates a Record  class named Pet. The class implements serializable in order for it to be saved to a file and loaded.
class Pet implements Serializable {
  // The Pet class implements the Serializable interface, which allows its attributes to be serialized.
    private static final long serialVersionUID = 1L;

    //Defines variables to store the pet attributes.
    String name;
    int hunger;
    int health;
    int happiness;
    String type;
    boolean alive;
}
//END Pet Record.

//Starting point of the program.
public class Main {
    public static void main(String[] args) {

      //Creates a new scanner object and sets the boolean value of quit to false. 
      Scanner scanner = new Scanner(System.in);
          boolean quit = false;


          //Defines a while loop that loops a main menu for the user to interact with aslong as quit is not selected. 
          while (!quit) {
              System.out.println("------------------------------------------------------------------------------");
              System.out.println("Main Menu:");
              System.out.println("1. Play the game");
              System.out.println("2. Instructions");
              System.out.println("3. Current Pet Information");
              System.out.println("4. Quit");
              System.out.println("------------------------------------------------------------------------------");

              System.out.print("Enter your choice: ");
              int choice = scanner.nextInt();
              scanner.nextLine();


            //Uses decision statements (IF ELSE) to find which option the user has selected. 
              if (choice == 1) {
                //Calls the petprogram and passes the scanner as a variable.
                  PetProgram(scanner);
              } else if (choice == 2) {
                //Calls a function to create an instruction array and another to display the created array. 
                String[] instructionsArray = createInstructions();
                Instructions(instructionsArray);
              } else if (choice == 3){
                //Calls the PetInfo function. 
                PetInfo();
              } else if (choice == 4) {
                //Sets the boolean value to true ending the game. 
                  quit = true;
                  System.out.println("Thanks for Playing!");
              } else {
                  System.out.println("Invalid choice.");
              }
          }
          //closes the scanner 
          scanner.close();
      }
  //END MAIN


      // Defines a method that returns a String array that contains instructions for the program.  
      public static String[] createInstructions() {
        //Creates a new array and defines its contents.  
          return new String[] {
              "1. Your pet starts with predetermined attributes: Hunger, Health, and Happiness",
              "2. These attributes will naturally decrease each round",
              "3. Feeding your pet will Boost Hunger and Happiness.",
              "4. Playing with your pet will increase Hunger and increase Happiness",
              "5. Taking your pet to the Vet will increases your pet's Health.",
              "6. If the Health or Happiness of your pet reach 0, the game ends.",
              "7. Quitting the game will Save and exit the game so you can load it at another time."
          };
      }
  //END createInstructions


      //Method called instructions which accepts an array as an argument. 
      public static void Instructions(String[] instructions) {
          System.out.println("------------------------------------------------------------------------------");

          //Uses a for loop to cycle through the array contents and output them to the user. 
          for (String instruction : instructions) {
              System.out.println(instruction);
          }

      }
  //END Instructions



  //Creates a method called PetProgram that accepts the scanner as a argument. 
  public static void PetProgram(Scanner scanner){

      System.out.println("------------------------------------------------------------------------------");
      System.out.println("Welcome to the Pet Game!");

    //Asks the user if they want to load an already existing save file or create a new game instance. 
      System.out.print("Do you want to load a save file? (Y/N): ");
      String loadChoice = scanner.nextLine().toUpperCase();
      System.out.println("------------------------------------------------------------------------------");

      Pet pet;

    //Loads the game save if the user response is Y. 
      if (loadChoice.equals("Y")) {
          pet = loadGame("game_data.txt");
         //Checks if the pet is alive or doesnt exist.
          if (pet == null || !pet.alive) {
            //If the pet doesnt exist a new game is started. 
              System.out.println("No save data found or the pet is not alive. Starting a new game.");
              pet = createNewPet(scanner);
          } else {
            //Otherwise the user is welcomed back to the game. 
              System.out.println("Welcome back to the Pet Game!");
          }
      } else {
        //If the user response is N then a new game is started. 
          pet = createNewPet(scanner);
      }

    //Defines a while loop that runs while the pet health is above 0. 
      while (pet.health > 0) {
        //Calls the printPet status function. 
          printPetStatus(pet);

        //Calls teh decrease attributes function.
          decreaseAttributes(pet);
        //Prints a menu out to the user so they can interact with the pet. 
        System.out.println("1. Feed " + pet.name);
        System.out.println("2. Play with " + pet.name);
        System.out.println("3. Take " + " " + pet.name + " to the Vet");
        System.out.println("4. Do nothing");
        System.out.println("5. Quit");
        System.out.println("------------------------------------------------------------------------------");


          //Choices stores the user selection and valid input checks if the input is valid. 
          int choice = 0;
          boolean validInput = false;

        // While Loop to validate player's input which continues while the input is not valid. 
          while (!validInput) {
              System.out.print("Enter your choice: ");
              if (scanner.hasNextInt()) {
                //Reads the player input. If its true it sets valid input to true and the loop ends.
                  choice = scanner.nextInt();
                  if (choice >= 1 && choice <= 5) {
                      validInput = true;
                  } else {
                    //If there is an invalid input it will prompt the user to re-enter the number.
                      System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                  }
              } else {
                  System.out.println("Invalid input. Please enter a number.");
                  scanner.next();
              }
          }
          scanner.nextLine();
          System.out.println("------------------------------------------------------------------------------");


        //Uses decision statements to call the functions that manipulate the pet statistics. 
          if (choice == 1) {
              feedPet(pet);
          } else if (choice == 2) {
              PlayPet(pet);
          } else if (choice == 3) {
              VetPet(pet);
          } else if (choice == 4) {
              pet.hunger -= 5;
              pet.health -= 5;
              pet.happiness -= 5;
          } else if (choice == 5) {
            // Exits the game loop and saves the game if the quit option is selected. 
              System.out.println("Thanks for Playing");
              saveGame(pet, "game_data.txt");
              System.out.println("Game saved");
              break;
          } else {
              System.out.println("Invalid choice");
          }

          // Automatically saves the game at the end of each round.
          saveGame(pet, "game_data.txt");

        ////Checks if the pets hunger is below or equal to 0 and outputs the relevant game over message. 
          if (pet.health <= 0) {
              System.out.println("GAME OVER - " + pet.name + " the " + pet.type + " has passed away.");
              pet.alive = false;
              saveGame(pet, "game_data.txt");
            //Checks if the pets happiness is below or equal to 0 and outputs the relevant game over message. 
          } else if (pet.happiness <= 0) {
              System.out.println(pet.name + " is too sad and has run away.");
              pet.alive = false;
              saveGame(pet, "game_data.txt");
          }
      }

      // Automatically save the game at the end of the game.
      saveGame(pet, "game_data.txt");

  }
//END PET PROGRAM

    //Creates a new method called PrintPetStatus that takes the pet record as a argument. 
    public static void printPetStatus(Pet pet) {
      //Outputs all the statistics of the pets to the user by getting them from the record. 
      System.out.println("------------------------------------------------------------------------------");
        System.out.println(pet.name + " | Hunger: " + getHunger(pet.hunger) + " | Happiness: " + getHappiness(pet.happiness) + " | Health: " + getHealth(pet.health) + " |");
      System.out.println("------------------------------------------------------------------------------");

    }

//END PRINTPETSTATUS

  //Creates a method called decreaseAttributes.
    public static void decreaseAttributes(Pet pet) {
      //Checks if hunger is above or equal to 20, if so it will decrease the health by a greater value to mimic starvation.
    if (pet.hunger <= 20) {
        pet.hunger -= 5;
        pet.health -= 10;
        pet.happiness -= 5;
    } else {
      //If the hunger is not above 20 the health will decrease slower. 
        pet.hunger -= 5;
        pet.health -= 5;
        pet.happiness -= 5;
    }
}
//END DECREASE ATTRIBUTES


  //Defines a new method called createNewPet.
  public static Pet createNewPet(Scanner scanner) {

      // Prompts the user for the pet's name and species.
      System.out.print("Please enter your pet's name: ");
      String petName = scanner.nextLine();
      System.out.print("Please enter your pet's species: ");
      String petType = scanner.nextLine();
      System.out.println("------------------------------------------------------------------------------");

      // Creates a new pet object. (acts as a constructor method for the class)
      Pet newPet = new Pet();
      newPet.name = petName;
      newPet.hunger = 50;
      newPet.happiness = 50;
      newPet.health = 60;
      newPet.type = petType;
      newPet.alive = true;

      return newPet; //returns the pet. 
  }
//END CreateNewPet.

  //Method that saves the pet attributes currently to a file. It accepts the pet record and the file name as variables. 
    public static void saveGame(Pet pet, String fileName) {

      //Creates an object output stream which allows for the writing of data to the file. 
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
          //Writes the pet attributes to the file. 
            out.writeObject(pet);
          //Catches any potential issues while saving the game. (eg no file present etc).
        } catch (IOException e) {
          //Outputs the stack trace to the terminal for debugging purposes. 
            e.printStackTrace();
        }
    }
  //END SAVE GAME.


  //Method that loads the previous pets attributes from a file. 
    public static Pet loadGame(String fileName) {
      //Creates an object input stream which allows for the reading of file contents. 
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
          //If a pet exists the data will be returned here. 
            return (Pet) in.readObject();
        } catch (FileNotFoundException e) {
          //If a pet doesnt exist then null is returned. 
            return null;
          //This block handles other IO errors that may occur during the loading of the file. 
        } catch (IOException | ClassNotFoundException e) {
          //Outputs the stack trace to the terminal for debugging purposes.
            e.printStackTrace();
            return null;
        }
    }
  //END LOAD GAME. 

  //Method called PetInfo that is called within the main menu. 
    public static void PetInfo() {
      //Creates a new object input stream to read the contents of the file named game data. 
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game_data.txt"))) {
        //Returns the contents of the file. 
          Pet pet = (Pet) in.readObject();

        //Checks if the pet is alive (hasnt died the last time the game ended/quit). Outputs the attributes if pet is alive. 
          if (pet.alive) {
              System.out.println("------------------------------------------------------------------------------");
              System.out.println(pet.name + " | Hunger: " + getHunger(pet.hunger) + " | Happiness: " + getHappiness(pet.happiness) + " | Health: " + getHealth(pet.health) + " | Type: " + pet.type);
              System.out.println("------------------------------------------------------------------------------");
          } else {
              System.out.println("------------------------------------------------------------------------------");
            //If the pet is not alive (the previous game ended in a game over) then the message below is output. 
              System.out.println("No current pet information exists");
          }

        //If no file is found when loading the file then the message below is output. 
      } catch (FileNotFoundException e) {
          System.out.println("------------------------------------------------------------------------------");
          System.out.println("No save file exists.");
          System.out.println("------------------------------------------------------------------------------");
        //If there is any other IO errors while loading the file then the stack trace is output for debugging. 
      } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
      }
  }
  //END PetInfo


//Method called gethealth that takes health as an argument. 
    public static String getHealth(int health) {
      //Defines a new array with descriptive statuses for the health status of the animal. 
        String[] descriptions = {"excellent", "good", "fair", "poor", "very poor"};

      //Depending on the integer level of the health it assigns one of these health statuses to the animal. 
        if (health >= 50) {
            return descriptions[0];
        } else if (health >= 40) {
            return descriptions[1];
        } else if (health >= 30) {
            return descriptions[2];
        } else if (health >= 20) {
            return descriptions[3];
        } else if (health >= 10) {
            return descriptions[4];
        } else if (health < 9) {
          return descriptions[4];
        } else {
            return "Invalid health value";
        }
    }
  //END GET HEALTH

  //Method called gethunger that takes hunger as an argument.
    public static String getHunger(int hunger) {
      //Defines an array with descriptive values for hunger status for the animal. 
        String[] descriptions = {"ravenous", "famished", "hungry", "content", "full"};

      //Depending on the integer hunger value it assigns one of these hunger statuses to the animal. 
        if (hunger <= 0) {
            return descriptions[0];
        } else if (hunger <= 10) {
            return descriptions[1];
        } else if (hunger <= 20) {
            return descriptions[2];
        } else if (hunger <= 30) {
            return descriptions[3];
        } else if (hunger <= 50) {
            return descriptions[4];
        } else if (hunger > 51 ) {
            return descriptions[4];
        } else {
            return "Invalid hunger value";
        }
    }
  //END GET HUNGER

  //Method called gethappiness that takes happiness as an argument.
    public static String getHappiness(int happiness) {
      //Defines a new array with descriptive statuses to assign to the happiness level of the animal. 
        String[] descriptions = {"Very Distressed", "Depressed", "Upset", "Content", "Happy"};

      //Depending on the integer happiness level a descriptive status is assigned to the animal. 
        if (happiness <= 0) {
            return descriptions[0];
        } else if (happiness <= 10) {
            return descriptions[1];
        } else if (happiness <= 20) {
            return descriptions[2];
        } else if (happiness <= 30) {
            return descriptions[3];
        } else if (happiness <= 50) {
            return descriptions[4];
        } else if (happiness > 51) {
          return descriptions[4];
        } else {
            return "Invalid happiness value"; 
        }
    }
  //END GET HAPPINESS

  //Method called VetPet.
    public static void VetPet(Pet pet) {
      //Adds 10 health to the pets health stored in record. 
        pet.health += 10;
      //Outputs to the user that the pet has been cured and is now healthier. 
      System.out.println(pet.name + " " + "was cured and is now healthy!");
            }

  //END VETPET

  //Method called feedPet
    public static void feedPet(Pet pet) {
      //Adds 10 hunger and 10 happiness to the pet attributes. 
        pet.hunger += 10;
        pet.happiness += 5;
      //Outputs to the user that the pet has been fed. 
        System.out.println(pet.name + " " + "was fed and is now full and happy!");
    }
//END FEEDPET

  //Method called PlayPet. 
    public static void PlayPet(Pet pet) {
      //Checks if the pet health is above 10, if so then it takes away hunger and increases happiness.
        if (pet.health >= 10) {
            pet.hunger -= 10;
            pet.happiness += 5;
          //Notifies the user that they have played with the pet. 
            System.out.println("you played with" + " " + pet.name + "!");
        } else {
          //If the pet is below 10 health it is classified as too unwell to play and so must be taken to a vet first. 
            System.out.println(pet.name + " is too unwell to go and play.");
        }
    }
}
//END PLAYPET. 