package com.winter;

//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.util.*;

public class ReadExcel {
    public static void main(String[] args) {

//        try {
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


    }
//
//
//    /**
//     * 读取Excel文件的内容
//     *
//     * @param inputStream excel文件，以InputStream的形式传入
//     * @param sheetName   sheet名字
//     * @return 以List返回excel中内容
//     */
//    public static List<Map<String, String>> readExcel(InputStream inputStream, String sheetName) {
//
//        //定义工作簿
//        XSSFWorkbook xssfWorkbook = null;
//        try {
//            xssfWorkbook = new XSSFWorkbook(inputStream);
//        } catch (Exception e) {
//            System.out.println("Excel data file cannot be found!");
//        }
//
//        //定义工作表
//        XSSFSheet xssfSheet;
//        if (sheetName.equals("")) {
//            // 默认取第一个子表
//            xssfSheet = xssfWorkbook.getSheetAt(0);
//        } else {
//            xssfSheet = xssfWorkbook.getSheet(sheetName);
//        }
//
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//
//        //定义行
//        //默认第一行为标题行，index = 0
//        XSSFRow titleRow = xssfSheet.getRow(0);
//
//        //循环取每行的数据
//        for (int rowIndex = 1; rowIndex < xssfSheet.getPhysicalNumberOfRows(); rowIndex++) {
//            XSSFRow xssfRow = xssfSheet.getRow(rowIndex);
//            if (xssfRow == null) {
//                continue;
//            }
//
//            Map<String, String> map = new LinkedHashMap<String, String>();
//            //循环取每个单元格(cell)的数据
//            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
//                XSSFCell titleCell = titleRow.getCell(cellIndex);
//                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
//                map.put(getString(titleCell), getString(xssfCell));
//            }
//            list.add(map);
//        }
//        return list;
//    }
//
//    /**
//     * 把单元格的内容转为字符串
//     *
//     * @param xssfCell 单元格
//     * @return 字符串
//     */
//    public static String getString(XSSFCell xssfCell) {
//        if (xssfCell == null) {
//            return "";
//        }
//        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
//            return String.valueOf(xssfCell.getNumericCellValue());
//        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
//            return String.valueOf(xssfCell.getBooleanCellValue());
//        } else {
//            return xssfCell.getStringCellValue();
//        }
//    }
//
//    /**
//     * 把内容写入Excel
//     *
//     * @param list         传入要写的内容，此处以一个List内容为例，先把要写的内容放到一个list中
//     * @param outputStream 把输出流怼到要写入的Excel上，准备往里面写数据
//     */
//    public static void writeExcel(List<List> list, OutputStream outputStream) {
//        //创建工作簿
//        XSSFWorkbook xssfWorkbook = null;
//        xssfWorkbook = new XSSFWorkbook();
//
//        //创建工作表
//        XSSFSheet xssfSheet;
//        xssfSheet = xssfWorkbook.createSheet();
//
//        //创建行
//        XSSFRow xssfRow;
//
//        //创建列，即单元格Cell
//        XSSFCell xssfCell;
//
//        //把List里面的数据写到excel中
//        for (int i = 0; i < list.size(); i++) {
//            //从第一行开始写入
//            xssfRow = xssfSheet.createRow(i);
//            //创建每个单元格Cell，即列的数据
//            List sub_list = list.get(i);
//            for (int j = 0; j < sub_list.size(); j++) {
//                xssfCell = xssfRow.createCell(j); //创建单元格
//                xssfCell.setCellValue((String) sub_list.get(j)); //设置单元格内容
//            }
//        }
//
//        //用输出流写到excel
//        try {
//            xssfWorkbook.write(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static List<List> convertMapToList(Map map) {
//        List<List> list = new ArrayList<List>();
//        List<String> key_list = new LinkedList<String>();
//        List<String> value_list = new LinkedList<String>();
//
//        Set<Map.Entry<String, String>> set = map.entrySet();
//        Iterator<Map.Entry<String, String>> iter1 = set.iterator();
//        while (iter1.hasNext()) {
//            key_list.add(iter1.next().getKey());
//        }
//        list.add(key_list);
//
//        Collection<String> value = map.values();
//        Iterator<String> iter2 = value.iterator();
//        while (iter2.hasNext()) {
//            value_list.add(iter2.next());
//        }
//        list.add(value_list);
//        return list;
//    }
//
//
//    private void test1() {
//
//        System.out.println("==============>>>");
//        int i;
//        Sheet sheet;
//        Workbook book;
//        Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
//        try {
//            //hello.xls为要读取的excel文件名
//            book = Workbook.getWorkbook(new File("/Users/harrishuang/Downloads/working/mx_zip.xls"));
//
//            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
//            sheet = book.getSheet(0);
//            //获取左上角的单元格
//            cell1 = sheet.getCell(0, 0);
//            System.out.println("标题：" + cell1.getContents());
//
//            i = 1;
//            while (true) {
//                //获取每一行的单元格
//                cell1 = sheet.getCell(0, i);//（列，行）
//                cell2 = sheet.getCell(1, i);
//                cell3 = sheet.getCell(2, i);
//                cell4 = sheet.getCell(3, i);
//                cell5 = sheet.getCell(4, i);
//
//                if ("".equals(cell1.getContents()) == true) {
//                    //如果读取的数据为空
//                    break;
//                }
//                System.out.println(cell1.getContents() + "\t" + cell2.getContents() + "\t" + cell3.getContents() + "\t" + cell4.getContents()
//                        + "\t" + cell5.getContents() + "\t");
//                i++;
//            }
//            book.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
