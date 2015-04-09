package hf.events;

import java.rmi.RemoteException;
import com.octanner.batchapp.eventprocessing.scheduler.EventServer2;
import com.octanner.eventprocessinghelper.scheduler.EventServerException;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class EventAndPostRunner {

    public static void main(String[] args) throws Exception {
        try {
            EventServer2 eventServer = new EventServer2();
            EventReset reset = new EventReset(); reset.init();
            for (String gid : args) {
                reset.reset(gid);
                eventServer.processEvent(gid);
            }
//            eventServer.postEventProcessing();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (EventServerException e) {
            e.printStackTrace();
        }
    }
}
