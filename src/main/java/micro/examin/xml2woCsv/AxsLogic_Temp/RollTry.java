package micro.examin.xml2woCsv.AxsLogic_Temp;

public class RollTry {

	public static void main(String[] args) {
		//$1$ //$2$ ("doc[''DELN_FK''].value == 2") // $3$ //$4$ //$5$ (doc['PREV_DAY_DELN_FK'].value == 2)
		String[][] map = {
				{"CCD2179","2","_0_1_","(1 TO 0)"},
				{"CCD2180","3","_0_2_","(2 TO 0)"},
				{"CCD2181","4","_0_3_","(3 TO 0)"},
				{"CCD2182","5","_0_4_","(4 TO 0)"},
				{"CCD2183","6","_0_5_","(5 TO 0)"},
				{"CCD2184","7","_0_6_","(6 TO 0)"},
				{"CCD2185","8","_0_7_","(7 TO 0)"},
				{"CCD2186","9","_0_8_","(8 TO 0)"},
				{"CCD2187","10","_0_9_","(9 TO 0)"},
				{"CCD2188","11","_0_10_","(10 TO 0)"},
				{"CCD2189","12","_0_11_","(11 TO 0)"},
				{"CCD2190","13","_0_12_","(12 TO 0)"},
				{"CCD2191","14","_0_13_","(13 TO 0)"},
		};
//		System.out.println(org);
		for(String[] curr : map){
			String imp = org;
			imp = imp.replace("$1$",curr[0]);
			imp = imp.replace("$2$","doc[''DELN_FK''].value == "+curr[1]);
			imp = imp.replace("$3$",curr[2]);
			imp = imp.replace("$4$",curr[3]);
			imp = imp.replace("$5$","doc['PREV_DAY_DELN_FK'].value == "+curr[1]);
			imp = imp.replace("$6$",curr[2].replace("_",""));
			System.out.println(imp);
			System.out.println("####################################\n");
		}
	}
	public static String org = "if(doc['ACCN_IN_FORC'].value > 0 && doc['DELN_FK'].value == 1 && $5$) { return doc['NO_OF_ALL_ACCN'].value } else { return 0 }\t\n" +
			"/\n" +
			"if(doc['ACCN_IN_FORC'].value > 0 && doc['PREV_DAY_ACCN_IN_FORC'].value > 0 && $5$) { return doc['NO_OF_ALL_ACCN'].value } else { return 0 }\t\n" +
			"\n" +
			"\n" +
			"update SA02.MEASURE set parentid = '' where measureid = '$1$';\n" +
			"\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_ROLLRATE$3$','SA02','Accounts Normalized $4$', 'ANNL_INCM_BND_FK','Sum','params.SA02_CS_ROLLRATE$3$A / params.SA02_CS_ROLLRATE$3$B','SA02_CS_ROLLRATE$3$A.SA02_CS_ROLLRATE$3$B','C','$1$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_ROLLRATE$3$A','SA02','Accounts Normalized $4$ - Nu', 'ANNL_INCM_BND_FK','Sum','params.SA02_S_ROLLRATE$3$NU_A PRESUBSET params.SA02_S_ROLLRATE$3$NU_B','SA02_S_ROLLRATE$3$NU_A,SA02_S_ROLLRATE$3$NU_B','A','ROLLRATENU$6$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLRATE$3$NU_A','SA02','if($2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLRATENUA$6$',1,'D');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLRATE$3$NU_B','SA02','if(doc[''ACCN_IN_FORC''].value > 0 && doc[''DELN_FK''].value == 1) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLRATENUB$6$',1,'D');\n" +
			"\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_ROLLRATE$3$B','SA02','Accounts Normalized $4$ - De', 'ANNL_INCM_BND_FK','Sum','params.SA02_S_ROLLRATE$3$DE_A PRESUBSET params.SA02_S_ROLLRATE$3$DE_B','SA02_S_ROLLRATE$3$DE_A,SA02_S_ROLLRATE$3$DE_B','A','ROLLRATEDE$6$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLRATE$3$DE_A','SA02','if(doc[''ACCN_IN_FORC''].value > 0 && $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLRATEDEA$6$',1,'D');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLRATE$3$DE_B','SA02','if(doc[''ACCN_IN_FORC''].value > 0 ) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLRATEDEB$6$',1,'D');\n";
}
