package com.bus.homework;
import java.io.Serializable;

import static com.bus.homework.BusValidator.*;

/**
 * Created by apavliuchenkova on 08/06/2017.
 */
class Bus implements Serializable{
    private String firstLastDriverName;
    private int busNumber;
    private int routeNumber;
    private String busModel;
    private int worksSinceYear;
    private int mileage;

    public Bus(){ }

    public Bus(String firstLastDriverName, int busNumber, int routeNumber, String busModel, int worksSinceYear, int mileage) {
        this.firstLastDriverName = firstLastDriverName;
        this.busNumber = busNumber;
        this.routeNumber = routeNumber;
        this.busModel = busModel;
        this.worksSinceYear = worksSinceYear;
        this.mileage = mileage;
    }

    public void setFirstLastDriverName(String firstLastDriverName) throws BusException {
        validateFirstLastDriverName(firstLastDriverName);
        this.firstLastDriverName = firstLastDriverName;
    }

    public void setBusNumber(String busNumber) throws BusException {
        validateBusNumber(busNumber);
        this.busNumber = Integer.parseInt(busNumber);
    }

    public void setRouteNumber(String routeNumber) throws BusException {
        validateRouteNumber(routeNumber);
        this.routeNumber = Integer.parseInt(routeNumber);
    }

    public void setBusModel(String busModel) throws BusException {
        validateBusModel(busModel);
        this.busModel = busModel;
    }

    public void setWorksSinceYear(String worksSinceYear) throws BusException {
        validateWorksSinceYear(worksSinceYear);
        this.worksSinceYear = Integer.parseInt(worksSinceYear);
    }

    public void setMileage(String mileage) throws BusException {
        validateMileage(mileage);
        this.mileage = Integer.parseInt(mileage);
    }

    public void setFieldValue(String fieldName, String fieldVal) throws Exception {
        switch (fieldName) {
            case "firstLastDriverName": setFirstLastDriverName(fieldVal); break;
            case "busNumber": setBusNumber(fieldVal); break;
            case "routeNumber": setRouteNumber(fieldVal); break;
            case "busModel": setBusModel(fieldVal); break;
            case "worksSinceYear": setWorksSinceYear(fieldVal); break;
            case "mileage": setMileage(fieldVal); break;
            default: throw new Exception("Invalid field name!");
        }
    }

    public String getFirstLastDriverName() {
        return firstLastDriverName;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getBusModel() {
        return busModel;
    }

    public int getWorksSinceYear() {
        return worksSinceYear;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return firstLastDriverName + "\t" +
               busNumber + "\t" +
               routeNumber + "\t" +
               busModel + "\t" +
               worksSinceYear + "\t" +
               mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;

        Bus bus = (Bus) o;

        if (busNumber != bus.busNumber) return false;
        if (routeNumber != bus.routeNumber) return false;
        if (worksSinceYear != bus.worksSinceYear) return false;
        if (mileage != bus.mileage) return false;
        if (!firstLastDriverName.equals(bus.firstLastDriverName)) return false;
        return busModel.equals(bus.busModel);

    }

    @Override
    public int hashCode() {
        int result = firstLastDriverName.hashCode();
        result = 31 * result + busNumber;
        result = 31 * result + routeNumber;
        result = 31 * result + busModel.hashCode();
        result = 31 * result + worksSinceYear;
        result = 31 * result + mileage;
        return result;
    }
}
