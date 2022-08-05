package com.winter;

import com.google.gson.Gson;
import com.winter.data.Area;
import com.winter.data.City;
import com.winter.data.Province;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelTest {

    public static void main(String[] args) {

        getText();


    }


    private static void getText() {
        ExcelTest excelTest = new ExcelTest();

        Workbook wb = excelTest.getExcel("/Users/harrishuang/Downloads/working/moxige/mx_zip.xlsx");

        if (wb == null) {
            System.out.println("文件读入出错");
        } else {
            try {
                excelTest.analyzeExcel(wb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Workbook getExcel(String filePath) {
        Workbook wb = null;
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            wb = null;
        } else {
            String fileType = filePath.substring(filePath.lastIndexOf("."));//获得后缀名
            try {
                InputStream is = new FileInputStream(filePath);
                if (".xls".equals(fileType)) {
                    wb = new HSSFWorkbook(is);
                } else if (".xlsx".equals(fileType)) {
                    wb = new XSSFWorkbook(is);
                } else {
                    System.out.println("格式不正确");
                    wb = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    public void analyzeExcel(Workbook wb) throws Exception {
        Sheet sheet = wb.getSheetAt(0);//读取sheet(从0计数)
        int rowNum = sheet.getLastRowNum();//读取行数(从0计数)

        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i <= rowNum; i++) {
            Row row = sheet.getRow(i);//获得行
            int colNum = row.getLastCellNum();//获得当前行的列数

            Map<String, String> map = new HashMap<>();
            Cell cell0 = row.getCell(0);
            Cell cell1 = row.getCell(2);
            Cell cell2 = row.getCell(1);
            Cell cell3 = row.getCell(3);
            Cell cell4 = row.getCell(4);


            if (cell0 != null) {
                map.put("id", cell0.toString().replace(".0", "") + "");
            }
            if (cell1 != null) {
                map.put("province", cell1.toString() + "");
            }
            if (cell2 != null) {
                map.put("city", cell2.toString() + "");
            }
            if (cell3 != null) {
                map.put("area", cell3.toString() + "");
            }
            if (cell4 != null) {
                map.put("pinCode", cell4.toString().replace(".0", "") + "");
            }
            dataList.add(map);
        }
        Gson gson = new Gson();
//        System.out.println("==============>>>>" + gson.toJson(dataList).length());
//        try {
//            writeOcrStrtoFile(gson.toJson(dataList), "/Users/harrishuang/Downloads/working/moxige", "shi.json");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        parseText(dataList);
    }


    private static void prinlnGson(Object objec) {
        Gson gson = new Gson();
        System.out.println("==============>>>>" + gson.toJson(objec));

        try {
            writeOcrStrtoFile(gson.toJson(objec), "/Users/harrishuang/Downloads/working/moxige", "shi.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void parseText(List<Map<String, String>> params) {

//        List<Province> dataList = new ArrayList<>();
//
//        String provinceTemp = "";
//
//        for (int i = 0; i < params.size(); i++) {
//            Map<String, String> map = params.get(i);
//            String provinceStr = map.get("province");
//            String cityStr = map.get("city");
//            String areaStr = map.get("area");
//            String pinCode = map.get("pinCode");
//            if (!provinceStr.equals(provinceTemp)) {
//                provinceTemp=provinceStr;
//                Province province = new Province();
//                province.setName(provinceTemp);
//                dataList.add(province);
//            }
//        }


        List<Province> dataList = new ArrayList<>();

        List<City> citieList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        String provinceTemp = "";
        String cityTemp = "";

        for (int i = 0; i < params.size(); i++) {
            Map<String, String> map = params.get(i);
            String provinceStr = map.get("province");
            String cityStr = map.get("city");
            String areaStr = map.get("area");
            String pinCode = map.get("pinCode");
            if (!provinceStr.equals(provinceTemp)) {
                provinceTemp = provinceStr;
                Province province = new Province();
                province.setName(provinceTemp);
                citieList = new ArrayList<>();
                province.setCities(citieList);
                dataList.add(province);
            }

            if (!cityStr.equals(cityTemp)) {
                areaList = new ArrayList<>();
                cityTemp = cityStr;
                City city = new City();
                city.setName(cityTemp);
                city.setAreas(areaList);
                citieList.add(city);
            }


            Area area = new Area();
            area.setName(areaStr);
            area.setPinCode(pinCode);
            areaList.add(area);

        }


        prinlnGson(dataList);


    }

    public static void writeOcrStrtoFile(String result, String outPath, String outFileName) throws Exception {
        File dir = new File(outPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File txt = new File(outPath + "/" + outFileName);
        if (!txt.exists()) {
            txt.createNewFile();
        }
        byte bytes[] = new byte[512];
        bytes = result.getBytes();
        int b = bytes.length; // 是字节的长度，不是字符串的长度
        FileOutputStream fos = new FileOutputStream(txt);
//		fos.write(bytes, 0, b);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

}


