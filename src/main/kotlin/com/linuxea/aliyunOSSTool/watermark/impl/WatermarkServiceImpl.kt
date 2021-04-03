package com.linuxea.aliyunOSSTool.watermark.impl

import cn.hutool.core.codec.Base64
import cn.hutool.core.util.ReflectUtil
import com.google.common.base.Joiner
import com.linuxea.aliyunOSSTool.watermark.ImageWatermarkParam
import com.linuxea.aliyunOSSTool.watermark.WatermarkService
import java.util.*

class WatermarkServiceImpl : WatermarkService {


    override fun watermark(imageWatermarkParam: ImageWatermarkParam): String {
        val buildImageWatermark: String = this.buildImageWatermark(imageWatermarkParam.imageWatermarkList)
        val buildTextWatermark: String = this.buildTextWatermark(imageWatermarkParam.textWatermarks)
        return imageWatermarkParam.image + "?x-oss-process=image/" + buildImageWatermark + buildTextWatermark
    }


    /**
     * 删除影响路径表示的符号
     */
    private fun encode(content: String): String {
        return Base64.encode(content).replace("+", "-").replace("/", "_")
    }

    /**
     * 处理图片水印
     */
    private fun buildImageWatermark(imageWatermarkList: List<ImageWatermarkParam.ImageWatermark>): String {
        val collect = imageWatermarkList
            .map { imageWatermark: ImageWatermarkParam.ImageWatermark ->
                val string = "/watermark,image_" + encode(imageWatermark.image)

                val attributes = ReflectUtil.getFieldsDirectly(ImageWatermarkParam.ImageWatermark::class.java, true)
                    .map {
                        if (!it.name.equals("image")) {
                            ReflectUtil.getFieldValue(imageWatermark, it)?.let { fileValue ->
                                return@map it.name + "_" + fileValue
                            } ?: ""
                        } else ""
                    }

                string + "," + Joiner.on(",").join(attributes)
            }
        return Joiner.on("/").join(collect)
    }

    /**
     * 处理文字水印
     */
    private fun buildTextWatermark(textWatermarks: List<ImageWatermarkParam.TextWatermark>): String {
        val collect = textWatermarks
            .map { textWatermark: ImageWatermarkParam.TextWatermark ->
                val string = "/watermark,text_" + encode(textWatermark.text)

                val attributes = ReflectUtil.getFieldsDirectly(ImageWatermarkParam.TextWatermark::class.java, true)
                    .map {
                        if (!it.name.equals("text")) {
                            ReflectUtil.getFieldValue(textWatermark, it)?.let { fileValue ->
                                return@map it.name + "_" + fileValue
                            } ?: ""
                        } else ""
                    }

                string + "," + Joiner.on(",").join(attributes)
            }
        return Joiner.on("/").join(collect)
    }


}