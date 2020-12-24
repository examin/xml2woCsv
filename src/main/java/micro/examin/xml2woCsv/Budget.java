package micro.examin.xml2woCsv;

public class Budget {
    private String mesrid;
    private String mesr_catg;
    private String type;
    private String agg;
    private String pb;
    private String cp;
    private String fv;
    private String formula;


    // Getter Methods

    public String getMesrid() {
        return mesrid;
    }

    public String getMesr_catg() {
        return mesr_catg;
    }

    public String getType() {
        return type;
    }

    public String getAgg() {
        return agg;
    }

    public String getPb() {
        return pb;
    }

    public String getCp() {
        return cp;
    }

    public String getFv() {
        return fv;
    }

    public String getFormula() {
        return formula;
    }

    // Setter Methods

    public void setMesrid(String mesrid) {
        this.mesrid = mesrid;
    }

    public void setMesr_catg(String mesr_catg) {
        this.mesr_catg = mesr_catg;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAgg(String agg) {
        this.agg = agg;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setFv(String fv) {
        this.fv = fv;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
