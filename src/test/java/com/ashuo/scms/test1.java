package com.ashuo.scms;


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
}
