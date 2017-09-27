package com.bus.homework;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alesia on 6/12/17.
 */
public class BusValidator {

    public static void validateFirstLastDriverName(String firstLastDriverName) throws BusException {
        final int maxLength = 30;
        if (firstLastDriverName.charAt(0) != firstLastDriverName.toUpperCase().charAt(0)){
            throw new BusException("Invalid First Last Driver Name! First letter should be in upper case!");
        }

        if (firstLastDriverName.length() > maxLength){
            throw new BusException("Invalid First Last Driver Name! Value is too long! It can be less then " + maxLength + ".");
        }

        Pattern pattern = Pattern.compile("[()<>/;_+=\\*%$].*");
        Matcher matcher = pattern.matcher(firstLastDriverName);
        if(matcher.find()) {
            throw new BusException("Invalid First Last Driver Name! First Last Driver Name can't have special symbols!");
        }

    }

    public static void validateBusNumber(String busNumber) throws BusException {
        final int busNumberMaxLength = 7;
        try {
            Integer.parseInt(busNumber);
        } catch (NumberFormatException e) {
            throw new BusException("Bus Number should be numeric!");
        }
        if (busNumber.length() >= busNumberMaxLength) {
            throw new BusException("Bus Number is too long! It can have less then " + busNumberMaxLength + " digits.");
        }
        if (Integer.parseInt(busNumber) < 0) {
            throw new BusException("Bus Number can't be negative.");
        }
    }

    public static void validateRouteNumber(String routeNumber) throws BusException {
        final int routeNumberMaxLength = 7;
        try {
            Integer.parseInt(routeNumber);
        } catch (NumberFormatException e) {
            throw new BusException("Route Number should be numeric!");
        }

        if (routeNumber.length() >= routeNumberMaxLength) {
            throw new BusException("Route Number is too long! It can have less then " + routeNumberMaxLength + " digits.");
        }

        if (Integer.parseInt(routeNumber) < 0) {
            throw new BusException("Route Number can't be negative.");
        }
    }

    public static void validateBusModel(String busModel) throws BusException {
        final int busModelMaxLength = 10;
        Pattern pattern = Pattern.compile("[()<>/;_+=\\*%$].*");
        Matcher matcher = pattern.matcher(busModel);
        if(matcher.find()) {
            throw new BusException("Bus Model is not valid! Bus Model can't have special symbols!");
        }
        if(busModel.length() >= busModelMaxLength) {
            throw new BusException("Bus Model is too long! It can have less then " + busModelMaxLength + " symbols.");
        }

    }

    public static void validateWorksSinceYear(String worksSinceYear) throws BusException {
        int year;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        final int startYear = 1965;
        try {
            year = Integer.parseInt(worksSinceYear);
        } catch (NumberFormatException e) {
            throw new BusException("Work Since Year should be numeric!");
        }

        if (year < startYear || year > currentYear) {
            throw new BusException("Work Since Year range is '" + startYear + "' - '" + currentYear + "'!");
        }
    }

    public static void validateMileage(String mileage) throws BusException {
        try {
            Integer.parseInt(mileage);
        } catch (NumberFormatException e) {
            throw new BusException("Mileage should be integer!");
        }

        if (Integer.parseInt(mileage) < 0) {
            throw new BusException("Mileage can't be negative.");
        }
    }
}
