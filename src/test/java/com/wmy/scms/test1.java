package com.wmy.scms;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class test1 {

    @Test
    public void t1(){
        //删除荣誉图片
        String tarImgPath = "D:\\A学习资料\\Java\\毕业设计\\scms_pic\\";
        File directory=new File(tarImgPath);
        File[] files = directory.listFiles();
        for(File f:files){
            if(!f.getName().equals("reward.png"))f.delete();
        }


    }

    @Test
    public void  t2(){
        int money = 20,children = 3;
        int res= 0;
        money -= children;
        while (money>7){
            money-=7;
            res++;
        }
        if(money==3){
            res--;
        }
        System.out.println(res);
    }
}
