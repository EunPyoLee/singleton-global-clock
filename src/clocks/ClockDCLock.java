package clocks;

// Lazy Init + Double-Checking Lock
/*
 - use when there is a need of lazy init as your app won't always need to create and use this instance
  + calling getInstance can be a performance bottle neck!
  (Simply, a performance imporved version of Method Sync)
 */
public class ClockDCLock implements IClock{
    private final Object mutex = new Object();
    private int counter = 0;
    // volatile is necessary, as it will enforce the cpu cache to be written back to memory immediately
    private static volatile ClockDCLock uniqClock;
    private ClockDCLock(){}

    public synchronized static ClockDCLock getInstance(){
        if(uniqClock == null){
            synchronized (ClockDCLock.class) {
                if(uniqClock == null){
                    uniqClock = new ClockDCLock();
                }
            }
        }
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
