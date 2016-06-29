
public class SQLiteExUtils {
	public static String[] getColumnNameArray(ResultSet rs) {
		String sArr[] = null;
		try {
			ResultSetMetaData rm = rs.getMetaData();
			String sArray[] = new String[rm.getColumnCount()];
			for (int ctr = 1; ctr <= sArray.length; ctr++) {
				String s = rm.getColumnName(ctr);
				sArray[ctr - 1] = s;
			}
			return sArray;
		} catch (Exception e) {
			System.out.println(e);
			return sArr;
		}
	}

        public static String[] getColumnTypeArray(ResultSet rs) {
                String sArr[] = null;
                try {
                        ResultSetMetaData rm = rs.getMetaData();
                        String sArray[] = new String[rm.getColumnCount()];
                        for (int ctr = 1; ctr <= sArray.length; ctr++) {
                                String s = rm.getColumnTypeName(ctr);
                                sArray[ctr - 1] = s;
                        }
                        return sArray;
                } catch (Exception e) {
                        System.out.println(e);
                        return sArr;
                }
        }

	public static void getColumnMetaData(Connection conn, String tableName) {
		String sql = "select * from "+tableName;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String[] colNames = getColumnNameArray(rs);
			String[] colTypes = getColumnTypeArray(rs);

			while(rs.next()) {
				for (int i = 1; i < columnNames.length; i++) {
					Object o = rs.getObject(colNames[i]);
					if (colTypes[i].toUpper().equals("VARCHAR")) {
						String sVal = o.toString();
					} else if (colTypes[i].toUpper().equals("TIMESTAMP")) {
					} else if (colTypes[i].toUpper().equals("TIME")) {
					} else if (colTypes[i].toUpper().equals("DATE")) {
					} else if (colTypes[i].toUpper().equals("INTEGER")) {
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
