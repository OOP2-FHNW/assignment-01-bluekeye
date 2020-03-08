package portfolio.investments;

import java.util.Objects;

public abstract class Investment { // abstract class, not interface!
    private String titel; // set in constructor only
    private String country; // can be changed via setter
    private double singlePrice; // can be changed via setter
    private double count; // can be changed via setter

    public Investment(String titel) {
        this.titel       = titel;
        this.country = "Schweiz";
        this.count = 1.0; // set to 1.0 every time a new object is created. Not additive.
        this.singlePrice = 1.0;
    }

    @Override
    public boolean equals(Object o) { // vergleicht Titel
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) { //.getClass: dynamic type
            return false;
        }
        Investment investment = (Investment) o; // variable name corrected to "investment"

        return Objects.equals(this.getTitel(), investment.getTitel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitel()); // hash-method of Object. Here: uses Titel to create hashCode randomly: Gold: 2225311; Silber: -1818463176
    }

    // all getters and setters according to conventions
    public String getTitel() {
        return titel;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
