package micro.examin.xml2woCsv.AxsLogic_Temp.QueryServiceTest;


import java.util.List;

public class EsQueryRequest {

    private String subscriber;
    private String product;
    private String productType;
    private String cognosIP;
    private List<String> dimension;
    private List<String> measureIds;
    private List<QueryFilter> filters;
    private String period;
    private String vintagePeriod;
    private boolean pivot;

    public EsQueryRequest(String subscriber, String product, String productType, String cognosIP, List<QueryFilter> filters){
        this.subscriber = subscriber;
        this.product = product;
        this.productType = productType;
        this.cognosIP = cognosIP;
        this.filters = filters;
    }

    public EsQueryRequest(String subscriber, String product, String productType, String cognosIP
            , List<String> dimension, List<String> measureIds, List<QueryFilter> filters, String period
            , boolean pivot, String vintagePeriod) {
        this.subscriber = subscriber;
        this.product = product;
        this.productType = productType;
        this.cognosIP = cognosIP;
        this.dimension = dimension;
        this.measureIds = measureIds;
        this.filters = filters;
        this.period = period;
        this.pivot = pivot;
        this.vintagePeriod = vintagePeriod;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCognosIP() {
        return cognosIP;
    }

    public void setCognosIP(String cognosIP) {
        this.cognosIP = cognosIP;
    }

    public List<String> getDimension() {
        return dimension;
    }

    public void setDimension(List<String> dimension) {
        this.dimension = dimension;
    }

    public List<String> getMeasureIds() {
        return measureIds;
    }

    public void setMeasureIds(List<String> measureIds) {
        this.measureIds = measureIds;
    }

    public List<QueryFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<QueryFilter> filters) {
        this.filters = filters;
    }

    public boolean isPivot() {
        return pivot;
    }

    public void setPivot(boolean pivot) {
        this.pivot = pivot;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getVintagePeriod() {
        return vintagePeriod;
    }

    public void setVintagePeriod(String vintagePeriod) {
        this.vintagePeriod = vintagePeriod;
    }

    @Override
    public String toString() {
        return "EsQueryRequest{" +
                "subscriber='" + subscriber + '\'' +
                ", product='" + product + '\'' +
                ", productType='" + productType + '\'' +
                ", cognosIP='" + cognosIP + '\'' +
                ", dimension=" + dimension +
                ", measureIds=" + measureIds +
                ", filters=" + filters.toString() +
                ", period='" + period + '\'' +
                ", pivot=" + pivot +
                ", vintagePeriod='" + vintagePeriod + '\'' +
                '}';
    }
}
