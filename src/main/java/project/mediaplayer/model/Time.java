package project.mediaplayer.model;

public class Time {

    private int minute;
    private int second;


    // time with minute and second constructor
    public Time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }

    // time with minute and second constructor
    public Time(int second) {
        this.second = second;
    }

    // GETTER METHODS
    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
    //////////////////////

    // SETTER METHODS
    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }


    // convert time seconds format (s) to minutes and seconds format (mm:ss) => WORKED
    public void convertToMinuteFormat() {
        if (this.second >= 0) {
            this.minute = this.second / 60;
            this.second %= 60;
        } else {
            throw new RuntimeException("Time format is not valid!");
        }
    }

    // convert time minute and seconds format (mm:ss) to seconds format (s) => WORKED
    public void convertToSecondFormat() {
        if (this.second >=0) {
            this.second = this.minute * 60 + this.second;
        } else {
            throw new RuntimeException("Time format is not valid!");
        }
    }




    public static void main(String[] args) {
        Time time = new Time(95);
        time.convertToMinuteFormat();
        System.out.println(time.getMinute() + " " + time.getSecond());
        time.convertToSecondFormat();
        System.out.println(time.getSecond());

    }
}
