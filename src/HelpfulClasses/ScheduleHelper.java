package HelpfulClasses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ScheduleHelper {

    /**
     * Checks the duration of a time slot (to verify that it is less than 6)
     * 
     * @param start  Start time of the time slot 
     * @param end    End time of the time slot 
     *
     * @return duration time in hours 
     */

    public static int calculateDurationInHours(int start, int end) {
        // First, separa hours and time from the start time and the end time
        //Como el tiempo es dado en militar se divide entre 100 para coger los primeros dos digitos
        //And then the modulus by 100 gives the las two digits 
        int startHour = start / 100;
        int startMinutes = start % 100;
        int endHour = end / 100;
        int endMinutes = end % 100;

        //Convert the hours to minutes (this is the beauty of military time)
        //then calculate the total difference
        int totalStartMinutes = (startHour * 60) + startMinutes;
        int totalEndMinutes = (endHour * 60) + endMinutes;
        int totalMinutesDifference = totalEndMinutes - totalStartMinutes;

        // return it (as hours again)
        return totalMinutesDifference / 60;
    }

        /**
     * Checks if two time slots overlap
     * 
     * @param firstStart  Start time of the first time slot (military time, e.g., 1300)
     * @param firstEnd    End time of the first time slot (military time, e.g., 1500)
     * @param secondStart Start time of the second timeslot (military time, e.g., 1400)
     * @param secondEnd   End time of the second time slot (military time, e.g., 1600)
     * @return true if the two intervals overlap, false otherwise
     */

     // Two time slots overlap if the start of the second is before the end of the first,
    // and vice versa. Esto es importante para verificar que haya un vehiculo disponible

     public static boolean timesOverlap(int firstStart, int firstEnd, int secondStart, int secondEnd) {
        return firstStart < secondEnd && secondStart < firstEnd;
    }

    //Ask the user for a month and verifies that it is actually valid
    //(from 1-12), keep asking until they get it right
    public static int askMonthAndValidate(Scanner scanner) {
        int month = -1;
        while (month < 1 || month > 12) {
            System.out.print("Enter reservation month (1-12): ");
            try {
                month = Integer.parseInt(scanner.nextLine());
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Must be between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 12.");
            }
        }
        return month;
    }

    //Ask the user for a month and verifies that it is actually valid
    //(from 1-12), keep asking until they get it right
    public static int askDateAndValidate(Scanner scanner) {
        int day = -1;
        while (day < 1 || day > 31) {
            System.out.print("Enter reservation day (1-31): ");
            try {
                day = Integer.parseInt(scanner.nextLine());
                if (day < 1 || day > 31) {
                    System.out.println("Date must be from 1-31. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1-31.");
            }
        }
        return day;
    }

    //Ask for the time and veridy that it is written correctly aka from 0-24 hours
    // and that minutes don;t exceed 60
    public static int askStartTimeAndValidate(Scanner scanner) {
        int start = -1;
        while (start < 700 || start > 1800 || start % 100 >= 60) {
            System.out.print("Enter start time in Military time: ");
            try {
                start = Integer.parseInt(scanner.nextLine());
                if (start < 700 || start > 1800 || start % 100 >= 60) {
                    System.out.println("Time was not written properly. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter military time");
            }
        }
        return start;
    }

    //Ask for the time and veridy that it is written correctly aka from 0-24 hours
    // and that minutes don;t exceed 60, oh and that it's after start time duh
    public static int askEndTimeAndValidate(Scanner scanner, int startTime) {

        int end = -1;
        while (end <= startTime || end > 1800 || end % 100 >= 60) {
            System.out.print("Enter start time in Military time: ");
            try {
                end = Integer.parseInt(scanner.nextLine());
                if (end <= startTime || end > 1800 || end % 100 >= 60) {
                    System.out.println("Time was not written properly or it's before the start time. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter military time");
            }
        }
        return end;
    }


    // --------------- HERE WE MANAGE THE SCHEDULES --------- //

    /**
     * Creates an empty schedule: a map from month to (map from day to set of time slots).
     */
    public static Map<Integer, Map<Integer, Set<TimeSlot>>> createEmptySchedule() {
        return new HashMap<>();
    }

        /**
     * Adds a new available time to the schedule map.
     *
     * @param schedule The initial schedule map
     * @param month 
     * @param day
     * @param startTime Start time of availability in military time
     * @param endTime End time of availability in military time
     * the map is as follows key : Month, value: [Key: day, Value: time slot]
     */
    //
    public static void addAvailability(Map<Integer, Map<Integer, Set<TimeSlot>>> schedule, int month, int day, int startTime, int endTime) {
        //Add month and time if they don't aleready exist
        schedule.putIfAbsent(month, new HashMap<>());
        Map<Integer, Set<TimeSlot>> monthMap = schedule.get(month);
        //add day to the month if it doesn't exist
        monthMap.putIfAbsent(day, new HashSet<>());
        Set<TimeSlot> daySlots = monthMap.get(day);
        //add new time slot to the day
        daySlots.add(new TimeSlot(startTime, endTime));
    }

        /**
     * Turn the scheduleMap into a string to save to CSV
     *
     * @param schedule The initia; schedule map 
     * the map is as follows key : Month, value: [Key: day, Value: time slot]
     * @return string schedule
     * Example 4:26:1300-1500;1600-1800|5:1:0900-1000
     * month:day:slots(divided by ;)
     *  "|" to separate the days
     */

    public static String compressSchedule(Map<Integer, Map<Integer, Set<TimeSlot>>> schedule) {
        if (schedule == null || schedule.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Integer, Map<Integer, Set<TimeSlot>>> monthEntry : schedule.entrySet()) {
            int month = monthEntry.getKey();
            for (Map.Entry<Integer, Set<TimeSlot>> dayEntry : monthEntry.getValue().entrySet()) {
                int day = dayEntry.getKey();
                builder.append(month).append(":").append(day).append(":");

                Set<TimeSlot> timeSlots = dayEntry.getValue();
                int count = 0;
                for (TimeSlot slot : timeSlots) {
                    if (count > 0) builder.append(";");
                    builder.append(slot.getStartTime()).append("-").append(slot.getEndTime());
                    count++;
                }
                builder.append("|");
            }
        }

        // Remove last "|"
        if (builder.length() > 0 && builder.charAt(builder.length() - 1) == '|') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    /**
     * Converts the string csv back to the map form
     * the map form is as follows: key : Month, value: [Key: day, Value: time slot]
     * * month:day:slots(divided by ;)
     *  "|" to separate the days
     * @param stringSchedule string representing the schedule
     * @return the map
     */
    public static Map<Integer, Map<Integer, Set<TimeSlot>>> expandSchedule(String stringSchedule) {
        Map<Integer, Map<Integer, Set<TimeSlot>>> schedule = new HashMap<>();
        if (stringSchedule == null || stringSchedule.isEmpty()) return schedule;

        String[] dayBlocks = stringSchedule.split("\\|");
        for (String block : dayBlocks) {
            String[] parts = block.split(":");
            if (parts.length != 3) continue;

            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            String[] timeSlots = parts[2].split(";");

            for (String slot : timeSlots) {
                String[] times = slot.split("-");
                if (times.length != 2) continue;

                int start = Integer.parseInt(times[0]);
                int end = Integer.parseInt(times[1]);
                addAvailability(schedule, month, day, start, end);
            }
        }
        return schedule;
    }
}

