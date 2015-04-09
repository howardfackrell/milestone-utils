package hf.pdr;

import com.octanner.sc.util.ManagerSingleton;
import com.trilogy.sc.ISCCollection;
import com.trilogy.sc.ISCManager;
import com.trilogy.sc.ISCRecordset;
import com.trilogy.sc.core.OCTAward;
import com.trilogy.sc.core.OCTAwardAdjStart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class PDRAdjustmentFixer {

    ISCManager mgr;

    public PDRAdjustmentFixer() { mgr = ManagerSingleton.getManager(); }

    public static void main(String[] args) {

        PDRAdjustmentFixer fixer = new PDRAdjustmentFixer();

        String stp =  "0001365934";
        String programid = "9";
        String selectionCode = "5323";
//        String selectionCode = "6865";
//        String selectionCode = "8857";
        List<OCTAwardAdjStart> adjustments = fixer.findOldPdrAdjustmentsForAward(stp, programid, selectionCode);
        OCTAward award = fixer.getAward(stp, programid, selectionCode);
        fixer.ensureAdjInCollection(award, adjustments);
        System.out.println("DONE!!!");
    }

    List<OCTAwardAdjStart> findOldPdrAdjustmentsForAward(String stp, String programid,  String selectionCode) {
        List<OCTAwardAdjStart> results = new ArrayList<OCTAwardAdjStart>();
        StringBuilder query = new StringBuilder();
        query.append("Select Aas.Tril_Gid From Octawardadjstart Aas \n" +
                "Join Octaward A On aas.Award = A.Tril_Gid\n" +
                "join octprogram p on a.program = p.tril_gid\n" +
                "Join Sc_Organization O On P.Customer = O.Tril_Gid\n" +
                "Join Octcustomer C On O.Octcustomer = C.Tril_Gid\n" +
                "join octawardavailable aa on a.tril_gid = aa.award\n" +
                "where c.soldtopartynumber = '").append(stp).append("' and p.programid = ").append(programid).append("\n" +
                "and a.selectioncode = '").append(selectionCode).append("'")
        ;//.append( "and aas.pdradj <> 0 ");
        ISCRecordset recordset = mgr.newQuery().executeSQL(query.toString());

        while (recordset.moveNext()) {
            String gid = (String) recordset.valueFromIndex(0);
            OCTAwardAdjStart adj = (OCTAwardAdjStart) mgr.objectFromGID(mgr.newGID(gid));
            results.add(adj);
        }

        return results;
    }

    OCTAward getAward(String stp, String programid,  String selectionCode) {
        StringBuilder query = new StringBuilder();

        query.append("select a.tril_gid from octaward a \n" +
                "join octprogram p on a.program = p.tril_gid\n" +
                "Join Sc_Organization O On P.Customer = O.Tril_Gid\n" +
                "Join Octcustomer C On O.Octcustomer = C.Tril_Gid\n" +
                "join octawardavailable aa on a.tril_gid = aa.award\n" +
                "where c.soldtopartynumber = '").append(stp).append("' and p.programid = ").append(programid).append("\n" +
                "and a.selectioncode = '").append(selectionCode).append("'");

        OCTAward award = null;
        ISCRecordset recordset = mgr.newQuery().executeSQL(query.toString());
        if (recordset.moveNext()) {
            String awardGid = (String) recordset.valueFromIndex(0);
            award =  (OCTAward) mgr.objectFromGID(mgr.newGID(awardGid));
        }
        return award;
    }

    void ensureAdjInCollection(OCTAward award, List<OCTAwardAdjStart> adjustments){
        System.out.println("Award:  " + award.getGID());
        System.out.println("Adjustments: " + award.getAdjustments() );
        ISCCollection collection = award.getAdjustments();
        for (OCTAwardAdjStart adj : adjustments) {
            if (!contains(collection, adj)) {
                collection.add(adj);
                System.out.println("Need to add this one " + adj.getGID().toString());
            }
        }

        collection.Save();
        award.Save();
    }

    boolean contains(ISCCollection collection, OCTAwardAdjStart adj) {
        boolean found = false;
        for (int i = 1; i <= collection.getCount(); i++) {
            OCTAwardAdjStart collectionAdj = (OCTAwardAdjStart) collection.item(i);
            if (collectionAdj.getGID().toString().equalsIgnoreCase(adj.getGID().toString())) {
                found = true;
            }
        }
        return found;
    }
}

