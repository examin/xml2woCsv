package micro.examin.xml2woCsv.AxsLogic_Temp.QueryServiceTest;


/**
 * @author GGoel
 * 
 */
public class QueryFilter {
	private String field;
	private String value;
	private Boolean in;

	
	/**
	 * 
	 */
	public QueryFilter() {
		super();
	}

	/**
	 * @param field
	 * @param value
	 * @param in
	 */
	public QueryFilter(String field, String value, Boolean in) {
		super();
		this.field = field;
		this.value = value;
		this.in = in;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getIn() {
		return in;
	}

	public void setIn(Boolean in) {
		this.in = in;
	}

	@Override
	public String toString() {
		return "QueryFilter{" +
				"field='" + field + '\'' +
				", value='" + value + '\'' +
				", in=" + in +
				'}';
	}
}
