import java.util.Comparator;

public class PositionComparator implements Comparator<Formula1Driver> {
    @Override
    public int compare(Formula1Driver temp1, Formula1Driver temp2) {//sorting drivers by the number of first positions achieved
        if(temp1.firstPositionCount<temp2.firstPositionCount){
            return 1;
        }else if(temp1.firstPositionCount==temp2.firstPositionCount){
            return 0;
        }else
            return -1;
    }
}
