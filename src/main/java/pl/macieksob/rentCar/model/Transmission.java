package pl.macieksob.rentCar.model;


public enum Transmission {

    AUTOMATIC("Automatyczna"),MANUAL("Manualna"),HALF_AUTOMATIC("Polautomatyczna");

    private String detail;

    Transmission(String detail) {
        this.detail = detail;
    }

    Transmission(){}
}
