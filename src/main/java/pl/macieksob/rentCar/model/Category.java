package pl.macieksob.rentCar.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


public enum Category {

    COMFORT("Comfort"),
    SPORT("Sport"),
    ECONOMY("Ekonomiczne"),
    EXCLUSIVE("Ekskluzywne"),
    RETRO("Retro"),
    CARGO("Dostawcze");

    private String detail;

    Category(String detail) {
        this.detail = detail;
    }

    Category(){}




}
