package portfolio;

import org.junit.jupiter.api.Test;
import portfolio.investments.Share;

import static org.junit.jupiter.api.Assertions.*;

public class PortfolioTest {
    @Test
    void testContains() {
        //given
        Portfolio<Share> p   = new Portfolio<>();
        Share            ubs = new Share("ubs"); // count=1
        assertFalse(p.contains(ubs)); // ubs in Share, but not yet in Portfolio

        //when
        p.buy(ubs); // ubs added to Portfolio, incl. all attributes

        //then
        assertTrue(p.contains(ubs)); // ubs in Portfolio
        assertTrue(p.contains(new Share("ubs"))); // ubs in Portfolio, Vergleich via equals (Titel)
    }

    @Test
    void testBuy() {
        //given
        Portfolio<Share> p   = new Portfolio<>();
        Share            ubs = new Share("ubs"); // count=1

        //when
        p.buy(ubs); // ubs added to Portfolio, incl. all attributes
        p.buy(new Share("ubs")); // // ubs already in Portfolio: just count increased
        // !! every time a new Share is created: count set to 1, not additive
        // !! real count Share set in Portfolio when buy

        //then
        assertEquals(2, ubs.getCount()); // 2 ubs-shares bought by Portfolio
    }

    @Test
    void testSell() {
        //given
        Portfolio<Share> p   = new Portfolio<>();
        Share            ubs = new Share("ubs"); // count=1
        ubs.setCount(2); // Shares: count set to 2: 2 ubs-shares available
        p.buy(ubs); // ubs added to Portfolio, incl. all attributes

        //when
        p.sell("ubs", 1); // sell: via Titel and amount, count in Share decreased

        //then
        assertTrue(p.contains(ubs)); // since count still >0: ubs not removed
        assertEquals(1, ubs.getCount()); //2-1=1
    }

    @Test
    void testSellUnknown() {
        //given
        Portfolio<Share> p   = new Portfolio<>();
        Share            ubs = new Share("ubs"); // count=1
        p.buy(ubs); // ubs added to Portfolio, incl. all attributes

        //when
        p.sell("cs", 1); // cs not sold, and doesn't change count of ubs anyway

        //then
        assertTrue(p.contains(ubs)); // still 1 ubs-share in Portfolio
        assertEquals(1, ubs.getCount()); // still count=1
    }

    @Test
    void testSellTotal() {
        //given
        Portfolio<Share> p   = new Portfolio<>();
        Share            ubs = new Share("ubs"); // count=1
        p.buy(ubs); // ubs added to Portfolio, incl. all attributes

        //when
        p.sell("ubs", 1); // count-1

        //then
        assertFalse(p.contains(ubs)); // all ubs-shares (1) sold: ubs-share removed from Portfolio if count <1
        assertEquals(0, ubs.getCount()); // count=0
    }

    @Test
    void testGetShare() {
        //given
        Portfolio<Share> p   = new Portfolio<>();
        Share            ubs = new Share("ubs"); // count=1
        p.buy(ubs); // added to Portfolio

        //when

        //then
        assertSame(ubs, p.getShare("ubs")); // get Share(object) via titel, return Share(object)
        assertNull(p.getShare("cs")); // get Share(object) via titel, return Share(object) or null
    }
}
