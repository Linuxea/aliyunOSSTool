package com.linuxea.aliyunOSSTool.watermark

interface WatermarkService {


    /**
     * 水印图片
     */
    fun watermark(imageWatermarkParam: ImageWatermarkParam): String

}