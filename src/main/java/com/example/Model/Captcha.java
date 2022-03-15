package com.example.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {
    /**
     * 宽
     */
    private int width;
    /**
     * 高
     */
    private int height;
    /**
     * 随机生成数量
     */
    private int num;
    /**
     * 从哪些文字中随机生成
     */
    private String code;
    /**
     * 随机数
     */
    private static final Random random = new Random();

    /**
     * 单例模式
     */
    private static Captcha captcha;

    private Captcha() {
        code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        num = 4;
    }

    public static Captcha getIntance() {
        if (captcha == null) {
            captcha = new Captcha();
        }
        return captcha;
    }

    public void set(int width, int height, int num, String code) {
        this.width = width;
        this.height = height;
        this.setNum(num);
        this.setCode(code);
    }

    public void set(int width, int height) {
        this.width = width;
        this.height = height;
        this.setNum(num);
        this.setCode(code);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Captcha getCaptcha() {
        return captcha;
    }

    public static void setCaptcha(Captcha captcha) {
        Captcha.captcha = captcha;
    }

    public static Random getRandom() {
        return random;
    }

    /**
     * 用于验证码字符串,获取随机生成的验证码
     *
     * @return
     */
    public String generateCheckcode() {
        StringBuffer codeBuffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            // 在字符串中 获取随机下标
            int index = random.nextInt(code.length());
            // 获取对应位置字符
            char value = code.charAt(index);
            codeBuffer.append(value);
        }
        return codeBuffer.toString();
    }

    /**
     * 返回弄好的图片,需要传入对应的验证码
     */
    public BufferedImage generateCheckImg(String checkcode) {
        // 创建一个图片对象
        BufferedImage img = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取画笔,画背景
        Graphics2D graphic = img.createGraphics();
        // 设置白色
        graphic.setColor(Color.white);
        // x和y轴都从0开始,全部画成白色
        graphic.fillRect(0, 0, width, height);
        // 设置颜色为黑色
        graphic.setColor(Color.BLACK);
        // 画边框
        graphic.drawRect(0, 0, width, height);
        // 2 设置字体
        // 创建字体对象,设置字体,粗体+斜体,以及字体大小
        Font font = new Font("宋体", Font.BOLD + Font.ITALIC,
                (int) (height * 0.8));
        // 把字体设置到画笔中
        graphic.setFont(font);
        for (int i = 0; i < num; i++) {
            // 画笔随机颜色.字体就随机颜色
            graphic.setColor(new Color(random.nextInt(255),
                    random.nextInt(255), random.nextInt(255)));
            // 画文字,把验证码每个文字都挨个取出来并设置x和y轴位置
            graphic.drawString(String.valueOf(checkcode.charAt(i)), i
                    * (width / num) + 4, height - 10);
        }

        // 加背景噪点
        for (int i = 0; i < (width + height); i++) {
            // 画笔随机颜色.字体就随机颜色
            graphic.setColor(new Color(random.nextInt(255),
                    random.nextInt(255), random.nextInt(255)));
            // 画噪点, x,y,width,height
            graphic.drawOval(random.nextInt(width), random.nextInt(height), 1,
                    1);
        }

        // 加背景干扰线
        for (int i = 0; i < 4; i++) {
            // 画笔随机颜色.字体就随机颜色
            graphic.setColor(new Color(random.nextInt(255),
                    random.nextInt(255), random.nextInt(255)));
            /**
             * x1 第一个点的 x 坐标。
             * y1 第一个点的 y 坐标
             * x2 第二个点的 x 坐标。
             * y2 第二个点的 y 坐标。
             *
             * x1是0  说明 第一个点 只会在y轴上移动,也就是图片的左边框
             *
             * x2是width 说明 第二个点只会在 图片的有边框移动
             *
             * 然后y轴随机, 就能做到 水平线 或者x这种对角线
             */
            graphic.drawLine(0, random.nextInt(height), width,
                    random.nextInt(height));
        }
        return img;
    }

}
