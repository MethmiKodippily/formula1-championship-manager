import java.io.Serializable;
import java.util.HashMap;

public class Race implements Serializable,Comparable<Race> {
    private Date date;
    private final static int[] pointList = {25,18,15,12,10,8,6,4,2,1,0,0,0,0,0};
    //https://www.javatpoint.com/java-hashmap
    private HashMap<Integer,String> positions=new HashMap<>();

    public HashMap<Integer, String> getPositions() {
        return positions;
    }

    public void setPositions(HashMap<Integer, String> positions) {
        this.positions = positions;
    }

    public static int[] getPointList() {
        return pointList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Race temp) {
        int sample= date.compareTo(temp.date);
        if(sample==1){
            return 1;
        }else if(sample==0){
            return 0;
        }else
            return -1;
    }
}
