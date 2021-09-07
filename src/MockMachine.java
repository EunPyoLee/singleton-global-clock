import clocks.IClock;

import java.util.List;

public class MockMachine implements Runnable{
    private final Object logMutex;
    private final int machineNum;
    private Thread t;
    private final List<Integer> clockTimeLogger;
    private final IClock clock;

    public MockMachine(int machineNum, IClock clockObj, List<Integer> clockTimeLogger, Object logMutex){
        this.logMutex = logMutex;
        this.machineNum = machineNum;
        this.clock = clockObj;
        this.clockTimeLogger = clockTimeLogger;
    }

    @Override
    public void run() {
        System.out.println("Running " + machineNum);
        try{
            for(int i = 0; i < 10; i++){
                synchronized (this.logMutex){
                    clockTimeLogger.add(clock.getCounter());
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e){
            System.out.println("Machine " + machineNum + " interrupted.");
        }
        System.out.println("Finishing " + machineNum);
    }

    public void start(){
        if(t == null){
            t = new Thread (this);
            t.start();
        }
    }

    public void join() throws InterruptedException{
        t.join();
    }
}
