package com.zxy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class Main {



    public static AtomicLong count = new AtomicLong(10);

    public static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        // 1.基本类型和基本类型
//        int a = 10;
//        int b = 10;
//        System.out.println( a == b); // true 对
//
//        // 2.基本类型和包装类型
//        Integer a2 = 10;
//        System.out.println(a == a2); // false 错
//
//
//        // 3.包装类型和包装类型
//        Integer a3 = 10;
//        System.out.println(a2 == a3); // true 对
//
//        // 4. Integer 和 new Integer
//        Integer a4 = new Integer(10);
//        System.out.println(a3 == a4); // false 对
//
//        // 5. new Integer 和 new Integer
//        Integer a5 = new Integer(10);
//        System.out.println(a4 == a5); // false 对
//
//        // 2.基本类型和new 包装类型
//        System.out.println(a == a5); // true 错
//
//        a5.intValue();


        // 总结：
        // 1. 只要有 int 进行比较，一定 true  ：  如  int 和 Integer 或 int 和 new Integer 或 int 和 int
        // 2. 只要是有 new Integer (排除有int的)，一定 false，如 Integer 和 new Integer 或 new Integer 和 new Integer
        // 3. Integer 和 Integer

        // int -> Integer  自动装箱 赋值，  Integer.value(10)
        // Integer -> int  自动拆箱 .intValue


        Integer a = -129;
        Integer b = -129;
        System.out.println(a == b);

        // [-128, 127]   -2^8  ~  2^8-1


        Integer a2 = Integer.valueOf(-129);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        Integer[] cache = new Integer[256];
        int num = -128;
        for (int i = 0; i < 256 ; i++) {
            cache[i] = num;
            num++;
        }

        // -128 127 直接拿到对象的物理地址  ==
        // 如果不在范围内，直接 new


        // 八大基本数据类型
        /**
         * 带宽 100Mb
         * 下载 12.5MB
         *
         * byte 字节               1
         * char 字符               2
         * short 短整型            2
         * int 整型               4
         * float 单精度浮点类型    4
         * double 双精度浮点类型   8
         * long 长整型             8
         * boolean 布尔类型         1
         *
         *
         *
         *
         *
         */


        String str = "abc";
        char c = 'c';

        // 32 位： 4个字节
        // 块

      // Integer.value(10)  优于 new Integer(10)


        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = format.parse("2019-10-10 12:12:12");
        Date date2 = FORMAT.parse("2019-10-10 12:12:12");




    }


}
