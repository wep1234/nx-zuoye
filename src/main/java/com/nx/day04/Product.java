package com.nx.day04;

import java.math.BigDecimal;

public class Product {
     String name = "nx P6课程";
     BigDecimal price = new BigDecimal(4299);
     String desc = "本公司产品属于国际领先产品，质量有保证，10年保修";
     Byte[] by1 = new Byte[1024*1024];

     public void buy(){
         System.out.println("欢迎购买本公司产品");
     }

}
