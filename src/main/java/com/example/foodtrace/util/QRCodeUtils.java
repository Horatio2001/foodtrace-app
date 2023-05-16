package com.example.foodtrace.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.*;

@Slf4j
public class QRCodeUtils {
    private static final String CHARSET = "UTF-8";
    private static final int CODE_WIDTH = 360;
    private static final int CODE_HEIGHT = 360;
    //前景色  黑色
    private static final int FRONT_COLOR = 0x000000;
    //背景色 白色
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    public static void createQRCode(String path, String codeContent, String hash) throws Exception {
        if (StringUtils.isBlank(codeContent)) {
            log.info("二维码文本内容为空，不可生成二维码，请重试");
            return;
        }

//        String path = "/www/wwwroot/101.43.206.180/QRCode";
        File folder = new File(path);

        codeContent = codeContent.trim();

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }
        File codeImgFile = new File(folder, hash + ".jpg");

        ImageIO.write(bufferedImage, "jpg", codeImgFile);
        log.info("二维码生成成功，文件名：{},存放路径：{}", hash, codeImgFile.getAbsolutePath());
    }


    /**
     * 解析二维码
     *
     * @param filePath 二维码文件存放路径
     * @return
     * @throws Exception
     */
    public static String parseQRCode(String filePath) throws Exception {
        if (StringUtils.isBlank(filePath)) {
            log.info("二维码存放路径不能为空");
            return null;
        }
        File codeFile = new File(filePath);
        BufferedImage image;
        image = ImageIO.read(codeFile);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

}