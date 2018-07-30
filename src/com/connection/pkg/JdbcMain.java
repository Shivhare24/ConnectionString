package com.connection.pkg;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;

public class JdbcMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@Admin:1521:xe","shubham","password");
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Employee");
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("data.xlsx");
			HSSFRow rowhead = sheet.createRow(0);
			rowhead.createCell(0).setCellValue("ID");
			rowhead.createCell(1).setCellValue("Name");
			rowhead.createCell(2).setCellValue("salary");
			rowhead.createCell(3).setCellValue("Dept");
			
			int index = 1;
			while(rs.next()) {
				HSSFRow row = sheet.createRow(index);
				row.createCell(0).setCellValue(rs.getInt(1));
				row.createCell(1).setCellValue(rs.getString(2));
				row.createCell(2).setCellValue(rs.getInt(3));
				row.createCell(3).setCellValue(rs.getString(4));
				index++;
				}
			
			workbook.write(new FileOutputStream("data.xlsx"));
			workbook.close();
		}
		catch (Exception e) {
			e.printStackTrace();
	        }
		finally {
			conn.close();
		}
    }
}
