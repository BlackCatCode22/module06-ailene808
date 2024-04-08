import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    static String calcBirth(int yearsOld, String birthSeason) {
        //Created a variable to the current year subtracted by yearsOld newDate day of birth
        int year = 2024 - yearsOld;
        String monthDay;
        String newDate;
        monthDay = switch (birthSeason) {
            case "spring," -> "03-21";
            case "summer," -> "06-21";
            case "fall," -> "09-21";
            case "winter," -> "12-21";
            default -> "01-01";
        };
        // assigned variable newDate to a string of year-monthDay
        newDate = year + "-" + monthDay;
        return newDate;
    }

    // created method to generate unique id for each animal
    static String genUniqueID(String speciesName, int numOfSpecies) {
        return switch (speciesName) {
            case "hyena" -> "Hy0" + (numOfSpecies);
            case "lion" -> "Li0" + (numOfSpecies);
            case "tiger" -> "Ti0" + (numOfSpecies);
            case "bear" -> "Be0" + (numOfSpecies);
            default -> "error";
        };
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\n\nWelcome to Ailene's zoo! \n\nHere is some information about the animals at the zoo.\n\n");
// Creates the variables used for the 4 different species
        int numOfHyenas = 0;
        int numOfLions = 0;
        int numOfTigers = 0;
        int numOfBears = 0;
// This code is to build 4 animals of each species and an array with 16 elements
        String[] hyenas = new String[4];
        String[] lions = new String[4];
        String[] tigers = new String[4];
        String[] bears = new String[4];
        String[] arrivingAnimals = new String[16];

// Use Scanner class to open the arrivingAnimals.txt file.
        {
        File myAnimalsFile = new
        File("C:\\2024_Spring\\JAVA\\Module06-Midterm\\src\\arrivingAnimals.txt");
        Scanner scanner = new Scanner(myAnimalsFile);

// Created variable animalNum to use in while loop
        int animalNum = 0;
        while (scanner.hasNextLine()) {
        String animal = scanner.nextLine();
        arrivingAnimals[animalNum] = animal;
        animalNum++;
            }
        }

// Variables needed to process the array.
        int position;
        String gender;
        String species;
        String birthdate;
        String color;
        String origin;
        String weight;
        String name;
        String uniqueID = "";


        String[] hyenaNames = {" ", "Shenzi", "Banzai", "Ed", "Zig", "Bud", "Lou",
                "Kamari", "Wema", "Nne", "Madoa", "Prince Nevarah"};
        String[] lionNames = {" ", "Scar", "Mufasa", "Simba", "Kiara", "King",
                "Drooper", "Kimba", "Nala", "Leo", "Samson", "Elsa", "Cecil"};
        String[] bearNames = {" ", "Yogi", "Smokey", "Paddington", "Lippy", "Bungle",
                "Baloo", "Rupert", "Winnie the Pooh", "Snuggles", "Bert"};
        String[] tigerNames = {" ", "Tony", "Tigger", "Amber", "Cosimia", "Cuddles",
                "Dave", "Jiba", "Rajah", "Rayas", "Ryker"};

// for loop to iterate the names arrays
        for (int i = 0; i < 16; i++) {

//  This code is splitting the string array into words
            String[] splitStr = arrivingAnimals[i].split(" ", 0);

// We use two elements from our split to calculate a birthday - years (an
            // int) and season (a String)
// Calculate birthday using years old and birth season
            birthdate = calcBirth(Integer.parseInt(splitStr[0]), splitStr[7]);
            gender = splitStr[3];
            species = splitStr[4];
            position = species.indexOf(",");
            // substring() arguments: beginning index, ending index-1
            species = species.substring(0, position);

// if else statement logic to get species and increment it by 1 as well as assign a unique id
            switch (species) {
                case "hyena" -> {
                    numOfHyenas++;
                    uniqueID = genUniqueID(species, numOfHyenas);
                }
                case "lion" -> {
                    numOfLions++;
                    uniqueID = genUniqueID(species, numOfLions);
                }
                case "tiger" -> {
                    numOfTigers++;
                    uniqueID = genUniqueID(species, numOfTigers);
                }
                case "bear" -> {
                    numOfBears++;
                    uniqueID = genUniqueID(species, numOfBears);
                }
            }

// created string array and using split method to split it by comma
            String[] splitStrComma = arrivingAnimals[i].split(",", 0);

// assigning variable to splitStrComma and getting weight, color and origin
            color = splitStrComma[2];
            weight = splitStrComma[3];
            origin = splitStrComma[4] + "," + splitStrComma[5];

// Use uniqueID
            String uniqueIDPrefix = uniqueID.substring(0, 2);
            String uniqueIDSuffix = uniqueID.substring(2);
            int suffixAsInt = Integer.parseInt(uniqueIDSuffix);

// Use a switch statement to check for unique id prefix and use it to assign a name
            name = switch (uniqueIDPrefix) {
                case "Hy" -> hyenaNames[suffixAsInt];
                case "Li" -> lionNames[suffixAsInt];
                case "Ti" -> tigerNames[suffixAsInt];
                case "Be" -> bearNames[suffixAsInt];
                default -> "error in name switch statement";
            };

// declared string variable arrivalDate but didnt assign
            String arrivalDate;
// used LocalDate to get the current date and arrivalDate and assigned to today's date
            LocalDate currentDate = LocalDate.now();
            arrivalDate = currentDate.toString();
            LocalDate bDate = LocalDate.parse(birthdate);

// Create int variable animalAge and used between() method to take in the birthdate and current date and math the maths
            int animalAge = Period.between(bDate, currentDate).getYears();
            String outputLine;
            outputLine = uniqueID + "; " +
                    name + "; " +
                    animalAge + " years old" + "; " +
                    "birth date " + birthdate + ";" +
                    color + "; " +
                    gender + ";" +
                    weight + ";" +
                    origin + ";" +
                    " arrived " + arrivalDate;

//Creates an animal into its species array
            switch (species) {
                case "hyena" -> hyenas[numOfHyenas - 1] = outputLine;
                case "lion" -> lions[numOfLions - 1] = outputLine;
                case "tiger" -> tigers[numOfTigers - 1] = outputLine;
                case "bear" -> bears[numOfBears - 1] = outputLine;
            }
        }
        // This code is the output of the arrays with a description of what is in each habitat
        System.out.println("\nIn the hyena habitat, we have 4 hyenas:\n");
        for (int i = 0; i < 4; i++) {
            System.out.println(hyenas[i]);
        }
        System.out.println("\nIn the lion habitat, we have 4 lions:\n");
        for (int i = 0; i < 4; i++) {
            System.out.println(lions[i]);
        }
        System.out.println("\nIn the tiger habitat, we have 4 tigers:\n");
        for (int i = 0; i < 4; i++) {
            System.out.println(tigers[i]);
        }
        System.out.println("\nIn the bear habitat, we have 4 bears:\n");
        for (int i = 0; i < 4; i++) {
            System.out.println(bears[i]);
        }
        // try catch block to write to output file
        try {
// Create a FileWriter
            FileWriter file = new
                    FileWriter("C:\\2024_Spring\\JAVA\\Module06-Midterm\\src\\zooPopulation.txt");
// Create a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);
// Replace the System.out.println() statements with output.write()
            output.write("Hyena Habitat:\n\n");
            for (int i = 0; i < 4; i++) {
                output.write(hyenas[i] + "\n");
            }
            output.write("\nLion Habitat:\n\n");
            for (int i = 0; i < 4; i++) {
                output.write(lions[i] + "\n");
            }
            output.write("\nTiger Habitat:\n\n");
            for (int i = 0; i < 4; i++) {
                output.write(tigers[i] + "\n");
            }
            output.write("\nBear Habitat:\n\n");
            for (int i = 0; i < 4; i++) {
                output.write(bears[i] + "\n");
            }
           } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
