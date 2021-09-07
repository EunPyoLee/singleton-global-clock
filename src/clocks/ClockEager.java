package clocks;

// Eager initializing Clock
/*
 - Simple, straighfoward
 - use when there is no high traffic requested on getInstance + when there isn't
   significant runtime overhead of creating this instance and needs to create & use this instance all time
 */
public class ClockEager implements IClock{
    private final Object mutex = new Object();
    private int counter = 0;
    private static final ClockEager uniqClock = new ClockEager();
    private ClockEager(){}

    public static ClockEager getInstance(){
        return uniqClock;
    }

    // Returns uniq counter that decides the order of distributed systems using this clock
    public int getCounter(){
        synchronized (mutex){
            int ret = counter;
            ++counter;
            return ret;
        }
    }
}
