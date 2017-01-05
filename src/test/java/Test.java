import cn.sxy.touch_to_top.TouchToTop;

public class Test {

    public static void main(String[] args) {
        testMain();
        testStart();
        testHeadCount();
        testTouchLimit();
        testComplex();
        testOutput();
    }

    private static void testMain() {
        TouchToTop t = TouchToTop.create(i -> true);
        int x = t.touch();
        assert x == Integer.MAX_VALUE;
    }

    private static void testStart() {
        TouchToTop t = TouchToTop.create(i -> true);
        t.setStart(100);
        int x = t.touch();
        assert x == Integer.MAX_VALUE;
    }

    private static void testHeadCount() {
        TouchToTop t = TouchToTop.create(i -> i <= 1000);
        t.setHeadCount(100);
        int x = t.touch();
        assert x == 1000;
    }

    private static void testTouchLimit() {
        TouchToTop t = TouchToTop.create(i -> !(i == 100));
        t.setTouchLimit(100);
        int x = t.touch();
        assert x == 99;
    }

    private static void testComplex() {
        TouchToTop t = TouchToTop.create(i -> !(i == 100));
        t.setHeadCount(1);
        t.setTouchLimit(1000);
        int x = t.touch();
        assert x == 99;
    }

    private static void testOutput() {
        TouchToTop t = TouchToTop.create(i -> !(i == 100));
        t.setOutput((c, m, f) -> System.out.println(c + " " + m + " " + f));
        t.setTouchLimit(1000);
        int x = t.touch();
        assert x == 1000;
    }

}
