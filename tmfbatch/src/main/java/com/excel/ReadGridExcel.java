// ======================================================================
// Package Name    : DevelopFrameWork
// Class Name      : ReadGridExcel
// Creation Date   : 2021/08/03
// ======================================================================
package com.excel;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excelの一覧表をメモリに読み込む。.xlsxに対応。
 * 
 * @author Yasuhiro Harada
 * @version 1.0.0
 */
@Getter
@Setter
public class ReadGridExcel {

    private XSSFRow currentRow = null;
    private int firstRow;
    private int firstColumn;

    /**
     * Excelの1行のデータを格納する構造体
     */
    public class RowData {
        public int rowIndex;
        public List<CellData> cellData;
    }

    public class CellData {
        public String cellReference;
        public String dataType;
        public String value;
    }

    /**
     * Constructor
     * @param path：読み込むパス付ファイル名
     * @param sheetName：読み込むシート名
     * @param firstRow：読み込む最初の行(0スタート)
     * @param firstColumn：読み込む最初の列(0スタート)
     * @throws Exception
     */
    public ReadGridExcel(String path, String sheetName, int firstRow, int firstColumn) throws Exception
    {
        
        this.firstRow = firstRow;
        this.firstColumn = firstColumn;

        XSSFWorkbook xssWorkBook = new XSSFWorkbook(new FileInputStream(path));

        XSSFSheet xssfSheet = xssWorkBook.getSheet(sheetName);
        currentRow = xssfSheet.getRow(firstRow);
    
    }

    /*
    public int moveFirstRow()
    {
        try
        {
            int return = -1;

            currentRow = ien_Row.GetEnumerator();

            //無限ループさせるが必ずどこかで抜けさせる
            while (ien_CurrentRow.MoveNext())
            {
                if (ien_CurrentRow.Current.RowIndex >= int_FirstRow)
                {
                    int_Return = 0;
                    break;
                }
            }

            return int_Return;
        }
        catch (Exception)
        {
            throw;
        }
}
*/
 }