import java.io.Serializable;

abstract class Driver implements Serializable {
    private String team;
    private String driver;
    private String location;

    public Driver(String team, String driver, String location) {
        this.driver=driver;
        this.location=location;
        this.team=team;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Requested driver's statistics" +"\r\n"+"\r\n"+
                "Team = " + team + "\r\n" +
                "Driver = " + driver + "\r\n" +
                "Location = " + location + "\r\n" ;
    }
}
