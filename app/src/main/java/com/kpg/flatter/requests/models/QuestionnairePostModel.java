package com.kpg.flatter.requests.models;


public class QuestionnairePostModel {
    private Boolean pets;
    private int constructionYearMin;
    private int constructionYearMax;
    private int roomAmountMin;
    private int roomAmountMax;
    private double sizeMin;
    private double sizeMax;
    private Boolean isFurnished;
    private Boolean smokingInside;
    private String type;
    private int totalCostMin;
    private int totalCostMax;

    public QuestionnairePostModel() {}

    public QuestionnairePostModel(Boolean pets, int minCost, int maxCost, int constructionYearMin, int constructionYearMax, int roomAmountMin, int roomAmountMax, double sizeMin, double sizeMax, Boolean isFurnished, Boolean smokingInside, String type) {
        this.pets = pets;
        this.constructionYearMin = constructionYearMin;
        this.constructionYearMax = constructionYearMax;
        this.roomAmountMin = roomAmountMin;
        this.roomAmountMax = roomAmountMax;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
        this.isFurnished = isFurnished;
        this.smokingInside = smokingInside;
        this.type = type;
        this.totalCostMin = minCost;
        this.totalCostMax = maxCost;
    }

    public Boolean getPets() {
        return pets;
    }

    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public int getConstructionYearMin() {
        return constructionYearMin;
    }

    public void setConstructionYearMin(int constructionYearMin) {
        this.constructionYearMin = constructionYearMin;
    }

    public int getConstructionYearMax() {
        return constructionYearMax;
    }

    public void setConstructionYearMax(int constructionYearMax) {
        this.constructionYearMax = constructionYearMax;
    }

    public int getRoomAmountMin() {
        return roomAmountMin;
    }

    public void setRoomAmountMin(int roomAmountMin) {
        this.roomAmountMin = roomAmountMin;
    }

    public int getRoomAmountMax() {
        return roomAmountMax;
    }

    public void setRoomAmountMax(int roomAmountMax) {
        this.roomAmountMax = roomAmountMax;
    }

    public double getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(double sizeMin) {
        this.sizeMin = sizeMin;
    }

    public double getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(double sizeMax) {
        this.sizeMax = sizeMax;
    }

    public Boolean getFurnished() {
        return isFurnished;
    }

    public void setFurnished(Boolean furnished) {
        isFurnished = furnished;
    }

    public Boolean getSmokingInside() {
        return smokingInside;
    }

    public void setSmokingInside(Boolean smokingInside) {
        this.smokingInside = smokingInside;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalCostMin() {
        return totalCostMin;
    }

    public void setTotalCostMin(int totalCostMin) {
        this.totalCostMin = totalCostMin;
    }

    public int getTotalCostMax() {
        return totalCostMax;
    }

    public void setTotalCostMax(int totalCostMax) {
        this.totalCostMax = totalCostMax;
    }

}
