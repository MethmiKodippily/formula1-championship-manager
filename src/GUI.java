import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GUI {

    private final Formula1ChampionshipManager obj;

    private boolean isRunning;

    private JFrame frame2;
    private JFrame frame3;
    private JFrame frame4;
    private JFrame frame5;
    private JFrame frame6;
    private JFrame frame7;
    private JFrame frame8;
    private JFrame frame9;
    private JFrame frame10;
    private JFrame frame11;

    public GUI(Formula1ChampionshipManager obj) {
        this.obj = obj;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void openGui() {

        isRunning = true;

        JFrame frame1 = new JFrame("Menu");
        //Dispose of the frame object, but keep the application running.
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("MENU");
        label.setBounds(200,15,40,40);

        JButton button1 = new JButton("Display Driver Tables");
        button1.setBounds(40,80, 160, 40);

        JButton button2 = new JButton("Random Race");
        button2.setBounds(230,80, 160, 40);

        JButton button3 = new JButton("Random Starting Position Race");
        button3.setBounds(105,230, 220, 40);

        JButton button4 = new JButton("All Races");
        button4.setBounds(40,160, 160, 40);

        JButton button5 = new JButton("Search Race");
        button5.setBounds(230,160, 160, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //try and catch block will close existing window if the button that opened the window is clicked again
                try {
                    frame2.dispose();
                } catch (NullPointerException ignored) {
                }

                frame2 = new JFrame("Driver Table Menu");
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                tableMenu();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame6.dispose();
                } catch (NullPointerException ignored) {
                }

                frame6 = new JFrame("Random Race");
                frame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ArrayList<Formula1Driver> array = obj.getArray();

                if (array.size() >= 15) {
                    randomRace(array, frame6);
                } else {
                    JOptionPane.showMessageDialog(frame6, "No Enough Drivers");
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame7.dispose();
                    frame8.dispose();
                } catch (NullPointerException ignored) {
                }

                //this frame will display the starting positions of the random race created
                frame7 = new JFrame("Starting Positions");
                frame7.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame8 = new JFrame("Results");
                frame8.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ArrayList<Formula1Driver> array = obj.getArray();

                if (array.size() >= 15) {
                    String[] startPositions = new String[15]; //array to store the divers chosen from random positions
                    String firstPlace = getFirstPlace(array, startPositions ,frame7);
                    randomSPositionRace(startPositions, frame8, firstPlace);
                } else {
                    JOptionPane.showMessageDialog(frame7, "No Enough Drivers");
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame9.dispose();
                } catch (NullPointerException ignored) {
                }

                frame9 = new JFrame("All Races");
                frame9.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                int year=obj.getSeasonYear();
                Date dateObj= new Date();
                dateObj.setYear(year);
                ArrayList<Race> raceList= obj.getRaceList();
                Collections.sort(raceList);
                displayAllRaces(raceList,frame9);
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame10.dispose();
                } catch (NullPointerException ignored) {
                }

                ArrayList<Race> raceList = obj.getRaceList();

                if (raceList.size() != 0) {
                    frame10 = new JFrame("Search Races");
                    frame10.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    raceSearchWindow(raceList, frame10);
                } else {
                    JOptionPane.showMessageDialog(frame10, "No Races");
                }
            }
        });

        //closing all open frames once the menu frame is closed
        WindowListener wListener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                JFrame[] frames = {frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11};

                for (JFrame frame : frames) {
                    try {
                        frame.dispose();
                    } catch (NullPointerException ignored) {
                    }
                }

                isRunning = false;// This closes  the gui
            }
        };

        frame1.add(label);
        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);
        frame1.add(button4);
        frame1.add(button5);
        frame1.addWindowListener(wListener);
        frame1.setSize(450,350);
        frame1.setLayout(null);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

    private void tableMenu() {
        JButton button1 = new JButton("Points Descending");
        button1.setBounds(130,60, 190, 40);

        JButton button2 = new JButton("Points Ascending");
        button2.setBounds(130,140, 190, 40);

        JButton button3 = new JButton("First Positions Descending");
        button3.setBounds(130,220, 190, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame3.dispose();
                } catch (NullPointerException ignored) {
                }

                frame3 = new JFrame("Points Descending");
                frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ArrayList<Formula1Driver> array = obj.getArray();
                Collections.sort(array);

                displayDriversTable(array, frame3);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame4.dispose();
                } catch (NullPointerException ignored) {
                }

                frame4 = new JFrame("Points Ascending");
                frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ArrayList<Formula1Driver> array = obj.getArray();
                //reversing the order of the previous array
                Collections.sort(array, Collections.reverseOrder());

                displayDriversTable(array, frame4);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame5.dispose();
                } catch (NullPointerException ignored) {
                }

                frame5 = new JFrame("First Positions Descending");
                frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ArrayList<Formula1Driver> array = obj.getArray();
                Collections.sort(array, new PositionComparator());

                displayDriversTable(array, frame5);
            }
        });

        frame2.add(button1);
        frame2.add(button2);
        frame2.add(button3);
        frame2.setSize(450,350);
        frame2.setLayout(null);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }

    private void displayDriversTable(ArrayList<Formula1Driver> array, JFrame frame) {
        if (array.size() != 0) {
            String[] column = {"TEAM", "DRIVER", "LOCATION", "POINTS", "1ST POSITIONS", "2ND POSITIONS", "3RD POSITIONS", "RACES"};

            String[][] data = new String[array.size()][8];

            for (int i = 0; i < array.size(); i++) {
                data[i][0] = array.get(i).getTeam();
                data[i][1] = array.get(i).getDriver();
                data[i][2] = array.get(i).getLocation();
                data[i][3] = String.valueOf(array.get(i).getPointCount());
                data[i][4] = String.valueOf(array.get(i).getFirstPositionCount());
                data[i][5] = String.valueOf(array.get(i).getSecondPositionCount());
                data[i][6] = String.valueOf(array.get(i).getThirdPositionCount());
                data[i][7] = String.valueOf(array.get(i).getRaceCount());
            }

            //https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
            //Making the table non editable
            TableModel model = new DefaultTableModel(data, column) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable table = new JTable(model);

            JScrollPane sPane = new JScrollPane(table);

            frame.add(sPane);
            frame.setSize(850, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "No Drivers");
        }
    }

    private String getFirstPlace(ArrayList<Formula1Driver> array, String[] startPositions, JFrame frame) {

        Random random = new Random();

        //adding random drivers to startPositions array
        for (int i = 0; i < startPositions.length; i++) {
            while (true) {
                int num = random.nextInt(array.size());
                String driver = array.get(num).getDriver();

                if(!obj.hasPosition(startPositions, driver)) {
                    startPositions[i] = driver;
                    break;
                }
            }
        }

        //display the staring positions
        String[] column1 = {"Starting Position", "Driver's Name"};

        String[][] data = new String[15][2];

        for (int i = 0; i < startPositions.length; i++) {
            data[i][0] = String.valueOf((i + 1));
            data[i][1] = startPositions[i];
        }

        TableModel model = new DefaultTableModel(data, column1) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        JScrollPane sPane = new JScrollPane(table);

        frame.add(sPane);
        frame.setSize(850, 400);
        frame.setVisible(true);

        int number = random.nextInt(100) + 1;

        //randomly choosing the driver who wins the first position according to their starting position and returning the name of that driver
        if (number <= 40) {
            return startPositions[0];
        } else if (number <= 70) {
            return startPositions[1];
        } else if (number <= 80) {
            return startPositions[2];
        } else if (number <= 90) {
            return startPositions[3];
        } else if (number <= 92) {
            return startPositions[4];
        } else if (number <= 94) {
            return startPositions[5];
        } else if (number <= 96) {
            return startPositions[6];
        } else if (number <= 98) {
            return startPositions[7];
        } else {
            return startPositions[8];
        }
    }

    private void  randomRace(ArrayList<Formula1Driver> array, JFrame frame){
        Random random = new Random();
        String[] winningDrivers = new String[15];

        //generating a random index to select a random driver from the driver array list and adding the chosen random driver to winningDrivers array
        for (int x = 0; x < winningDrivers.length; x++) {
            while (true) {
                int num = random.nextInt(array.size());
                String driver = array.get(num).getDriver();

                if(!obj.hasPosition(winningDrivers, driver)) {
                    winningDrivers[x] = driver;
                    break;
                }
            }
        }

        displayRace(winningDrivers, frame);

    }

    private void randomSPositionRace(String[] startPositions, JFrame frame, String firstPlace) {
        Random random = new Random();
        String[] winningDrivers = new String[15];
        //adding the driver who wins the race to the 0th index of the winningDrivers array
        winningDrivers[0] = firstPlace;

        //randomly generating the rest of the places in the race and adding them to the winningDrivers array
        for (int i = 1; i < winningDrivers.length; i++) {
            while (true) {
                int num = random.nextInt(startPositions.length);
                String driver = startPositions[num];

                if(!obj.hasPosition(winningDrivers, driver)) {
                    winningDrivers[i] = driver;
                    break;
                }
            }
        }

        displayRace(winningDrivers, frame);
    }

    private void displayRace(String[] array, JFrame frame) {
        Random random = new Random();
        Date dateObj= new Date();

        //generating random month and day
        int year = obj.getSeasonYear();
        int month = random.nextInt(12) + 1;
        int day;

        while (true) {
            day = random.nextInt(31) + 1;

            if (dateObj.validateDay(day, month)) {
                break;
            }
        }

        dateObj.setYear(year);
        dateObj.setMonth(month);
        dateObj.setDay(day);

        obj.addPositions(array, dateObj);

        String[] column = {"Date", "Place", "Driver's Name"};

        String[][] data = new String[15][3];

        for (int i = 0; i < array.length; i++) {
            data[i][0] = dateObj.toString();
            data[i][1] = String.valueOf((i + 1));
            data[i][2] = array[i];
        }

        TableModel model = new DefaultTableModel(data, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        JScrollPane sPane = new JScrollPane(table);

        frame.add(sPane);
        frame.setSize(850, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void raceSearchWindow(ArrayList<Race> raceList, JFrame frame) {
        JLabel label = new JLabel("Enter Driver's Name");
        label.setBounds(50,50, 200,30);

        JTextField textField = new JTextField();
        textField.setBounds(180,50, 200,30);

        JButton button = new JButton("Search Races");
        button.setBounds(180,130, 150,30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String driver = textField.getText();

                if (!obj.checkDriver(driver)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Name");
                } else {
                    ArrayList<Race> matchingRaces = new ArrayList<>();

                    //if the driver that the user in searching for is in one or more races, those races will be added to matchingRaces array list
                    for (Race race : raceList) {
                        for (int i = 1; i <= race.getPositions().size(); i++) {
                            if (race.getPositions().get(i).equalsIgnoreCase(driver)) {
                                matchingRaces.add(race);
                            }
                        }
                    }

                    try {
                        frame11.dispose();
                    } catch (NullPointerException ignored) {
                    }

                    //displaying the matching races in a new frame
                    if (matchingRaces.size() != 0) {
                        frame11 = new JFrame("Search Races");
                        frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        displayAllRaces(matchingRaces, frame11);
                    } else {
                        JOptionPane.showMessageDialog(frame11, "Has not participated any races");
                    }
                }
            }
        });

        frame.add(label);
        frame.add(textField);
        frame.add(button);;
        frame.setSize(450,350);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displayAllRaces(ArrayList<Race> races, JFrame frame) {
        String[] column = {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15"};

        String[][] data = new String[races.size()][16];

        for (int i = 0; i < races.size(); i++) {
            data[i][0] = races.get(i).getDate().toString();
            data[i][1] = races.get(i).getPositions().get(1);
            data[i][2] = races.get(i).getPositions().get(2);
            data[i][3] = races.get(i).getPositions().get(3);
            data[i][4] = races.get(i).getPositions().get(4);
            data[i][5] = races.get(i).getPositions().get(5);
            data[i][6] = races.get(i).getPositions().get(6);
            data[i][7] = races.get(i).getPositions().get(7);
            data[i][8] = races.get(i).getPositions().get(8);
            data[i][9] = races.get(i).getPositions().get(9);
            data[i][10] = races.get(i).getPositions().get(10);
            data[i][11] = races.get(i).getPositions().get(11);
            data[i][12] = races.get(i).getPositions().get(12);
            data[i][13] = races.get(i).getPositions().get(13);
            data[i][14] = races.get(i).getPositions().get(14);
            data[i][15] = races.get(i).getPositions().get(15);

        }

        TableModel model = new DefaultTableModel(data, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        JScrollPane sPane = new JScrollPane(table);

        frame.add(sPane);
        frame.setSize(850, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
