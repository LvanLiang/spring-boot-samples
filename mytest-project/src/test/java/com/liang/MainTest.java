package com.liang;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import com.liang.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Liangxp
 * @date 2020/6/15 20:07
 */
@Slf4j
public class MainTest {

    @Test
    public void testSmf() {
        SymmetricCrypto smf = SmUtil.sm4();
        System.out.println(smf.getSecretKey());
    }

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate now = LocalDate.now();
        String month = dtf.format(now);


        System.out.println(now.toString() + "[ ]年月" + month);
        List<Long> functionIdList = Arrays.asList(1353408239894560L, 1353408489455648L, 1353411706486816L);
        String idList = CollUtil.join(functionIdList, ",");
        System.out.println("======" + idList);
        String ids = "1352482133377088";
        String[] split = ids.split(",");
        String join = String.join("','", split);

        Float d = 12.454f;
        System.out.println(d);
        Date date = new Date();
        System.out.println("time:==" + date.getTime());


        System.out.println("Float最大值：" + Float.MAX_VALUE);

        Integer  readCount = 29;

        long readTotal = 200L;

        double pecent = readCount.longValue() * 1.0 / readTotal;

        String liang = SecureUtil.md5("liang");
        String liang2 = SecureUtil.md5("liang");
        String liang3 = SecureUtil.md5("liang");
        System.out.println(liang.equals(SecureUtil.md5("liang")));
        System.out.println("百分比：" + pecent);
        RSA rsa = SecureUtil.rsa();
        String liangs = rsa.encryptHex("liang", KeyType.PrivateKey);
        System.out.println("liang：" + liangs);


        DateTime dateTime = DateUtil.lastMonth();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        System.out.println("上个月：{}" + format.format(dateTime));

        List<String> urlList = new ArrayList<>();
        List<String> collect = urlList.stream().filter(u -> u.startsWith("/user/*")).collect(Collectors.toList());


        List<Integer> integers = Arrays.asList(10, 20, 30);
        Integer sum = integers.stream().collect(Collectors.summingInt(Integer::intValue));
        int sum1 = integers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum:::" + sum);
        System.out.println("sum1:::" + sum1);

    }

    @Test
    public void testEqual() {
        Integer a = 10;
        Integer b = 10;
        System.out.println(a == b);
    }


    @Test
    public void testDateUtil() {
//        DateTime lastMonth = DateUtil.lastMonth();
        DateTime lastMonth = DateUtil.date();
        Date date = new Date();
        log.info("jdkDate:{}, sqlDate:{},date:{}", lastMonth.toJdkDate(), lastMonth.toSqlDate(), date);

        DateTime dateTime = DateUtil.offsetDay(new Date(), -30);
        String formatDateTime = DateUtil.formatDateTime(dateTime);
        log.info("回退30天：{}", formatDateTime);
        DateTime dateTime1 = DateUtil.offsetDay(dateTime, 1);
        log.info("回退29天：{}", dateTime1);
        log.info("回退29天：{}", DateUtil.format(dateTime1, DatePattern.NORM_DATE_PATTERN));
    }

    @Test
    public void testDouble() {
        long a = 250L, b = 38L, c = 132L, e = 27L, d = 447L;
        double d1 = a  * 1.0 / d * 100;
        double d2 = b  * 1.0 / d * 100;
        double d3 = c  * 1.0 / d * 100;
        double d4 = e  * 1.0 / d * 100;
        DecimalFormat df = new DecimalFormat("0.0");
        String format =  df.format(d1);
        String format2 = df.format(d2);
        String format3 = df.format(d3);
        String format4 = df.format(d4);

        Double sum = Double.parseDouble(format) +Double.parseDouble(format2) + Double.parseDouble(format3) + Double.parseDouble(format4) ;
        if (sum < 100) {
            double ddd = 100 - sum;
            d4 = Double.parseDouble(format4) + ddd;
            format4 =  df.format(d4);
        }

        if (sum > 100) {
            d4 = Double.parseDouble(format4) - (sum - 100);
            format4 =  df.format(d4);
        }


        log.info("结果：{}, {}", d1, format);
        log.info("结果：{}, {}", d2, format2);
        log.info("结果：{}, {}", d3, format3);
        log.info("结果：{}, {}", d4, format4);

        log.info("====================");

        int aa = 10, bb = 17;
        double ddd = aa * 1.0 / bb;
        log.info("均值：{}", ddd);

        double ddddd = ddd * (1 - 0.6);
        log.info("标杆阅读量：{}", ddddd);
        log.info("是否大于：{}", ddd > ddddd);

    }

    @Test
    public void testSorted() {
        List<Integer> list = Arrays.asList(8, 6, 9, 7);
        List<Integer> collect = list.stream().sorted().collect(Collectors.toList());
        log.info("排序结果：{}", collect);
    }

    @Test
    public void testABC() {
        String number = "BCDAE";
        char[] chars = number.toCharArray();
        Arrays.sort(chars);
        String s = String.valueOf(chars);
        log.info("排序号{},,,,{}", chars, s);
    }

    @Test
    public void test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String currentMonth = format.format(date);
        log.info("当月{}", currentMonth);
    }


    @Test
    public void testLinkedHashMap() {
        LinkedHashMap<String, Object> stringObjectLinkedHashMap = new LinkedHashMap<>();
        stringObjectLinkedHashMap.put("1z","张1三");
        stringObjectLinkedHashMap.put("l2","李四2");
        stringObjectLinkedHashMap.put("3w","王3五");
        stringObjectLinkedHashMap.put("4b","赵六4");
        stringObjectLinkedHashMap.put("5","赵六5");
        stringObjectLinkedHashMap.put("6a","赵六6");
        stringObjectLinkedHashMap.put("z6","赵六7");
        stringObjectLinkedHashMap.put("tq8","赵六8");

        stringObjectLinkedHashMap.put("l2","王贝贝不不2222");


        String jsonStr = JSONUtil.toJsonStr(stringObjectLinkedHashMap);
        log.info("jsonStr: {}", jsonStr);

    }

    @Test
    public void testRank() {
        int a = 10, b = 15;
        float c = (a - b) / b;
        double d =  -7 * 1.0 / 105 * 100;

        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(d);

        log.info("变化:{}", format);
    }
    
    @Test
    public void testIdUtil() {
        long id = IdUtil.getId();
        log.info("分布式主键:{}", id);
    }


}
