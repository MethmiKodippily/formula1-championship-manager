import java.io.Serializable;

public class Date implements Serializable, Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public boolean validateMonth(int month){
        if(!(month>=1 && month<=12)){
            return false;
        }else
            return true;
    }
    //method to validate month
    public boolean validateDay(int day,int month){
        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
            if(!(day>=1 && day<=31)){
                return false;
            }else
                return true;
        }else if(month==4||month==6||month==9||month==11){
            if(!(day>=1 && day<=30)){
                return false;
            }else
                return true;
        }else if(month==2){
            if(!(day>=1&&day<=29)){
                return false;
            }else
                return true;

        }else
            return false;
    }
    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    @Override
    public int compareTo(Date temp) {
        if(this.year>temp.year){
            return 1;
        }
        else if(this.year==temp.year){
            if(this.month>temp.month){
                return 1;
            }else if(this.month==temp.month){
                if(this.day>temp.day){
                    return 1;
                }else if(this.day==temp.day){
                    return 0;}
                else
                    return -1;}
            else
                return -1;
        }
        else
            return -1;
    }
}