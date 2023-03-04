package project.mediaplayer.model;

public class TimeUtils {


    // time with minute and second constructor
//    public TimeUtils(int minute, int second) {
//        this.minute = minute;
//        this.second = second;
//    }

    // time with minute and second constructor
//    public TimeUtils(int second) {
//        this.second = second;
//    }


    // convert time seconds format (s) to minutes and seconds format (mm:ss) => WORKED
    public static String convertToMinuteFormat(double seconds) {
        int minute = 0;
        int second = 0;
        if (seconds >= 0) {
            minute = (int) (seconds / 60);
            second = (int) (seconds % 60);
        } else {
            throw new RuntimeException("Time format is not valid!");
        }

        return minute + ":" + second;
    }

    // convert time minute and seconds format (mm:ss) to seconds format (s) => WORKED
    public static String convertToSecondFormat(double minutes, double seconds) {
        String result = "";
        int second;
        if (seconds >= 0) {
            second = (int) minutes * 60 + (int) seconds;
        } else {
            throw new RuntimeException("Time format is not valid!");
        }
        return "" + second;

    }


//    public static void main(String[] args) {
//        TimeUtils timeUtils = new TimeUtils();
//        timeUtils.setSecond(95);
//        timeUtils.convertToMinuteFormat();
//        System.out.println(timeUtils.getMinute() + " " + timeUtils.getSecond());
//        timeUtils.convertToSecondFormat();
//        System.out.println(timeUtils.getSecond());
//
//    }
//
//    // toString method for minute and second format (mm:ss)
//    public String minuteAndSecondToString() {
//        return this.minute + ":" + this.second;
//    }
//
//    // toString method for second format (ss)
//    public String secondToString() {
//        return "" + this.second;
//    }
}
