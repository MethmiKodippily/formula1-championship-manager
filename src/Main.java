import java.io.*;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Formula1ChampionshipManager obj =new Formula1ChampionshipManager();

        obj.checkYear();

        try {
            //https://www.programiz.com/java-programming/objectinputstream
            //saving data that belongs to one season in one text file.
            FileInputStream fin = new FileInputStream("Formula1Championship(" + obj.getSeasonYear() + ").txt");
            ObjectInputStream oin = new ObjectInputStream(fin);

            obj = (Formula1ChampionshipManager) oin.readObject();
            oin.close();
            fin.close();

            System.out.println("Data load successful. \nFile name : Formula1Championship(" + obj.getSeasonYear() + ").txt");
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            obj.menu();

            System.out.print("Enter Choice or enter exit to stop: ");
            String userInput = input.nextLine();

            System.out.println("-----------------------------------------------------------------------");

            try {
                int choice = Integer.parseInt(userInput); //https://www.javatpoint.com/java-integer-parseint-method

                if (choice == 1) {
                    obj.addDriver();
                } else if (choice == 2) {
                    obj.deleteDriver();
                } else if (choice == 3) {
                    obj.changeDriver();
                } else if (choice == 4) {
                    obj.displayDriverInfo();
                } else if (choice == 5) {
                    obj.displayDriverTable();
                } else if (choice == 6) {
                    obj.addRaceInfo();
                } else if (choice == 7) {
                    //https://www.javatpoint.com/serialization-in-java
                    saveRaceInfo(obj);
                } else if (choice == 8) {
                    goToGUI(obj);
                } else if (choice == 9) {
                    System.exit(0);
                } else {
                    System.out.println("Enter a valid option");
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
        }
    }

    public static void saveRaceInfo(Formula1ChampionshipManager obj) {
        //https://www.programiz.com/java-programming/objectoutputstream
        try {
            FileOutputStream fOut = new FileOutputStream("Formula1Championship(" + obj.getSeasonYear() + ").txt");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);

            oOut.writeObject(obj);
            oOut.flush();

            oOut.close();
            fOut.close();
            System.out.println("Data save successful. \nFile name : Formula1Championship(" + obj.getSeasonYear() + ").txt");
        } catch (IOException e) {
            System.out.println("***ERROR***");
        }
    }
    private static void goToGUI(Formula1ChampionshipManager obj) {
        GUI f1GUI = new GUI(obj);

        f1GUI.openGui();

        while (f1GUI.isRunning()) {
            System.out.print("");
        }
    }
}
