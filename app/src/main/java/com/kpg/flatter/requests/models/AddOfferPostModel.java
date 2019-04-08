package com.kpg.flatter.requests.models;

public class AddOfferPostModel {
    private String jhiType;
    private String description;
    private Integer roomAmount;
    private Double totalCost;
    private Double jhiSize;
    private Integer constructionYear;
    private Boolean pets;
    private Boolean smokingInside;
    private Boolean isFurnished;

    public AddOfferPostModel() {
    }

    public AddOfferPostModel(String jhiType, String description, Integer roomAmount, Double totalCost, Double jhiSize, Integer constructionYear, Boolean pets, Boolean smokingInside, Boolean isFurnished) {
        this.jhiType = jhiType;
        this.description = description;
        this.roomAmount = roomAmount;
        this.totalCost = totalCost;
        this.jhiSize = jhiSize;
        this.constructionYear = constructionYear;
        this.pets = pets;
        this.smokingInside = smokingInside;
        this.isFurnished = isFurnished;
    }

    public String getJhiType() {
        return jhiType;
    }

    public void setJhiType(String jhiType) {
        this.jhiType = jhiType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(Integer roomAmount) {
        this.roomAmount = roomAmount;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getJhiSize() {
        return jhiSize;
    }

    public void setJhiSize(Double jhiSize) {
        this.jhiSize = jhiSize;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Boolean getPets() {
        return pets;
    }

    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public Boolean getSmokingInside() {
        return smokingInside;
    }

    public void setSmokingInside(Boolean smokingInside) {
        this.smokingInside = smokingInside;
    }

    public Boolean getIsFurnished() {
        return isFurnished;
    }

    public void setIsFurnished(Boolean isFurnished) {
        this.isFurnished = isFurnished;
    }
}
