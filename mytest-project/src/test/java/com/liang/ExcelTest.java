package com.liang;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.Test;

import java.util.List;

/**
 * @author Liangxp
 * @date 2020/8/27 14:55
 */
public class ExcelTest {

    public static void handle(List rowList, String msg) {
        if (rowList.size() < 10) {
            rowList.add(9, "");
            rowList.add(10, msg);
        } else {
            rowList.add(10, msg);
        }
    }

    public static void handleSb(List rowList, String msg) {
        if (rowList.size() < 7) {
            rowList.add(6, "");
            rowList.add(7, msg);
        } else {
            rowList.add(7, msg);
        }
    }


    @Test
    public void test() {
        ExcelReader reader = ExcelUtil.getReader("F:\\exceltmp\\紫云人民政府\\紫云4.xlsx");
        List<List<Object>> readAll = reader.read();
        int i = 1;
        for (List<Object> objects : readAll) {
            System.out.println("开始处理：" + (i++));
            List rowList = (List) objects;
            String url = rowList.get(2).toString();
            try {
                HttpResponse res = HttpRequest.get(url).timeout(3000).execute();
                if (res.getStatus() == 200) {
                    handle(rowList, "是");
                } else {
                    handle(rowList, "否");
                }
            } catch (Exception e) {
                handle(rowList, "否");
            }

        }

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("F:\\exceltmp\\紫云人民政府\\check\\紫云4.xlsx");
        //一次性写出内容，强制输出标题
        writer.write(readAll, true);
        //关闭writer，释放内存
        writer.close();
    }

    @Test
    public void tes2() {
        ExcelReader reader = ExcelUtil.getReader("F:\\exceltmp\\丹寨错链\\丹寨人民政府.xlsx");
        List<List<Object>> readAll = reader.read();
        int i = 1;
        for (List<Object> objects : readAll) {
            System.out.println("开始处理：" + (i++));
            List rowList = (List) objects;
            String url = rowList.get(2).toString();
            try {
                HttpResponse res = HttpRequest.get(url).timeout(2000).execute();
                if (res.getStatus() == 200) {
                    handle(rowList, "是");
                } else {
                    handle(rowList, "否");
                }
            } catch (Exception e) {
                handle(rowList, "否");
            }

        }

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("F:\\exceltmp\\丹寨错链\\check\\丹寨人民政府.xlsx");
        //一次性写出内容，强制输出标题
        writer.write(readAll, true);
        //关闭writer，释放内存
        writer.close();
    }

    @Test
    public void tes3() {
        ExcelReader reader = ExcelUtil.getReader("F:\\exceltmp\\5226250001_镇远县人民政府_20200818_全站不可用链接（疑似）_60369.xlsx");
        List<List<Object>> readAll = reader.read();
        int i = 1;
        for (List<Object> objects : readAll) {
            System.out.println("开始处理：" + (i++));
            List rowList = (List) objects;
            String url = rowList.get(2).toString();
            try {
                HttpResponse res = HttpRequest.get(url).timeout(2000).execute();
                if (res.getStatus() == 200) {
                    handle(rowList, "是");
                } else {
                    handle(rowList, "否");
                }
            } catch (Exception e) {
                handle(rowList, "否");
            }

        }

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("F:\\excelCheck\\5226250001_镇远县人民政府_20200818_全站不可用链接（疑似）_60369.xlsx");
        //一次性写出内容，强制输出标题
        writer.write(readAll, true);
        //关闭writer，释放内存
        writer.close();
    }


}
