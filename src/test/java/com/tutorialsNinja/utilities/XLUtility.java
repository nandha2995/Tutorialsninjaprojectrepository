package com.tutorialsNinja.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

public static FileInputStream fi;
public static XSSFWorkbook wb;
public static XSSFSheet sheet;
public static XSSFRow row;
public static XSSFCell cell;
public static FileOutputStream fo;
XSSFCellStyle style;
String path = null;

public XLUtility(String path) {

this.path=path;
}

public int getRowCount(String sheetName) throws IOException {
fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
sheet = wb.getSheet(sheetName);
int rowCount = sheet.getLastRowNum();
wb.close();
fi.close();
return rowCount;
}

public int getCellCount(String sheetName, int rowNum) throws IOException {
fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
sheet = wb.getSheet(sheetName);
row = sheet.getRow(rowNum);
int cellCount = row.getLastCellNum();
wb.close();
fi.close();
return cellCount;
}

public String getCellData(String sheetName, int rowNum, int ColNum) throws IOException {
fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
sheet = wb.getSheet(sheetName);
row = sheet.getRow(rowNum);
cell = row.getCell(ColNum);
DataFormatter formatter = new DataFormatter();
String data;
try {
data = formatter.formatCellValue(cell);
} catch (Exception e) {
data = "";
}
wb.close();
fi.close();
return data;
}

public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
sheet = wb.getSheet(sheetName);
row = sheet.getRow(rowNum);
cell = row.createCell(colNum);
cell.setCellValue(data);

fo = new FileOutputStream(path);
wb.write(fo);
wb.close();
fi.close();
fo.close();
}

public void fillGreenColour(String sheetName, int rowNum, int colNum) throws IOException {
fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
sheet = wb.getSheet(sheetName);
row = sheet.getRow(rowNum);
cell = row.getCell(colNum);
style = wb.createCellStyle();
style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

cell.setCellStyle(style);
wb.write(fo);
wb.close();
fi.close();
fo.close();

}
}