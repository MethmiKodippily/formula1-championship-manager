import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {

    private int seasonYear;

    private static Scanner  input = new Scanner(System.in);

    private ArrayList<Formula1Driver> array=new ArrayList<Formula1Driver>();

    private ArrayList<Race> raceList=new ArrayList<>();

    public int getSeasonYear() {
        return seasonYear;
    }

    public ArrayList<Formula1Driver> getArray() {
        return array;
    }

    public ArrayList<Race> getRaceList() {
        return raceList;
    }

    @Override
    public void menu() {
        //printing the menu
        System.out.println("-----------------------------------------------------------------------");
        System.out.println();
        System.out.println("MENU: SELECT OPTION NUMBER.");
        System.out.println();
        System.out.println("1 - Add New Driver to and His/Her Team.");
        System.out.println("2 - Delete a Driver and the Driver's Team from the Formula 1 Championship.");
        System.out.println("3 - Change the driver of an Existing Team.");
        System.out.println("4 - Display Driver Statistics.");
        System.out.println("5 - Display the Formula 1 Driver Table.");
        System.out.println("6 - Add Race Information (15 Drivers Per Race).");
        System.out.println("7 - Save All Information Added So Far.");
        System.out.println("8 - Open GUI.");
        System.out.println("9 - Exit.");
        System.out.println();

    }

    //this method checks if the year entered is valid.
    public void checkYear(){
        while(true){
            System.out.println("Enter the year : ");
            String userInput = input.nextLine();
            try {
                int year = Integer.parseInt(userInput);
                if(year>1900 && year<3000){
                    this.seasonYear=year;
                    break;
                }else {
                    System.out.println("Invalid year!");
                    System.out.println("");
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer");
                System.out.println("");
            }
        }
    }

    //checks if the driver has already bee added.
    public boolean checkDriver(String name){
        for (Formula1Driver driver: array) {
            if(driver.getDriver().equalsIgnoreCase(name)){
                return true; }
        }
        return false;
    }

    //checks if the team already exists.
    public boolean checkTeam(String teamName){
        for (Formula1Driver driver: array) {
            if(driver.getTeam().equalsIgnoreCase(teamName)){
                return true; }
        }
        return false;
    }

    @Override
    public void addDriver() {
        System.out.println("Enter the driver's team : ");
        String team = input.nextLine();
        if (!team.isEmpty()) {
            if (checkTeam(team)) {
                System.out.println("This team already has a driver!");
            } else {
                System.out.println("Enter the driver's name : ");
                String driver = input.nextLine();
                if (!driver.isEmpty()) {
                    if (checkDriver(driver)) {
                        System.out.println("This driver has already been added to another team!");
                    } else {
                        System.out.println("Enter the driver's location : ");
                        String location = input.nextLine();
                        if (!location.isEmpty()) {
                            Formula1Driver driverObj = new Formula1Driver(team, driver, location);
                            array.add(driverObj);
                        } else {
                            System.out.println("Location cannot be empty");
                        }
                    }
                } else {
                    System.out.println("Driver cannot be empty");
                }
            }
        } else {
            System.out.println("Team cannot be empty");
        }
    }

    @Override
    public void deleteDriver() {
        if (array.size() != 0){
            System.out.println("Enter the name of driver that should be removed: ");
            String delDriver = input.nextLine();
            if (!delDriver.isEmpty()) {
                if (checkDriver(delDriver)) {
                    for (int x = 0; x < array.size(); x++) {
                        if (array.get(x).getDriver().equalsIgnoreCase(delDriver)) {
                            array.remove(x);
                            System.out.println("Driver has been successfully removed!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid name!");
                }
            } else {
                System.out.println("Driver cannot be empty");
            }
        }
        else {
            System.out.println("No drivers have been added yet!");
        }
    }

    @Override
    public void changeDriver() {
        System.out.println("Enter the name of the team to which the new driver must be added: ");
        String newTeam = input.nextLine();
        if (!newTeam.isEmpty()) {
            if (checkTeam(newTeam)) {
                for (int x = 0; x < array.size(); x++) {
                    if (array.get(x).getTeam().equalsIgnoreCase(newTeam)) {
                        System.out.println("Enter the name of the new driver that should be added: ");
                        String newDriver = input.nextLine();
                        if (!newDriver.isEmpty()) {
                            if (checkDriver(newDriver)) {
                                System.out.println("This driver has already been added to another team!");
                                break;
                            } else {
                                System.out.println("Enter the new driver's location : ");
                                String newLocation = input.nextLine();
                                if (!newLocation.isEmpty()) {
                                    array.remove(x);
                                    Formula1Driver newObj = new Formula1Driver(newTeam, newDriver, newLocation);
                                    array.add(x, newObj);
                                    break;
                                } else {
                                    System.out.println("Location cannot be empty");
                                }
                            }
                        } else {
                            System.out.println("Driver cannot be empty");
                        }
                    }
                }
            } else {
                System.out.println("This team does not exist!");
            }
        } else {
            System.out.println("Team cannot be empty");
        }
    }

    @Override
    public void displayDriverInfo() {
        System.out.println("Enter the name the name of the driver: ");
        String driverName= input.nextLine();
        if (!driverName.isEmpty()) {
            if (checkDriver(driverName)) {
                for (Formula1Driver driver : array) {
                    if (driver.getDriver().equalsIgnoreCase(driverName)) {
                        System.out.println(driver.toString());
                    }
                }
            } else {
                System.out.println("Invalid name!");
            }
        } else {
            System.out.println("Driver cannot be empty");
        }
    }


    @Override
    public void displayDriverTable() {
        Collections.sort(array);

        //https://www.baeldung.com/java-pad-string#3-using-stringformat
        //printing table headings
        String tableHeadings = String.format("%1$" + -20 + "s" , "TEAM")+
                String.format("%1$" + -20 + "s" , "DRIVER")+
                String.format("%1$" + -20 + "s" , "LOCATION")+
                String.format("%1$" + -20 + "s" , "POINTS")+
                String.format("%1$" + -20 + "s" , "1ST POSITIONS")+
                String.format("%1$" + -20 + "s" , "2ND POSITIONS")+
                String.format("%1$" + -20 + "s" , "3RD POSITIONS")+
                String.format("%1$" + -20 + "s" , "RACES")+"\n";
        System.out.println(tableHeadings);

        String data = "";
        //printing each row
        for(Formula1Driver item: array){
            data+= String.format("%1$" + -20 + "s" , item.getTeam())+
                    String.format("%1$" + -20 + "s" , item.getDriver())+
                    String.format("%1$" + -20 + "s" , item.getLocation())+
                    String.format("%1$" + -20 + "s" , item.getPointCount())+
                    String.format("%1$" + -20 + "s" , item.getFirstPositionCount())+
                    String.format("%1$" + -20 + "s" , item.getSecondPositionCount())+
                    String.format("%1$" + -20 + "s" , item.getThirdPositionCount())+
                    String.format("%1$" + -20 + "s" , item.getRaceCount())+"\n";
        }
        System.out.println(data);
    }

    @Override
    public void addRaceInfo() {
        if(array.size()>=15){
            Date dateObj= new Date();
            while (true){
                System.out.println("Enter the date of the race");
                System.out.println("Month: ");
                int month= input.nextInt();
                input.nextLine();
                if(!(dateObj.validateMonth(month))){
                    System.out.println("Invalid Month!");
                    continue;
                }
                System.out.println("Day: ");
                int day= input.nextInt();
                input.nextLine();
                if(!(dateObj.validateDay(day,month))){
                    System.out.println("Invalid Day!");
                    continue;
                }
                dateObj.setYear(seasonYear);
                dateObj.setMonth(month);
                dateObj.setDay(day);
                break;
            }

            String[] winningDrivers= new String[15];

            //enter driver to position if the driver exists in the divers array and doesn't already have a position in the race.
            for(int i=0; i< winningDrivers.length; i++){
                while (true){
                    System.out.println("Enter the name of the driver who won place "+ (i+1) +":");
                    String winnerName= input.nextLine();
                    if (!winnerName.isEmpty()) {
                        if (!checkDriver(winnerName)) {
                            System.out.println("This driver does not exist in formula1 championship!");
                            continue;
                        }
                        if (hasPosition(winningDrivers, winnerName)) {
                            System.out.println("This driver already has a position in the race");
                            continue;
                        }
                        winningDrivers[i] = winnerName;
                        break;
                    } else {
                        System.out.println("Driver cannot be empty");
                    }
                }
            }

            addPositions(winningDrivers, dateObj);
        }else {
            System.out.println("Not enough drivers to add a race!");
        }
    }

    public void addPositions(String[] winningDrivers, Date dateObj){
        int[] pointList= Race.getPointList(); //get the array that sores the point list from Race class

        HashMap<Integer,String> positions=new HashMap<>();

        for(int i=0; i< winningDrivers.length;i++){
            positions.put((i+1),winningDrivers[i]);
            for (Formula1Driver driver: array) {
                //adding one to whichever position the driver won
                if(driver.getDriver().equalsIgnoreCase(winningDrivers[i])){
                    if(i==0){
                        driver.addFirstPositionCount(1);
                    }else if(i==1){
                        driver.addSecondPositionCount(1);
                    } else if(i==3){
                        driver.addThirdPositionCount(1);
                    }
                    //adding the points earned from that race to the point count and increasing the race count.
                    driver.addPointCount(pointList[i]);
                    driver.addRaceCount(1);
                }
            }
        }

        Race raceObj=new Race();
        raceObj.setDate(dateObj);
        raceObj.setPositions(positions);

        raceList.add(raceObj);
    }

    //checking if the diver already has a position in the race
    public boolean hasPosition(String[] winningDrivers,String winnerName){
        try{
            for(int x=0; x< winningDrivers.length; x++){
                if(winnerName.equalsIgnoreCase(winningDrivers[x])){
                    return true;
                }
            }return false;
        } catch (NullPointerException e){
            return false;
        }
    }
}
