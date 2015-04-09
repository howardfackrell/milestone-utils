package hf.events;

import com.octanner.sc.events.RecipientDynamicSolicitationUtil;
import com.octanner.sc.util.ManagerSingleton;
import com.trilogy.sc.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class EventReset {

    ISCManager mgr = null;

    public static void main(String[] args) {

        EventReset reset = new EventReset();
        reset.init();

        for (String eventGid : args) {
            reset.reset(eventGid);
        }

    }

    public void reset(String eventGid) {
        IOCTEvent event = getEvent(eventGid);
        List<IOCTRecipient> recipients = getRecipientsForEvent(event);
        System.out.println(recipients.size() + " recipients being reprocessed for event " + eventGid);
        for (IOCTRecipient recipient : recipients) {

            if (isEmailOnlySolicitation(event))
                RecipientDynamicSolicitationUtil.reprocessSolicitation_emailOnly(recipient, event);
            else {
                recipient.setSolicitationReasonCode("02");

                recipient.setSolicitationReorderType("ZNC");
                recipient.setSolicitationPartialBravo(false);
                recipient.reprocessEventIfExists(event.getEventDetail().getType());
            }
        }
        System.out.println(recipients.size() + " recipients reprocessed.");
    }



    public void init() {
        mgr = ManagerSingleton.getManager();
    }

    public IOCTEvent getEvent(String eventGID)
    {
        IOCTEvent event = null;
        if (eventGID != null && eventGID.length() > 0)
        {
            try
            {
                event = (IOCTEvent) mgr.objectFromGID(mgr.newGID(eventGID));
            }
            catch (Exception e)
            {
                System.err.println("error getEvent(" + eventGID + "): " + e.getMessage());
            }
        }
        return event;
    }


    public List<IOCTRecipient> getRecipientsForEvent(IOCTEvent event)
    {

        List<IOCTRecipient> recipients = new ArrayList<IOCTRecipient>();
        if (event != null)
        {

            IOCTEventSchedule eventSchedule = (IOCTEventSchedule) event.getSchedule();
            if (eventSchedule != null)
            {
                String eventScheduleGID = eventSchedule.get_GID();
                StringBuffer sb = new StringBuffer();
                sb.append("select r.tril_gid as recipientGID");
                sb.append(" from OCTRecipient r");
                sb.append(" where r.eventSchedule = '");
                sb.append(eventScheduleGID);
                sb.append("'");

                String query = sb.toString();

                ISCRecordset resultSet = mgr.newQuery().executeSQL(query);
                while (resultSet.moveNext())
                {
                    String recipientGID = (String) resultSet.valueFromIndex(0);
                    GID recipientgid = mgr.newGID(recipientGID);
                    IOCTRecipient recipient = (IOCTRecipient) mgr.objectFromGID(recipientgid);
                    if (recipient != null)
                    {
                        recipients.add(recipient);
                    }
                }
            }
        }
        return recipients;
    }


    private boolean isEmailOnlySolicitation(IOCTEvent event)
    {
        if (event.getEventDetail().getAdminEventTypeName().equalsIgnoreCase("SOLICITATION"))
        {
            if (event != null
                    &&  event.getEventDetail().getEventName().equals("email only"))
                return true;
        }
        return false;
    }
}
