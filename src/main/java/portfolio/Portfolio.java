package portfolio;

import portfolio.investments.Investment;

import java.util.*;

public class Portfolio<T extends Investment> {
    //TODO Complete this implementation and make it generic
    /*
    public static void main(String[] args) {
        Resource silber= new Resource("Silber");
        System.out.println("HashCode Silber:"+silber.hashCode()); // Gold: 2225311; Silber: -1818463176
    }
     */

    /**
     * investment= current investment (just bought or sold)
     * i= sum of all investments of one type (e.g. ubs-share)
     */

    private Set<T> portfolio;

    public Portfolio() { //  Portfolio<Share> p   = new Portfolio<>();
        portfolio = new HashSet<T>();
    }

    public boolean contains(T investment) {
        // check based on title with equals-method
        // if count <1: Share removed from portfolio: contains false. If Share still in portfolio with count=0: contains == false if count <1.
        return portfolio.contains(investment); // contains checks based on overwritten .equals-Methode (Titel)
    }

    public void buy(T investment) {
        T i = getShare(investment.getTitel()); // get object with titel out of portfolio
        if (i == null) {
            portfolio.add(investment); // object investment added with all attributes incl. count
        } else {
            i.setCount(i.getCount() + investment.getCount()); // object investment not added, just count of existing object increased
            // !! every time a new Share is created: count set to 1, not additive -> portfolio has the right count:
            // use count of portfolio and add count of current investment to get correct total count: set count in Share
        }
    }

    public void sell(String titel, int amount) {
        // if amount=0: Objekt rausschmeissen. Sonst bei contains: if amount <1: false.
        T i = getShare(titel);
        double count;
        if (i != null) {
            count = i.getCount();
            if (count >= amount) { // count can't become <0
                i.setCount(count - amount);
                if (i.getCount() < 1) {
                    portfolio.remove(i);
                }
            }
        }
    }

    public T getShare(String titel) {
        // mit for-loop nach Objekt (i) mit Titel in portfolio suchen
        T i = null;
        for (T inv : portfolio) {
            if (i == null && inv.getTitel().equals(titel)) {
                i = inv;
            }
        }
        return i;
    }

}
