package pl.macieksob.rentCar.model;

public enum Petrol {

    DIESEL("Diesel"), BENZINE("Benzyna"), HYBRID("Hybryda"), ELECTRIC("ELektryczny");

    private String detail;

    Petrol(){}

    Petrol(String detail){this.detail=detail;}
}
