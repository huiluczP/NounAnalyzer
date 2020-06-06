package filereader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

// 利用poi读取excal文件数据
public class FileInfoGetter {

    // demo直接将数据读成字符串集合,cellNum为数据所在列
    public ArrayList<String> readFile(String path, int cellNum){
        ArrayList<String> lines = new ArrayList<String>();
        InputStream is = null;

        //获取文件输入流信息
        try {
            is = new FileInputStream(new File(path));
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            //默认读取sheet0中数据
            Sheet xssfSheet=xssfWorkbook.getSheetAt(0);
            if (xssfSheet == null) {
                System.out.println("sheet data null");
                return lines;
            }

            //处理当前页，循环读取每一行
            //第一行默认为表头，不读
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                Row xssfRow = xssfSheet.getRow(rowNum);
                Cell cell = xssfRow.getCell(cellNum);
                String dataStr = cell.getStringCellValue();
                lines.add(dataStr);
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭文件流
        finally{
            try {
                if(is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}
