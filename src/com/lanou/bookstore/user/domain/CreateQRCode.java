package com.lanou.bookstore.user.domain;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;


/**
 * Created by dllo on 17/9/28.
 */
public class CreateQRCode {
    public static void main(String[] args) {

        //图片的宽高(px)
        int width = 300;
        int height = 300;
        //图片格式
        String format = "png";
        //图片内容(存储的信息)
        String content = "这是一个还不能实现的操作,请使用账户密码登录![jaso祝您生活愉快]";

        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//内容编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级
        hints.put(EncodeHintType.MARGIN, 2);//边距

        //存储路径
        Path file = new File("/Users/dllo/Desktop/ewmL.png").toPath();

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
