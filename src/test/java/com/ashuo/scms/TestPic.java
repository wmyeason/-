package com.ashuo.scms;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class TestPic {


    /**
     * @use 利用Java代码给图片添加文字(透明图调低点, 也可以当做水印)
     */


    /**
     * 编辑图片,往指定位置添加文字
     *
     * @param srcImgPath    :源图片路径
     * @param targetImgPath :保存图片路径
     * @param list          :文字集合
     */
    public static void writeImage(String srcImgPath, String targetImgPath, List<ImageDTO> list) {
        FileOutputStream outImgStream = null;
        try {
            //读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高

            //添加文字:
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            for (ImageDTO imgDTO : list) {
                g.setColor(imgDTO.getColor());                                  //根据图片的背景设置水印颜色
                g.setFont(imgDTO.getFont());                                    //设置字体
                g.drawString(imgDTO.getText(), imgDTO.getX(), imgDTO.getY());   //画出水印
            }
            g.dispose();

            // 输出图片
            outImgStream = new FileOutputStream(targetImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
        } catch (Exception e) {
            log.error("==== 系统异常::{} ====", e);
        } finally {
            try {
                if (null != outImgStream) {
                    outImgStream.flush();
                    outImgStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建ImageDTO, 每一个对象,代表在该图片中要插入的一段文字内容:
     *
     * @param text  : 文本内容;
     * @param color : 字体颜色(前三位)和透明度(第4位,值越小,越透明);
     * @param font  : 字体的样式和字体大小;
     * @param x     : 当前字体在该图片位置的横坐标;
     * @param y     : 当前字体在该图片位置的纵坐标;
     * @return
     */
    private static ImageDTO createImageDTO(String text, Color color, Font font, int x, int y) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setText(text);
        imageDTO.setColor(color);
        imageDTO.setFont(font);
        imageDTO.setX(x);
        imageDTO.setY(y);
        return imageDTO;
    }


    /**
     * main方法:
     *
     * @param args
     */
    public static void main(String[] args) {

        //=========================================自行发挥================================
        //todo 自己真实的地址:(如果项目中使用的阿里云,则自行改造'writeImage'方法,接受流对象就好了);
        //todo  图片保存本地  并且使用UUID给文件命名  并存入数据库中
        String srcImgPath = "D:\\A学习资料\\Java\\毕业设计\\scms_pic\\reward.png";    //源图片地址
        String tarImgPath = "D:\\A学习资料\\Java\\毕业设计\\scms_pic\\"+"test"+".png";   //目标图片的地址
        //        String tarImgPath = "C:\\Users\\chaowie\\Desktop\\reward1.png";   //目标图片的地址
        //==============================================================================

        //获取数据集合；
        ArrayList<ImageDTO> list = new ArrayList<>();
        //找时间的  X  Y  的位置
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        list.add(createImageDTO(dateString, new Color(255, 0, 2, 255), new Font("微软雅黑", Font.HANGING_BASELINE, 24),
                130, 750));

        //找 名次 的位置
        list.add(createImageDTO("冠", new Color(4, 0, 2, 255), new Font("微软雅黑", Font.BOLD, 32),
                270, 690));

        //找到名字  的位置
        list.add(createImageDTO("管理员", new Color(255, 6, 0, 255), new Font("微软雅黑", Font.BOLD, 52),
                265, 525));

        //找到赛事信息  的位置
        list.add(createImageDTO("第一节运动会", new Color(255, 40, 40, 255), new Font("微软雅黑", Font.PLAIN, 24),
                230, 610));

        //找到比赛项目信息  的位置
        list.add(createImageDTO("足球比赛", new Color(255, 47, 47, 255), new Font("微软雅黑", Font.PLAIN, 24),
                300, 640));
//
        //操作图片:
        TestPic.writeImage(srcImgPath, tarImgPath, list);

        //这句代码,自己项目中可以不用加,在这里防止main方法报错的;
        System.exit(0);
    }
}


/**
 * 存放文本内容的类
 */
@Setter
@Getter
class ImageDTO {
    //文字内容
    private String text;
    //字体颜色和透明度
    private Color color;
    //字体和大小
    private Font font;
    //所在图片的x坐标
    private int x;
    //所在图片的y坐标
    private int y;

}


