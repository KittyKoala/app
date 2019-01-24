package com.kangyonggan.app.util;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author kangyonggan
 * @date 6/23/17
 */
public final class Images {

    private Images() {
    }

    /**
     * 生成缩略图。
     *
     * @param source 原图（大）
     * @param desc   目标图片
     * @param width  宽
     * @param height 高
     * @throws Exception
     */
    public static void thumb(String source, String desc, int width, int height) throws Exception {
        BufferedImage image = ImageIO.read(new FileInputStream(source));

        // 判断原图的长宽比是不是比目标图的长宽比大，用于计算压缩后的宽和高
        int pressWidth;
        int pressHeight;
        if (image.getHeight() * 1.0 / image.getWidth() > height * 1.0 / width) {
            // 原图是高图
            // 让压缩后的宽度等于目标宽度
            pressWidth = width;
            // 等比例计算高度
            pressHeight = (int) (1.0 * image.getHeight() * width / image.getWidth());
        } else {
            // 原图是宽图
            // 让压缩后的高度等于目标高度
            pressHeight = height;
            // 等比例计算高度
            pressWidth = (int) (1.0 * image.getWidth() * height / image.getHeight());
        }

        // 先压缩
        BufferedImage temp = zoomImage(image, pressWidth, pressHeight);

        // 再截取
        int x = (temp.getWidth() - width) / 2;
        int y = (temp.getHeight() - height) / 2;
        temp = temp.getSubimage(x, y, width, height);

        // 最后写文件
        ImageIO.write(temp, "png", new FileOutputStream(desc));
    }

    /**
     * 缩放图片
     *
     * @param image
     * @param w
     * @param h
     * @throws Exception
     */
    private static BufferedImage zoomImage(BufferedImage image, int w, int h) throws Exception {
        double wr = w * 1.0 / image.getWidth();
        double hr = h * 1.0 / image.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        return ato.filter(image, null);
    }

}
