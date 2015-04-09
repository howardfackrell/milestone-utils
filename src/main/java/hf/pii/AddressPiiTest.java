package hf.pii;

import com.octanner.sc.util.ManagerSingleton;
import com.trilogy.sc.*;

import java.util.Random;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class AddressPiiTest {


    public static final String STP = "0000101025";
    public static final int PROGRAMID = 218;
    public static final int LEVELPROP = 10;
    public static final int SCALE = 100;
    Random rand;

    ISCManager mgr;

    public AddressPiiTest() {
        mgr = ManagerSingleton.getManager();
        rand = new Random();
    }

    public static void main(String[] args) {
        AddressPiiTest test = new AddressPiiTest();

        IOCTRecipient recipient = test.createRecipient(
                test.createOctAddress("addr1", "addr1@example.com"),
                test.createOctAddress("addr2", "addr2@example.com"),
                test.createOctAddress("addr3", "addr3@example.com")
        );


//
//        IOCTRecipientAddress addr1 = test.createOctAddress("addr1", "addr1@example.com");
//        addr1.Save();
//        IOCTRecipientAddress addr2 = test.createOctAddress("addr2", "addr2@example.com");
//        addr2.Save();
//        IOCTRecipientAddress addr3 = test.createOctAddress("addr3", "addr2@example.com");
//        addr3.Save();
//
//        IOCTRecipient recipient = test.createRecipient();
//        recipient.setContact1(addr1);
//        recipient.setContact2(addr2);
//        recipient.setContact3(addr3);



        System.out.println("Gunna save it!!!");
        recipient.Save();

        System.out.println("Recipient ID: " +recipient.get_GID());
        System.out.println("Contact 1: " +recipient.getContact1().get_GID() + " " + recipient.getContact1().getAddress1());
        System.out.println("Contact 2: " +recipient.getContact2().get_GID() + " " + recipient.getContact2().getAddress1());
        System.out.println("Contact 3: " +recipient.getContact3().get_GID() + " " + recipient.getContact3().getAddress1());


        recipient.Delete();
        System.out.println("Deleted the stuff. All done.");
    }


    IOCTRecipientAddress createOctAddress(String name, String email) {
        IOCTRecipientAddress address = (IOCTRecipientAddress) mgr.newObject(IOCTRecipientAddress.typeID);
        address.setAddress1(name + "Address1");
        address.setCity("City");
        address.setState("ST");
        address.setEmail(email);
        return address;
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

        return recipient;
    }

    public IOCTRecipient createRecipient(IOCTRecipientAddress addr1, IOCTRecipientAddress addr2, IOCTRecipientAddress addr3) {
        IOCTRecipient recipient = createRecipient();

        recipient.setContact1(addr1);
        recipient.setContact2(addr2);
        recipient.setContact3(addr3);
        return recipient;
    }
}

