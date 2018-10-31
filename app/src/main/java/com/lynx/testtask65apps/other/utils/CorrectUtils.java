package com.lynx.testtask65apps.other.utils;

import java.util.Calendar;
import java.util.regex.Pattern;

public class CorrectUtils {

    public static String getAge(String birthday) {
        int age;

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        int year = Integer.valueOf(birthday.substring(birthday.length() - 4, birthday.length()));
        int month = Integer.valueOf(birthday.substring(3, 5));
        int day = Integer.valueOf(birthday.substring(0, 2));

        age = currentYear - year;
        if (currentMonth < month) age--;
        if (currentMonth == month) {
            if (currentDay < day) age--;
        }

        return String.valueOf(age);
    }

    public static String birthdayToRequiredLook(String birthday) {
        if(birthday == null || birthday.isEmpty()) return null;
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)");
        if(pattern.matcher(birthday).matches()) return birthday.replace("-", ".");
        StringBuilder result = new StringBuilder();

        result.append(birthday.substring(birthday.length()-2, birthday.length()))
                .append(".")
                .append(birthday.substring(5, 7))
                .append(".")
                .append(birthday.substring(0, 4));

        return result.toString();
    }

    public static String nameToRequiredLook(String srcString) {
        String buf = srcString.toLowerCase();
        return buf.substring(0, 1).toUpperCase() + buf.substring(1);
    }
}
