package com.ECA.utils;

import com.ECA.Exception.ObjectNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class UploadExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadExcelUtil.class);

    private static final String XLS_HEADER_PARSING_FAILED = "Please upload a valid file.(Header Mismatch)";


    protected static Workbook getWorkbook(MultipartFile multipartFile, String excelFilePath) throws IOException {
        Workbook workbook;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(multipartFile.getInputStream());
        } else {
            throw new IllegalArgumentException("Please upload xls or xlsx  file only.");
        }
        return workbook;
    }

    protected static Object getCellValue(Cell cell) {
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        return null;
    }

    protected static void matchHeadersExcel(List<Object> headersList, List<Object> fixedHeadersList)
            throws ObjectNotFoundException {
        if (!isEmpty(headersList) && !isEmpty(fixedHeadersList)
                && headersList.size() != fixedHeadersList.size()) {
            throw new ObjectNotFoundException(XLS_HEADER_PARSING_FAILED + " No of columns mismatched."
                    + "Expected - " + fixedHeadersList.size() + " In File - " + headersList.size(), HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < fixedHeadersList.size(); i++) {
                String headerElement = (String) headersList.get(i);
                String fixedHeaderElement = (String) fixedHeadersList.get(i);

                if (!(headerElement.trim()).equalsIgnoreCase(fixedHeaderElement.trim())) {
                    throw new ObjectNotFoundException(XLS_HEADER_PARSING_FAILED + "Expected - " + fixedHeaderElement +
                            " In File - " + headerElement, HttpStatus.NOT_FOUND);
                }
            }
        }

    }

    private static boolean isEmpty(List<Object> listObject) {
        return listObject == null || listObject.isEmpty();
    }
}
