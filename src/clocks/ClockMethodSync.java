package clocks;

// Lazy Init + GetInstance Method Synchronization
/*
 - Simple, straighfoward
 - use when there is a need of lazy init as your app won't always need to create and use this instance
  + calling getInstance does NOT create substantial performance overhead
 */
public class ClockMethodSync implements IClock{
    private final Object mutex = new Object();
    private int counter = 0;
    private static ClockMethodSync uniqClock;
    private ClockMethodSync(){}

    public synchronized static ClockMethodSync getInstance(){
        if(uniqClock == null){
            uniqClock = new ClockMethodSync();
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
