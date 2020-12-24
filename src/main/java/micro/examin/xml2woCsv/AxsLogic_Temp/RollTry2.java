package micro.examin.xml2woCsv.AxsLogic_Temp;

public class RollTry2 {

	public static void main(String[] args) {
		//$1$ //$2$ ("doc[''DELN_FK''].value == 2") // $3$ //$4$ //$5$ (doc['PREV_DAY_DELN_FK'].value == 2)
		String[][] map = {
				{"CCD2160","2","_1_0_","(1 TO 0)"},
				{"CCD2114","3","_2_1_","(2 TO 1)"},
				{"CCD2066","4","_3_2_","(3 TO 2)"},
				{"CCD2106","5","_4_3_","(4 TO 3)"},
				{"CCD2135","6","_5_4_","(5 TO 4)"},
				{"CCD2052","7","_6_5_","(6 TO 5)"},
				{"CCD2057","8","_7_6_","(7 TO 6)"},
				{"CCD2120","9","_8_7_","(8 TO 7)"}
		};
//		System.out.println(org);
		for(String[] curr : map){
			String imp = org;
			imp = imp.replace("$1$",curr[0]);
			imp = imp.replace("$2$",curr[1]);
			imp = imp.replace("$3$",curr[2]);
			imp = imp.replace("$4$",curr[3]);
			if(curr[1].equals(2)){
				imp.replace("< 2 ","== 1");
			}
			System.out.println(imp);
			System.out.println("####################################\n");
		}
	}
	public static String org = "SELECT * FROM SA02.MEASURE where parentid = '$1$'\n" +
			"\n" +
			"\n" +
			"if(doc['ACCN_IN_FORC'].value > 0 && doc[''DELN_FK''].value < $2$ && doc['PREV_DAY_DELN_FK'].value == $2$) { return doc['NO_OF_ALL_ACCN'].value } else { return 0 }\n" +
			"/\n" +
			"if(doc['ACCN_IN_FORC'].value > 0 && doc['PREV_DAY_ACCN_IN_FORC'].value > 0 && doc['PREV_DAY_DELN_FK'].value == $2$) { return doc['NO_OF_ALL_ACCN'].value } else { return 0 }\t\n" +
			"\n" +
			"\n" +
			"-------------------------------------\n" +
			"\n" +
			"update SA02.MEASURE set parentid = '' where parentid = '$1$';\n" +
			"\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_ROLLBACK$3$','SA02','Accounts Roll Back $4$', 'ANNL_INCM_BND_FK','Sum','params.SA02_CS_ROLLBACK$3$_A / params.SA02_CS_ROLLBACK$3$_B','SA02_CS_ROLLBACK$3$_A,SA02_CS_ROLLBACK$3$_B','C','$1$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_ROLLBACK$3$_A','SA02','Accounts Roll Back $4$ - Nu', 'ANNL_INCM_BND_FK','Sum','params.SA02_S_ROLLBACK$3$_NU_A PRESUBSET params.SA02_S_ROLLBACK$3$_NU_B','SA02_S_ROLLBACK$3$_NU_A,SA02_S_ROLLBACK$3$_NU_B','A','ROLLBACKNU$3$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLBACK$3$_NU_A','SA02','if(doc[''DELN_FK''].value == $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLBACKNUA$3$',1,'D');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLBACK$3$_NU_B','SA02','if(doc[''ACCN_IN_FORC''].value > 0 && doc[''DELN_FK''].value < $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLBACKNUB$3$',1,'D');\n" +
			"\n" +
			"\n" +
			"insert into SA02.MEASURE values ('SA02_CS_ROLLBACK$3$_B','SA02','Accounts Roll Back $4$ - De', 'ANNL_INCM_BND_FK','Sum','params.SA02_S_ROLLBACK$3$_DE_A PRESUBSET params.SA02_S_ROLLBACK$3$_DE_B','SA02_S_ROLLBACK$3$_DE_A,SA02_S_ROLLBACK$3$_DE_B','A','ROLLBACKDE$3$',1,'cc','portd',0,'TRUE','TRUE','RollRate');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLBACK$3$_DE_A','SA02','if(doc[''ACCN_IN_FORC''].value > 0 && doc[''DELN_FK''].value == $2$) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLBACKDEA$3$',1,'D');\n" +
			"\n" +
			"insert into SA02.STOREDSCRIPT values ('SA02_S_ROLLBACK$3$_DE_B','SA02','if(doc[''ACCN_IN_FORC''].value > 0 ) { return doc[''NO_OF_ALL_ACCN''].value } else { return 0 }','SROLLBACKDEB$3$',1,'D');\n";
}
