package micro.examin.xml2woCsv.AxsLogic_Temp;

public class RollTry4 {

	public static void main(String[] args) {
		//$1$ //$2$ ("doc[''DELN_FK''].value == 2") // $3$ //$4$ //$5$ (doc['PREV_DAY_DELN_FK'].value == 2)
		String[][] map = {
				{"CCD2071", "2", "_1_1_", "(1 TO 1)"},
				{"CCD2104", "3", "_2_2_", "(2 TO 2)"},
				{"CCD2130", "4", "_3_3_", "(3 TO 3)"},
				{"CCD2061", "5", "_4_4_", "(4 TO 4)"},
				{"CCD2042", "6", "_5_5_", "(5 TO 5)"},
				{"CCD2119", "7", "_6_6_", "(6 TO 6)"},
				{"CCD2127", "8", "_7_7_", "(7 TO 7)"},
				{"CCD2074", "9", "_8_8_", "(8 TO 8)"}
		};
//		System.out.println(org);
		for (String[] curr : map) {
			String imp = org;
			imp = imp.replace("$1$", curr[0]);
			imp = imp.replace("$2$", curr[1]);
			imp = imp.replace("$3$", curr[2]);
			imp = imp.replace("$4$", curr[3]);

			System.out.println(imp.replace("__","_"));
			System.out.println("####################################\n");
		}
	}

	public static String org = "SELECT * FROM SA02.MEASURE where parentid = '$1$'\n" +
			"\n" +
			"\n" +
			"if(doc['ACCN_IN_FORC'].value > 0 && doc['DELN_FK'].value == $2$ && doc['PREV_DAY_DELN_FK'].value == $2$) { return doc['NO_OF_ALL_ACCN'].value } else { return 0 }\t\t\n" +
			"/\n" +
			"if(doc['ACCN_IN_FORC'].value > 0 && doc['PREV_DAY_ACCN_IN_FORC'].value > 0 && doc['PREV_DAY_DELN_FK'].value == $2$) { return doc['NO_OF_ALL_ACCN'].value } else { return 0 }\t\n" +
			"\n" +
			"\n" +
			"update SA02.MEASURE set parentid = '' where measureid = '$1$';\n" +
			"\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_STABILIZED$3$','SA02','Accounts Stabilized $4$', 'ANNL_INCM_BND_FK','Sum','params.SA02_CS_STABILIZED$3$_A / params.SA02_CS_STABILIZED$3$_B','SA02_CS_STABILIZED$3$_A,SA02_CS_STABILIZED$3$_B','C','$1$',1,'cc','portd',0,'TRUE','TRUE','RollRateSt');\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_STABILIZED$3$_A','SA02','Accounts Stabilized $4$ - Nu', 'ANNL_INCM_BND_FK','Sum','params.SA02_S_STABILIZED$3$_NU_A PRESUBSET params.SA02_S_STABILIZED$3$_NU_B','SA02_S_STABILIZED$3$_NU_A,SA02_S_STABILIZED$3$_NU_B','A','STABILIZEDNU$3$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_STABILIZED$3$_NU_A','SA02','if(doc[''DELN_FK''].value == $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SSTABILIZEDNUA01',1,'D');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_STABILIZED$3$_NU_B','SA02','if(doc[''ACCN_IN_FORC''].value > 0 && doc[''DELN_FK''].value == $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SSTABILIZEDNUB$3$',1,'D');\n" +
			"\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_STABILIZED$3$_B','SA02','Accounts Stabilized $4$ - De', 'ANNL_INCM_BND_FK','Sum','params.SA02_S_STABILIZED$3$_DE_A PRESUBSET params.SA02_S_STABILIZED$3$_DE_B','SA02_S_STABILIZED$3$_DE_A,SA02_S_STABILIZED$3$_DE_B','A','STABILIZEDDE$3$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_STABILIZED$3$_DE_A','SA02','if(doc[''ACCN_IN_FORC''].value > 0 && doc[''DELN_FK''].value == $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SSTABILIZEDDEA$3$',1,'D');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_STABILIZED$3$_DE_B','SA02','if(doc[''ACCN_IN_FORC''].value > 0 ) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SSTABILIZEDDEB$3$',1,'D');\n";
}