package micro.examin.xml2woCsv.AxsLogic_Temp.QueryServiceTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class TestQueryServiceApi5 {
    static final String QUERY_SERVICE_URL = "http://172.16.33.106:9230/query/measure";
    static final String OUTPUT_PATH = "/home/gaurav/Documents/QUERY_SERVICE_TEST.log";
    static final String SUBSCRIBERS[] = {"SA02", "CALA03"};


    static final String[] PRODUCTS = {"CC", "PL"};
    static final String[] PRODUCT_TYPES = {"ACQD", "PORTM", "PORTD"};
    static final String[] DAILY_PERIODS = {"month", "quater", "year", "day", "week", "mob"}; //todo half year
    static final String[] MONTHLY_PERIODS = {"month", "quater", "year", "mob"};
    static final String[] CC_DIMENSIONS = {"AGE_BND_FK", "AGNT_NAM_FK", "ANLY_FOLD_FK", "ANNL_INCM_BND_FK", "APPL_SCOR_BND_FK", "APR_BND_FK", "ASXX10_ASXY10_FK", "ASXX11_ASXY11_FK", "ASXX12_ASXY12_FK", "ASXX13_ASXY13_FK", "ASXX14_ASXY14_FK", "ASXX15_ASXY15_FK", "ASXX16_ASXY16_FK", "ASXX17_ASXY17_FK", "ASXX18_ASXY18_FK", "ASXX19_ASXY19_FK", "ASXX1_ASXY1_FK", "ASXX20_ASXY20_FK", "ASXX21_ASXY21_FK", "ASXX22_ASXY22_FK", "ASXX23_ASXY23_FK", "ASXX24_ASXY24_FK", "ASXX25_ASXY25_FK", "ASXX29_ASXY29_FK", "ASXX2_ASXY2_FK", "ASXX30_ASXY30_FK", "ASXX31_ASXY31_FK", "ASXX32_ASXY32_FK", "ASXX33_ASXY33_FK", "ASXX34_ASXY34_FK", "ASXX35_ASXY35_FK", "ASXX36_ASXY36_FK", "ASXX3_ASXY3_FK", "ASXX4_ASXY4_FK", "ASXX5_ASXY5_FK", "ASXX6_ASXY6_FK", "ASXX7_ASXY7_FK", "ASXX8_ASXY8_FK", "ASXX9_ASXY9_FK", "BANK_AGNT_NAM_FK", "BANK_BRAN_FK", "BANK_CITY_FK", "BANK_REGN_FK", "BEHV_SCOR_BND_FK", "BILL_CYCL_DAY_FK", "BT_INTR_FK", "BT_TENR_FK", "BT_TICK_FK", "BUR_NAM_FK", "CAMP_NAM_FK", "CAMP_TYPE_FK", "CARD_ASSC_FK", "CARD_TYPE_FK", "CHAN_NAM_FK", "CITY_FK", "CONT_FK", "CRED_LIMT_BND_FK", "CRED_OFFC_FK", "CUST_SEGM_FK", "DAY_FK", "DECS_STAT_FK", "DELN_FK", "DESG_FK", "DOCM_DU", "EDCT_LEVL_FK", "EMPL_NAM_FK", "EMPL_TYPE_FK", "EPP_INTR_FK", "EPP_TENR_FK", "EPP_TICK_FK", "EXCP_INDC", "EXCP_RESN_FK", "FIRS_PAYM_DEFL", "FRAD_CLAS_FK", "FRAD_RESN_FK", "GEND_FK", "HOM_OWNR_FK", "INDS_SEGM_FK", "LINE_VINT_LIST_MONT_FK", "LIN_INC_DEC_INDC_FK", "MART_STAT_FK", "MODL_FK", "MONT_AFTR_WO_FK", "MONT_ON_BOKS_FK", "MONT_SINC_LINE_CHAN_FK", "NATN_FK", "NEGT_FIL_FK", "NEGT_FIL_SORC_FK", "NUMB_OF_DEPN_FK", "PAYM_MOD_FK", "PROD_FK", "PROD_TYPE_FK", "PROF_FK", "REGN_FK", "REJC_RESN_FK", "REPR_CATG_FK", "RESD_PERD_BND_FK", "REWR_INDC", "REWR_TYPE_FK", "SCHE_TYPE_FK", "SECN_PAYM_DEFL", "SORC_CHAN_FK", "STAT_COD_FK", "STAT_FK", "STAT_VINT_LIST_MONT_FK", "TOP_UP", "VINT_FK", "VINT_LIST_MONT_FK", "WORK_PERD_BND_FK", "YES_NO_UNKN_FK", "ZIPC_FK"};
    static final String[] PL_DIMENSIONS = {"ACQS_DISB_STAT_FK", "ACQS_DISB_TYPE_FK", "AGE_BND_FK", "AGNT_NAM_FK", "ANLY_FOLD_FK", "ANNL_INCM_BND_FK", "APPL_SCOR_BND_FK", "APR_BND_FK", "ASXX10_ASXY10_FK", "ASXX11_ASXY11_FK", "ASXX12_ASXY12_FK", "ASXX13_ASXY13_FK", "ASXX14_ASXY14_FK", "ASXX15_ASXY15_FK", "ASXX16_ASXY16_FK", "ASXX17_ASXY17_FK", "ASXX18_ASXY18_FK", "ASXX19_ASXY19_FK", "ASXX1_ASXY1_FK", "ASXX20_ASXY20_FK", "ASXX21_ASXY21_FK", "ASXX22_ASXY22_FK", "ASXX23_ASXY23_FK", "ASXX24_ASXY24_FK", "ASXX25_ASXY25_FK", "ASXX2_ASXY2_FK", "ASXX31_ASXY31_FK", "ASXX33_ASXY33_FK", "ASXX34_ASXY34_FK", "ASXX35_ASXY35_FK", "ASXX36_ASXY36_FK", "ASXX37_ASXY37_FK", "ASXX3_ASXY3_FK", "ASXX4_ASXY4_FK", "ASXX5_ASXY5_FK", "ASXX6_ASXY6_FK", "ASXX7_ASXY7_FK", "ASXX8_ASXY8_FK", "ASXX9_ASXY9_FK", "BANK_AGNT_NAM_FK", "BANK_BRAN_FK", "BANK_CITY_FK", "BANK_REGN_FK", "BEHV_SCOR_BND_FK", "BILL_CYCL_DAY_FK", "BUR_NAM_FK", "CAMP_NAM_FK", "CAMP_TYPE_FK", "CHAN_NAM_FK", "CITY_FK", "CONT_FK", "CRED_OFFC_FK", "CUST_SEGM_FK", "DAY_FK", "DBR_BND_FK", "DECS_STAT_FK", "DELN_FK", "DESG_FK", "DOCM_DU", "EDCT_LEVL_FK", "EMPL_NAM_FK", "EMPL_TYPE_FK", "EXCP_INDC", "EXCP_RESN_FK", "FIRS_PAYM_DEFL", "FORC_STAT_FK", "FRAD_CLAS_FK", "FRAD_RESN_FK", "GEND_FK", "GURN_TAG_FK", "HOM_OWNR_FK", "INDS_SEGM_FK", "INTR_TYPE_FK", "LON_PURP_FK", "LON_TENR_BND_FK", "LON_VAL_BND_FK", "MART_STAT_FK", "MONT_AFTR_WO_FK", "MONT_ON_BOKS_FK", "NATN_FK", "NEGT_FIL_FK", "NEGT_FIL_SORC_FK", "NUMB_OF_DEPN_FK", "NUMB_OF_INST_FK", "PAYM_MOD_FK", "PORT_DISB_STAT_FK", "PORT_DISB_TYPE_FK", "PROD_FK", "PROD_TYPE_FK", "PROF_FK", "REGN_FK", "REJC_RESN_FK", "REMN_LON_TENR_BND_FK", "REPR_CATG_FK", "RESD_PERD_BND_FK", "REWR_INDC", "REWR_TYPE_FK", "SCHE_TYPE_FK", "SECN_PAYM_DEFL", "SORC_CHAN_FK", "STAT_COD_FK", "STAT_FK", "STAT_VINT_LIST_MONT_FK", "TOPUP_AMNT_BND_FK", "TOP_UP", "VINT_FK", "VINT_LIST_MONT_FK", "WORK_PERD_BND_FK", "YES_NO_UNKN_FK", "ZIPC_FK"};

    static final String[]  SA02_PL_ACQD_MEASUREIDS = {"50"};
    static final String[]  SA02_PL_PORTM_MEASUREIDS = {"50"};
    static final String[]  SA02_PL_PORTD_MEASUREIDS = {"50"};
    static final String[]  CALA03_PL_ACQD_MEASUREIDS = {"50"};
    static final String[]  CALA03_PL_PORTM_MEASUREIDS = {"50"};
    static final String[]  CALA03_PL_PORTD_MEASUREIDS = {"50"};


    static final String[]  SA02_CC_ACQD_MEASUREIDS = {"50"};
    static final String[]  SA02_CC_PORTM_MEASUREIDS = {"50"};
    static final String[]  SA02_CC_PORTD_MEASUREIDS = {"50"};
    static final String[]  CALA03_CC_ACQD_MEASUREIDS = {"50"};
    static final String[]  CALA03_CC_PORTM_MEASUREIDS = {"50"};
    static final String[]  CALA03_CC_PORTD_MEASUREIDS = {"50"};


    static final HashMap<String, String[]> PERIODS_MAP = new HashMap<String, String[]>() {{
        put("ACQD", DAILY_PERIODS);
        put("PORTM", MONTHLY_PERIODS);
        put("PORTD", DAILY_PERIODS);
    }};
    static final HashMap<String, String[]> DIMENSIONS_MAP = new HashMap<String, String[]>() {{
        put("CC", CC_DIMENSIONS);
        put("PL", PL_DIMENSIONS);
    }};
    static final HashMap<String, String[]> MEASUREID_MAP = new HashMap<String, String[]>() {{
        put("SA02CCACQD", SA02_CC_ACQD_MEASUREIDS);
        put("SA02CCPORTM",SA02_CC_PORTM_MEASUREIDS);
        put("SA02CCPORTD", SA02_CC_PORTD_MEASUREIDS);
        put("SA02PLACQD", SA02_PL_ACQD_MEASUREIDS);
        put("SA02PLPORTM", SA02_PL_PORTM_MEASUREIDS);
        put("SA02PLPORTD", SA02_PL_PORTD_MEASUREIDS);

        put("CALA03CCACQD", CALA03_CC_ACQD_MEASUREIDS);
        put("CALA03CCPORTM",CALA03_CC_PORTM_MEASUREIDS);
        put("CALA03CCPORTD", CALA03_CC_PORTD_MEASUREIDS);
        put("CALA03PLACQD", CALA03_PL_ACQD_MEASUREIDS);
        put("CALA03PLPORTM", CALA03_PL_PORTM_MEASUREIDS);
        put("CALA03PLPORTD", CALA03_PL_PORTD_MEASUREIDS);
    }};
    

    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter(OUTPUT_PATH);
            BufferedWriter bw = new BufferedWriter(writer)) {
            URL url = new URL(QUERY_SERVICE_URL);
            for (String subscriber : SUBSCRIBERS) {
                for (String product : PRODUCTS) {
                    for (String productType : PRODUCT_TYPES) {
                        String[] periods = PERIODS_MAP.get(productType.toUpperCase());
                        String[] dimensions = DIMENSIONS_MAP.get(product.toUpperCase());
                        String[] measureIds = MEASUREID_MAP.get((subscriber+product+productType).toUpperCase());
                        for(String measureId: measureIds) {
                            for (String dimension : dimensions) {
                                for (String period : periods) {
                                    String requestBody = "{\r\n\"subscriber\": \"" + subscriber + "\",\r\n\"product\": \"" + product + "\",\r\n\"productType\": \"" + productType + "\",\r\n\"dimension\": [\"" + dimension + "\"],\r\n\"measureIds\": [" + measureId + "],\r\n\"filters\": [],\r\n\"pivot\":\"true\",\r\n\"period\":\"" + period + "\",\r\n\"vintagePeriod\":\"\"\r\n}\r\n";
                                    //System.out.println(requestBody);
                                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                                    con.setConnectTimeout(60000);
                                    con.setRequestMethod("POST");
                                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                                    con.setRequestProperty("Accept", "application/json");

                                    con.setDoOutput(true);
                                    try (OutputStream os = con.getOutputStream()) {
                                        byte[] input = requestBody.getBytes("utf-8");
                                        os.write(input, 0, input.length);
                                    }
                                    int responseCode = con.getResponseCode();
                                    try {
                                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                                        StringBuilder response = new StringBuilder();
                                        String responseLine = null;
                                        while ((responseLine = br.readLine()) != null) {
                                            response.append(responseLine.trim());
                                        }
                                        bw.write(subscriber + "|" + product + "|" + productType + "|" + measureId + "|" + period + "|" + dimension + "|" + responseCode + "|" + response + "\n");
                                    } catch (IOException e) {
                                        bw.write(subscriber + "|" + product + "|" + productType + "|" + measureId + "|" + period + "|" + dimension + "|" + "error" + "\n");
                                    }
                                    System.out.println("yes");
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.format("IOException: %s%n", e);
        }
    }

}

