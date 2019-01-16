package com.kangyonggan.app.util;

import com.github.ofofs.jca.annotation.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 1/15/19
 */
public final class SealUtil {

    /**
     * 2~4个汉字
     */
    private static final String REGEX = "^[\\u4e00-\\u9fa5]{2,4}$";

    /**
     * 全部字体
     */
    private static final Map<String, Font> FONT_MAP = new HashMap<>();

    private SealUtil() {
    }

    /**
     * 构建印章
     *
     * @param name     姓名
     * @param fontName 字体
     * @param path     保存位置
     * @throws Exception
     */
    @Log
    public static void build(String name, String fontName, String path) throws Exception {
        if (!name.matches(REGEX)) {
            throw new IllegalArgumentException("姓名必须是2~4位汉字");
        }
        if (name.length() == 2) {
            name += "之印";
        }
        if (name.length() == 3) {
            name += "印";
        }

        // 默认偏移量和字体大小
        int y = 225;
        int x = 30;
        int fontSize = 230;

        // 根据具体字体调节
        if ("印品篆遇简".equals(fontName)) {
            y += 25;
        } else if ("毛泽东字体".equals(fontName)) {
            x += 15;
            fontSize = 210;
        } else if ("繁篆书".equals(fontName)) {
            y -= 10;
        } else if ("幼圆".equals(fontName)) {
            y -= 10;
        } else if ("华文行楷".equals(fontName)) {
            y -= 13;
        }

        BufferedImage image = ImageIO.read(SealUtil.class.getResourceAsStream("/images/border.png"));
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(new Color(230, 0, 18));
        Font font = getFont(fontName, fontSize);
        if (font == null) {
            throw new IllegalArgumentException("字体库不存在");
        }
        graphics2D.setFont(font);
        graphics2D.drawString(name.substring(0, 1), x + fontSize, y);
        graphics2D.drawString(name.substring(1, 2), x + fontSize, y + fontSize);
        graphics2D.drawString(name.substring(2, 3), x, y);
        graphics2D.drawString(name.substring(3, 4), x, y + fontSize);

        ImageIO.write(image, "PNG", new FileOutputStream(path));
    }

    private static Font getFont(String fontName, int fontSize) {
        Font font = FONT_MAP.get(fontName);
        if (font == null) {
            loadFont(fontName, fontSize);
            font = FONT_MAP.get(fontName);
        }

        return font;
    }

    private static void loadFont(String fontName, int fontSize) {
        try {
            InputStream in = SealUtil.class.getResourceAsStream("/font/" + fontName + ".ttf");
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, in);
            in.close();
            FONT_MAP.put(fontName, dynamicFont.deriveFont(fontSize * 1.0f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        build("康永敢", "行书", "/Users/kyg/Desktop/demo.png");
    }

}
