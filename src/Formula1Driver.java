import java.io.Serializable;

public class Formula1Driver extends Driver implements Comparable<Formula1Driver>, Serializable {

    public int firstPositionCount;
    public int secondPositionCount;
    public int thirdPositionCount;
    public int pointCount;
    public int raceCount;

    public Formula1Driver(String team, String driver, String location) {
        super(team,driver,location);
    }
    public int getFirstPositionCount() {
        return firstPositionCount;
    }

    public void addFirstPositionCount(int firstPositionCount) {
        this.firstPositionCount += firstPositionCount;
    }
    public int getSecondPositionCount() {
        return secondPositionCount;
    }

    public void addSecondPositionCount(int secondPositionCount) {
        this.secondPositionCount += secondPositionCount;
    }
    public int getThirdPositionCount() {
        return thirdPositionCount;
    }

    public void addThirdPositionCount(int thirdPositionCount) {
        this.thirdPositionCount += thirdPositionCount;
    }
    public int getPointCount() {
        return pointCount;
    }

    public void addPointCount(int pointCount) {
        this.pointCount += pointCount;
    }
    public int getRaceCount() {
        return raceCount;
    }

    public void addRaceCount(int raceCount) {
        this.raceCount += raceCount;
    }

    @Override
    public String  toString() {
        return  super.toString() + "\n" +
                "firstPositionCount = " + firstPositionCount + "\n" +
                "secondPositionCount = " + secondPositionCount + "\n" +
                "thirdPositionCount = " + thirdPositionCount + "\n" +
                "pointCount = " + pointCount + "\n" +
                "raceCount = " + raceCount;
    }

    @Override //https://www.youtube.com/watch?v=wboqZ2dPDtQ
    public int compareTo(Formula1Driver temp) {
        if(this.pointCount<temp.pointCount){
            return 1;
        }
        else if(this.pointCount==temp.pointCount){
            if(this.firstPositionCount<temp.firstPositionCount){
                return 1;
            }else if(this.firstPositionCount==temp.firstPositionCount){
                return 0;}
            else
                return -1;
        }
        else
            return -1;
    }
}
