package hf.pii;

import com.octanner.sc.util.ManagerSingleton;
import com.trilogy.sc.IOCTAwardLevel;
import com.trilogy.sc.IOCTRecipient;
import com.trilogy.sc.ISCManager;
import com.trilogy.sc.ISCRecordset;

import hf.util.Timer;
import java.util.*;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class RecipientOperationStats {


    public static final String STP = "0000101025";
        public static final int PROGRAMID = 217; //not pii
//    public static final int PROGRAMID = 218; // pii
    public static final int LEVELPROP = 10;
    public static final int SCALE = 100;


    List<String> gids;
    ISCManager mgr;
    Random rand;
    Timer timer;

    RecipientOperationStats() {
        mgr = ManagerSingleton.getManager();
        rand = new Random();
        timer = new Timer();
        gids = new ArrayList<String>() {{

            //running this the first time will generate a statement like this for each recipient created, past them in here to run it again
//            add("GVZZZBOFZ3WFLA2ITUZYPLWT3GEZZZZZ"); // <- they look like this
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UBZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LCZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YQZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UQZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QQZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LQZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YPZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UPZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QPZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LPZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YDZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UDZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QDZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LDZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YEZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UEZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QEZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LEZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YOZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UOZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QOZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LOZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YNZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UNZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QNZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LNZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YFZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UFZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QFZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LFZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YMZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UMZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QMZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LMZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YLZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41ULZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QLZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LLZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YKZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UKZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QKZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LKZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YJZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UJZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QJZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LJZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YIZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UIZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QIZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LIZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YHZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UHZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QHZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LHZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YGZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UGZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QGZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LGZZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YZ0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UZ0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QZ0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LZ0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Y00ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41U00ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Q00ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41L00ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YY0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UY0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QY0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LY0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YX0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UX0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QX0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LX0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Y10ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41U10ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Q10ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41L10ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Y20ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41U20ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Q20ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41L20ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YW0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UW0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QW0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LW0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YV0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UV0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41QV0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41LV0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Y30ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41U30ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Q30ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41L30ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Y40ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41U40ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41Q40ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41L40ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41YU0ZZZZ");
            add("GVZZZBHFZ3WFU1KI5UZJMAG41UU0ZZZZ");
        }};
    }


    /**
     * This program is intended to run 2x
     * 1.  Creates recipients and saves them to the database
     * 2.  After copying and pasting from the output into the gids list, the recipients are loaded, modified, saved, then deleted
     * The 2 runs are needed in order to get a time on the load w/o trilogy's cache
     *
     * The last output lines give a summary of the time required to perform the opperations
     * The SCALE constant in this class controls how many recipients are created
     * @param args
     */
    public static void main(String[] args) {
        RecipientOperationStats stats = new RecipientOperationStats();
        Timer.Token totalToken = null;
        List<IOCTRecipient> recipients;
        if (stats.gids.size() == 0) {
            totalToken = stats.timer.start("Creation Total", "");
            recipients = stats.createRecipients(SCALE);
            stats.logRecipientIds(recipients);
        } else {
            totalToken = stats.timer.start("Update/Modify/Delet Total", "");
            recipients = stats.loadRecipients(stats.gids);
            stats.modifyAndSaveRecipients(recipients);
            stats.deleteRecipients(recipients);
        }

        stats.timer.stop(totalToken);
        System.out.println(stats.timer);
    }

    public List<IOCTRecipient> createRecipients(int count) {
        List<IOCTRecipient> recipients = new ArrayList<IOCTRecipient>();
        for (int i = 0; i < count; i++) {
            recipients.add(createRecipient());
        }
        return recipients;
    }

    public IOCTRecipient createRecipient() {
        IOCTRecipient recipient = (IOCTRecipient) mgr.newObject(IOCTRecipient.typeID);
        IOCTAwardLevel level = getLevel(STP, PROGRAMID, LEVELPROP);

        recipient.setAwardLevel(level);
        recipient.setEmployeeNumber(random(10));
        recipient.setFirstName("Howardtest");
        recipient.setLastName("Testtest");
        recipient.setAuditRecipient(true);
        recipient.setCustomerShell(level.getProgram().getCustomerShell());
        recipient.setRecipientEmail("recipent@example.com");
        recipient.setManagerName("Mister Manager");
        recipient.setManagerEmail("theman@example.com");

        Timer.Token token = timer.start("create", recipient.getEmployeeNumber());
        recipient.Save();
        timer.stop(token);
        return recipient;
    }

    public List<IOCTRecipient> loadRecipients(List<String> gids) {
        List<IOCTRecipient> recipientList = new ArrayList<IOCTRecipient>();
        for (String gid : gids) {
            recipientList.add(loadRecipient(gid));
        }
        return recipientList;
    }

    public IOCTRecipient loadRecipient(String gid) {
        Timer.Token token = timer.start("Load", gid);
        IOCTRecipient recipient = (IOCTRecipient) mgr.objectFromGID(mgr.newGID(gid));
        recipient.getFullName();
        timer.stop(token);
        return recipient;
    }

    public void deleteRecipients(List<IOCTRecipient> recipients) {
        for (IOCTRecipient recipient : recipients) {
            deleteRecipient(recipient);
        }
    }

    public void deleteRecipient(IOCTRecipient recipient) {
        Timer.Token token = timer.start("delete", recipient.getEmployeeNumber());
        recipient.Delete();
        timer.stop(token);
    }

    public IOCTAwardLevel getLevel(String stp, int programid, int levelprop) {
        String sql = "Select L.Tril_Gid \n" +
                " From Octawardlevel L \n" +
                " Where L.Program = Programutils.Getprogramfromstppid('" + stp + "'," + programid + ")\n" +
                " and l.levelprop = " + levelprop;
        ISCRecordset rs = mgr.newQuery().executeSQL(sql);
        IOCTAwardLevel level = null;
        if (rs.moveNext()) {
            String gid = (String) rs.valueFromIndex(0);
            level = (IOCTAwardLevel) mgr.objectFromGID(mgr.newGID(gid));
        }
        return level;
    }

    private String random(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(rand.nextInt() % chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public void logRecipientIds(List<IOCTRecipient> recipients) {
        for (IOCTRecipient recipient : recipients) {
            System.out.println("add(\"" + recipient.get_GID() + "\");");
        }
    }

    public void modifyAndSaveRecipients(List<IOCTRecipient> recipients) {
        for (IOCTRecipient recipient : recipients) {
            modifyAndSaveRecipient(recipient);
        }
    }

    public void modifyAndSaveRecipient(IOCTRecipient recipient) {
        recipient.setLastName("Test" + random(5));
        Timer.Token token = timer.start("Modify", recipient.getEmployeeNumber());
        recipient.Save();
        timer.stop(token);
    }
}
