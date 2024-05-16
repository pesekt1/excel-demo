package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


enum AgeType {
    JUNIOR, STANDARD, SENIOR
}

enum SwimType {
    COMPETITIVE, NONCOMPETITIVE
}

public class Member {
    private String name;
    private String BirthDate;
    private boolean activeMembership;

    public AgeType ageType;

    public SwimType swimType;

    public int subscriptionFee;

    public String registrationDate;

    public String trainerName;

    public double ButterflyTime;
    public String ButterflyDate;

    public double BackstrokeTime;
    public String BackstrokeDate;

    public double BreaststrokeTime;
    public String BreaststrokeDate;

    public int balance;

    //constructor
    public Member(String name, String BirthDate, boolean activeMembership, SwimType swimType) {
        this.name = name;
        this.BirthDate = BirthDate;
        this.activeMembership = activeMembership;
        this.ageType = calculateAgeType(BirthDate);
        this.swimType = swimType;
        this.subscriptionFee = calculateSubscriptionFee(ageType);
        this.registrationDate = LocalDate.now().toString();
        this.trainerName = (swimType == SwimType.COMPETITIVE) ? " Trainer" : "x";
        this.balance = 0;
        this.BreaststrokeDate = "";
        this.BreaststrokeTime = 0;
        this.BackstrokeDate = "";
        this.BackstrokeTime = 0;
        this.ButterflyDate = "";
        this.ButterflyTime = 0;
    }

    public Member() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public boolean isActiveMembership() {
        return activeMembership;
    }

    public AgeType getAgeType() {
        return ageType;
    }

    public SwimType getSwimType() {
        return swimType;
    }

    public int getSubscriptionFee() {
        return subscriptionFee;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public double getButterflyTime() {
        return ButterflyTime;
    }

    public String getButterflyDate() {
        return ButterflyDate;
    }

    public double getBackstrokeTime() {
        return BackstrokeTime;
    }

    public String getBackstrokeDate() {
        return BackstrokeDate;
    }

    public double getBreaststrokeTime() {
        return BreaststrokeTime;
    }

    public String getBreaststrokeDate() {
        return BreaststrokeDate;
    }

    public int getBalance() {
        return balance;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public void setActiveMembership(boolean activeMembership) {
        this.activeMembership = activeMembership;
    }

    public void setAgeType(AgeType ageType) {
        this.ageType = ageType;
    }

    public void setSwimType(SwimType swimType) {
        this.swimType = swimType;
    }

    public void setSubscriptionFee(int subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public void setButterflyTime(double ButterflyTime) {
        this.ButterflyTime = ButterflyTime;
        this.ButterflyDate = LocalDate.now().toString();
    }

    public void setButterflyDate(String ButterflyDate) {
        this.ButterflyDate = ButterflyDate;
    }

    public void setBackstrokeTime(double BackstrokeTime) {
        this.BackstrokeTime = BackstrokeTime;
        this.BackstrokeDate = LocalDate.now().toString();
    }

    public void setBackstrokeDate(String BackstrokeDate) {
        this.BackstrokeDate = BackstrokeDate;
    }

    public void setBreaststrokeTime(double BreaststrokeTime) {
        this.BreaststrokeTime = BreaststrokeTime;
        this.BreaststrokeDate = LocalDate.now().toString();
    }

    public void setBreaststrokeDate(String BreaststrokeDate) {
        this.BreaststrokeDate = BreaststrokeDate;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AgeType calculateAgeType(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(birthDate, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid birth date format. Expected format is dd/MM/yyyy");
        }

        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if (age < 18) {
            return AgeType.JUNIOR;
        } else if (age <= 60) {
            return AgeType.STANDARD;
        } else {
            return AgeType.SENIOR;
        }
    }

    public int calculateSubscriptionFee(AgeType ageType) {
        switch (ageType) {
            case JUNIOR:
                return 1000;
            case STANDARD:
                return 1600;
            case SENIOR:
                return 1200;
            default:
                throw new IllegalArgumentException("Invalid age type");
        }
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Birth Date: " + BirthDate);
        System.out.println("Active Membership: " + activeMembership);
        System.out.println("Age Type: " + ageType);
        System.out.println("Swim Type: " + swimType);
        System.out.println("Subscription Fee: " + subscriptionFee);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Trainer Name: " + trainerName);
        System.out.println("Butterfly Time: " + ButterflyTime);
        System.out.println("Butterfly Date: " + ButterflyDate);
        System.out.println("Backstroke Time: " + BackstrokeTime);
        System.out.println("Backstroke Date: " + BackstrokeDate);
        System.out.println("Breaststroke Time: " + BreaststrokeTime);
        System.out.println("Breaststroke Date: " + BreaststrokeDate);
        System.out.println("Balance: " + balance);
    }




}
