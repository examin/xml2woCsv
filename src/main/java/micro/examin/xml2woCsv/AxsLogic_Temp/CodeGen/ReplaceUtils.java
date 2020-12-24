package micro.examin.xml2woCsv.AxsLogic_Temp.CodeGen;

import java.util.ArrayList;

public class ReplaceUtils {

	String PRODUCTType;
	String PRODUCTTypefull;
	String productTypefull;
	String productType;
	String Type;
	String product;
	String productSubType;
	String type;
	String typefull;
	String configFieldStr;
	String writerFieldPropertyStr;
	String writerFieldTypeStr;
	String dataString;
	String getterSetterString;

	public ReplaceUtils(String PRODUCT, String TYPE, String productSubType, ArrayList<String> productColumnsTypes, ArrayList<String> productColumnsNames, ArrayList<String> productColumnsESTypes) {

		this.product = PRODUCT.toLowerCase();
		this.type = TYPE.toLowerCase();
		this.typefull =this.type+productSubType.toLowerCase();
		if (TYPE.length() > 1) {
			this.Type = TYPE.substring(0, 1).toUpperCase() + TYPE.substring(1).toLowerCase();
		} else throw new IllegalArgumentException();
		PRODUCTType = PRODUCT + Type;

		productType = product + Type;
		if(TYPE.equals("PORT")) {
			this.productSubType = productSubType;
		}else this.productSubType ="";

		PRODUCTTypefull = PRODUCT + (Type)+this.productSubType;
		productTypefull = product + (Type)+this.productSubType;

		configFieldStr = getConfigFieldStr(productColumnsNames);
		writerFieldTypeStr = getWriterFieldTypeStr(productColumnsNames);


		writerFieldPropertyStr = getWriterFieldPropertyStr(productColumnsNames,productColumnsESTypes);
		if ((productColumnsTypes.size() != productColumnsNames.size()) || (productColumnsTypes.size() != productColumnsESTypes.size())) {
			throw new IllegalArgumentException("All List for product column names are not equal");
		}


		dataString = getDataString(productColumnsNames);
		getterSetterString = getGetterSetterString(productColumnsNames, productColumnsTypes);
	}

	private String getConfigFieldStr(ArrayList<String> productColumnsNames) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		int itr = 0;
		for(String str :productColumnsNames){
			if(itr%5==0){
				sb.append(str+"\",\n\t\t\"");
			}
			else{
				sb.append(str+"\",\"");
			}
			itr++;
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	private String getWriterFieldTypeStr(ArrayList<String> productColumnsNames) {
		if (productColumnsNames.size() > 1) {
			//this.configFieldStr = ('"' + String.join("\",\"", productColumnsNames) + '"');
		} else throw new IllegalArgumentException();
		StringBuilder writerFieldsb = new StringBuilder();
		for (String name : productColumnsNames) {
			writerFieldsb.append(String.format("\n\t\t\t\t\t.field(\"%s\", item.get%s())", name, name));
		}
		return writerFieldsb.toString();
	}

	private String getWriterFieldPropertyStr(ArrayList<String> productColumnsNames, ArrayList<String> productColumnsESTypes) {

		StringBuilder writerFieldPropertysb = new StringBuilder();
		int esTypeIter = 0;
		for (String name : productColumnsNames) {
			writerFieldPropertysb.append(String.format("\n\t\t\tproperties.put(\"%s\", %s);", name, productColumnsESTypes.get(esTypeIter)));
			esTypeIter++;
		}
		return writerFieldPropertysb.toString();
	}

	private String getDataString(ArrayList<String> productColumnsNames) {
		StringBuilder sb = new StringBuilder();
		for (String columnName : productColumnsNames) {
			sb.append(String.format("\tpublic String %s ;\n", columnName));
		}
		return sb.toString();
	}

	private String getGetterSetterString(ArrayList<String> productColumnsNames, ArrayList<String> productColumnsTypes) {
		StringBuilder sb = new StringBuilder();
		int typeIter = 0;

		for (String columnName : productColumnsNames) {
			String type = productColumnsTypes.get(typeIter);
			sb.append(("\n\tpublic String get/*field*/(){\n\t\t return /*field*/ ;\n\t}")
					.replace("/*field*/", columnName));
			sb.append(("\n\tpublic void set/*field*/ (String /*field*/){\n\t\t this./*field*/ = Utils.getValid/*fieldtype*/(/*field*/) ;\n\t}\n")
					.replace("/*field*/", columnName)
					.replace("/*fieldtype*/", Character.toUpperCase(type.charAt(0))+type.substring(1)));
			typeIter++;
		}
		return sb.toString();
	}

	public void print() {
		System.out.println( "ReplaceUtils{" +
				"PRODUCTType='" + PRODUCTType + '\'' +
				", PRODUCTTypefull='" + PRODUCTTypefull + '\'' +
				", productTypefull='" + productTypefull + '\'' +
				", productType='" + productType + '\'' +
				", Type='" + Type + '\'' +
				", product='" + product + '\'' +
				", productSubType='" + productSubType + '\'' +
				", type='" + type + '\'' +
				", typefull='" + typefull+
				'}');
		System.out.println("");
	}
}
