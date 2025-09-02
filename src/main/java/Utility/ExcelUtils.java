package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {
	


    private static XSSFWorkbook workbook;

    public static List<Map<String, String>> getTestDetails(String sheetname) {
        List<Map<String, String>> list = null;
        FileInputStream fis = null;
        try {
        	String ExcelPath = System.getProperty("user.dir") + "\\TestData\\OrangeHRMTestData.xlsx";
          fis = new FileInputStream(ExcelPath);
            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetname);

            list = new ArrayList<>();
            int lastrownum = sheet.getLastRowNum();
            int lastcolnum = sheet.getRow(0).getLastCellNum();

            for (int i = 1; i <= lastrownum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue; // skip empty rows

                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < lastcolnum; j++) {
                    Cell keyCell = sheet.getRow(0).getCell(j);
                    String Key = getCellContentAsString(keyCell);

                    Cell value = row.getCell(j);
                    String ValueOfKey = getCellContentAsString(value);

                    map.put(Key, ValueOfKey);
                }
                list.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(fis)) {
                    fis.close();
                }
				/*
				 * if (Objects.nonNull(workbook)) { workbook.close(); }
				 */
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     * Returns cell data by converting it to String
     */
    private static String getCellContentAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        evaluator.evaluateInCell(cell);  // modifies cell type in POI 3.9

        String cellData = "";

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                cellData = "";
                break;
            case Cell.CELL_TYPE_STRING:
                cellData = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date dateValue = cell.getDateCellValue();
                    String df = cell.getCellStyle().getDataFormatString();
                    cellData = new CellDateFormatter(df).format(dateValue);
                } else {
                    cellData = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                int cachedType = cell.getCachedFormulaResultType();
                if (cachedType == Cell.CELL_TYPE_NUMERIC) {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date dateValue = cell.getDateCellValue();
                        String df = cell.getCellStyle().getDataFormatString();
                        cellData = new CellDateFormatter(df).format(dateValue);
                    } else {
                        cellData = NumberToTextConverter.toText(cell.getNumericCellValue());
                    }
                } else if (cachedType == Cell.CELL_TYPE_STRING) {
                    cellData = cell.getRichStringCellValue().getString();
                } else if (cachedType == Cell.CELL_TYPE_BOOLEAN) {
                    cellData = String.valueOf(cell.getBooleanCellValue());
                } else {
                    cellData = "";
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellData = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                cellData = "";
                break;
        }

        return cellData;
    }
}
