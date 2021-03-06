import clocks.ClockEager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EagerClockTest {
    @Test
    public void testEagerClock (){
        List<MockMachine> machines = new ArrayList<>();
        List<Integer> clockLogger = new ArrayList<>();
        Object logMutex = new Object();
        for(int i = 0; i < 10; ++i){
            machines.add(new MockMachine(i, ClockEager.getInstance(), clockLogger, logMutex));
            machines.get(i).start();
        }
        for(int i = 0; i < 10; ++i){
            try {
                machines.get(i).join();
            } catch(InterruptedException e){
                System.out.print(e);
            }
        }
        int prev = -1;
        for(int i = 0; i < clockLogger.size(); ++i){
            Assertions.assertEquals(prev + 1, clockLogger.get(i));
            prev = clockLogger.get(i);
            System.out.println(clockLogger.get(i));
        }
    }
}
