package hf.pii;

import com.octanner.piivault.PiiVaultClient;
import com.octanner.piivault.PiiVaultContractClient;
import com.octanner.sc.util.ManagerSingleton;
import com.trilogy.sc.ISCManager;
import com.trilogy.sc.ISCRecordset;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class PiiContractinator {

    PiiVaultClient vaultClient;
    PiiVaultContractClient contractClient;

    ISCManager mgr;

    public void init() {
        vaultClient = new PiiVaultClient();
        contractClient = new PiiVaultContractClient();
        mgr = ManagerSingleton.getManager();

    }

    public static void main(String[] args) {

        PiiContractinator contractinator = new PiiContractinator();
        contractinator.init();
        contractinator.contractinate();
    }


    public void contractinate() {
        List<String> vaultKeys = lookupVaultKeys();
//        List<String> vaultKeys = vaultKey();

        for (String vaultKey : vaultKeys) {
            try {
                String contractKey = vaultKeyToContractKey(vaultKey);
                System.out.print("converting " + vaultKey + " -> " + contractKey);
                swapRecipientKey(vaultKey, contractKey);
                System.out.print(" ...saved");
                deleteVaultKey(vaultKey);
                System.out.print(" ...vault deleted");
            } catch (Exception e) {
                System.out.println("Something went wrong with that one. ");
                e.printStackTrace();
            }
            System.out.println();
        }

        System.out.println("DONE!!");
    }

    public List<String> vaultKey() {
        return new ArrayList<String>() {{
            add("HDEV208jh8WJwqzy8TIJovIUDmJ8SM7DInNLFZUsTy2INLFHNE");
        }};
    }

    public List<String> lookupVaultKeys() {
        StringBuilder sql = new StringBuilder();

        sql.append( "select externaldatakey from octrecipient where externaldatakey is not null");

        ISCRecordset rs = mgr.newQuery().executeSQL(sql.toString());

        List<String> vaultKeys = new ArrayList<String>();

        while( rs.moveNext() ) {
            String key = (String) rs.valueFromIndex(0);
            vaultKeys.add(key);
        }

        return vaultKeys;
    }

    public String vaultKeyToContractKey(String vaultKey) {
        Map<String, String> values = vaultClient.get(vaultKey);

        if (values == null || values.size() < 1) {
            return null;
        }

        String contractKey = contractClient.create(values);

        return contractKey;
    }

    public void swapRecipientKey(String vaultKey, String contractKey) {
        if (StringUtils.isNotBlank(contractKey) && StringUtils.isNotBlank(vaultKey)) {
            StringBuilder sql = new StringBuilder();

            sql.append("update octrecipient set ")
                    .append("FirstName = '").append(contractKey).append("', ")
                    .append("LastName = '").append(contractKey).append("', ")
                    .append("FullName = '").append(contractKey).append("', ")
                    .append("RecipientEmail = '").append(contractKey).append("', ")
                    .append("ManagerName = '").append(contractKey).append("', ")
                    .append("ManagerEmail = '").append(contractKey).append("', ")
                    .append("ExternalDataKey = '").append(contractKey).append("' ")
                    .append("where externalDataKey = '").append(vaultKey).append("' ");

            mgr.newQuery().executeSQLUpdate(sql.toString());
        }
    }

    public void deleteVaultKey(String vaultKey) {
        try {
            vaultClient.delete(vaultKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

