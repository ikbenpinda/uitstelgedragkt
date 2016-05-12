package achan.nl.uitstelgedrag.models;

import java.util.Calendar;

/**
 * Created by Etienne on 24-4-2016.
 */
public class Timestamp {

    public static final String DATE_DELIMITER = "-";
    public static final String FIELD_DELIMITER = "|";
    public static final String TIME_DELIMITER = ":";

    public static final String ARRIVAL = "arrival";
    public static final String DEPARTURE = "departure";

    public int id = -1;

    public int day;
    public int month;
    public int year;

    public int hours;
    public int minutes;

    public String type;
    public String location; // or x and y coordinates?

    public final Calendar instance = Calendar.getInstance();

    /**
     * Returns Dutch translations for the name of the month.
     * @param month
     * @return
     */
    public String translateMonth(int month){
        switch (month){
            case Calendar.JANUARY:
                return "Januari";
            case Calendar.FEBRUARY:
                return "Februari";
            case Calendar.MARCH:
                return "Maart";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "Mei";
            case Calendar.JUNE:
                return "Juni";
            case Calendar.JULY:
                return "Juli";
            case Calendar.AUGUST:
                return "Augustus";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "Oktober";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
            default:
                return "Onbekend";
        }
    }

    /**
     * Returns Dutch translations for the day of the week.
     * @param day
     * @return
     */
    public String translateDay(int day){

        String str = "";
        switch (day){
            case Calendar.MONDAY:
                str = "Maandag";
                break;
            case Calendar.TUESDAY:
                str = "Dinsdag";
                break;
            case Calendar.WEDNESDAY:
                str = "Woensdag";
                break;
            case Calendar.THURSDAY:
                str = "Donderdag";
                break;
            case Calendar.FRIDAY:
                str = "Vrijdag";
                break;
            case Calendar.SATURDAY:
                str = "Zaterdag";
                break;
            case Calendar.SUNDAY:
                str = "Zondag";
                break;
        }
        return str;
        // DateUtils refers to using SimpleDateFormat?
    }

    public String translateType(String Type){
        return type.equals(ARRIVAL) ? "Aankomst" : "Vertrek";
    }

    public Timestamp() {
    }

    /**
     * Creates a new timestamp set to the current moment.
     * @param type
     */
    public Timestamp(String type){

        this.type = type;

        this.day = instance.get(Calendar.DAY_OF_MONTH);
        this.month = instance.get(Calendar.MONTH) + 1; // month is zero-based.
        this.year = instance.get(Calendar.YEAR);

        this.hours = instance.get(Calendar.HOUR_OF_DAY);
        this.minutes = instance.get(Calendar.MINUTE);
    }

    /**
     * Used for creating a timestamp from the past.
     * @param type ARRIVAL/DEPARTURE
     * @param day day of the month
     * @param month month of the year
     * @param year
     * @param hours 24h format
     * @param minutes
     */
    public Timestamp(String type, int day, int month, int year, int hours, int minutes){

        this.type = type;

        this.day = day;
        this.month = month;
        this.year = year;

        this.hours = hours;
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        StringBuilder timestampbuilder = new StringBuilder();
        timestampbuilder
                .append(day)
                .append(DATE_DELIMITER)
                .append(month)
                .append(DATE_DELIMITER)
                .append(year)
                .append(FIELD_DELIMITER)
                .append(hours)
                .append(TIME_DELIMITER)
                .append(minutes);
        String timestamp = timestampbuilder.toString();

        return timestamp;
    }
}
