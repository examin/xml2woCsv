package micro.examin.xml2woCsv;

public class GetJson {
    Alert_desc Alert_descObject;
    private String alert_name;
    private String lowr_limt;
    private String uppr_limt;
    private String comp_type;
    private String cap_track_flag;
    private String alrt_flag;
    private String report_key;
    private String report_name;


    // Getter Methods

    public Alert_desc getAlert_desc() {
        return Alert_descObject;
    }

    public String getAlert_name() {
        return alert_name;
    }

    public String getLowr_limt() {
        return lowr_limt;
    }

    public String getUppr_limt() {
        return uppr_limt;
    }

    public String getComp_type() {
        return comp_type;
    }

    public String  getCap_track_flag() {
        return cap_track_flag;
    }

    public String getAlrt_flag() {
        return alrt_flag;
    }

    public String getReport_key() {
        return report_key;
    }

    public String getReport_name() {
        return report_name;
    }

    // Setter Methods

    public void setAlert_desc(Alert_desc alert_descObject) {
        this.Alert_descObject = alert_descObject;
    }

    public void setAlert_name(String alert_name) {
        this.alert_name = alert_name;
    }

    public void setLowr_limt(String lowr_limt) {
        this.lowr_limt = lowr_limt;
    }

    public void setUppr_limt(String uppr_limt) {
        this.uppr_limt = uppr_limt;
    }

    public void setComp_type(String comp_type) {
        this.comp_type = comp_type;
    }

    public void setCap_track_flag(String cap_track_flag) {
        this.cap_track_flag = cap_track_flag;
    }

    public void setAlrt_flag(String alrt_flag) {
        this.alrt_flag = alrt_flag;
    }

    public void setReport_key(String report_key) {
        this.report_key = report_key;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }
}

