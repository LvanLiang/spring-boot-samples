package com.liang;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.Test;

import java.security.MessageDigest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Liangxp
 * @date 2020/8/27 14:55
 */
public class ExcelTest {

    @Test
    public void md5() throws Exception {
        System.out.println(MD5.create().digestHex("123456", "UTF-16"));
        System.out.println(getMD5("fzyjzx66", "UTF-16LE"));
        // CE-0B-FD-15-05-9B-68-D6-76-88-88-4D-7A-3D-3E-8C
        // CE-0B-FD-15-05-9B-68-D6-76-88-88-4D-7A-3D-3E-8C
    }

    public static String getMD5(String str, String encoding) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(encoding));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val < 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toUpperCase();
    }

/*
    public static string Encrypt(string cleanString)
    {
        Byte[] clearBytes = new UnicodeEncoding().GetBytes(cleanString);
        Byte[] hashedBytes = ((HashAlgorithm)CryptoConfig.CreateFromName("MD5")).ComputeHash(clearBytes);

        return BitConverter.ToString(hashedBytes);
    }
*/

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
    public void searchHref() {
        ExcelReader reader = ExcelUtil.getReader("F:\\domain.xls");
        List<List<Object>> readAll = reader.read();
        for (int i = 1; i < readAll.size(); i++) {
            List rowList = (List) readAll.get(i);
            String url = rowList.get(4).toString();
            try {
                HttpResponse res = HttpRequest.get(url).timeout(3000).execute();
                if (res.getStatus() == 200) {
                    String content = res.body();
                    //附件
                    String a = "<a\\b[^>]+\\bhref=\"?http://www.trjyzx.cn([^\"]*)\"[^>]*>([\\s\\S]*?)</a>";
                    Matcher hrefMatcher = Pattern.compile(a).matcher(content);
                    while (hrefMatcher.find()) {
                        String href = hrefMatcher.group();
                        if (!href.contains(">www.trjyzx.cn")
                                && !href.contains(">http://www.trjyzx.cn")
                                && !href.contains(">http://trjyzx.cn")
                                && !href.contains(">http:/www.jyzx.trs")
                                && !href.contains(">www.jyzx.trs")
                                && !href.contains(">www.jyzx.trs")
                                && !href.contains(">//www.trjyzx.cn")
                                && !href.contains(">http://jyzx.trs.gov.cn")
                                && !href.contains(">jyzx.trs")
                                && !href.contains("CA办理流程")) {
                            System.out.println("文章id：" + rowList.get(0) + "===地址：" + url + "===href:" + href);
                        }
                    }
                } else {

                }
            } catch (Exception e) {

            }
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
