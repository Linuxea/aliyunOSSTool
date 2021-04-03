package com.linuxea.aliyunOSSTool.watermark

import com.linuxea.aliyunOSSTool.watermark.impl.WatermarkServiceImpl
import java.net.URL

fun main() {


    // 姓名
    val nurseWatermark = ImageWatermarkParam.TextWatermark()
    nurseWatermark.text = "（游客）"
    nurseWatermark.x = "110"
    nurseWatermark.y = "200"
    nurseWatermark.color = "333333"
    nurseWatermark.size = "18"
    nurseWatermark.type = "d3F5LXplbmhlaQ"
    nurseWatermark.g = "sw"

    // 学习标语
    val learnWatermark = ImageWatermarkParam.TextWatermark()
    learnWatermark.text = "打卡学习"
    learnWatermark.x = "110"
    learnWatermark.y = "170"
    learnWatermark.color = "333333"
    learnWatermark.size = "15"
    learnWatermark.type = "ZHJvaWRzYW5zZmFsbGJhY2s"
    learnWatermark.g = "sw"

    // 头像的图片
    val avatarImageWatermark = ImageWatermarkParam.ImageWatermark()
    val avatarUrl = "http://xxx.oss-cn-shenzhen.aliyuncs.com/temp/default_avatar.jpg"
    avatarImageWatermark.image = URL(avatarUrl).path.substring(1) + "?x-oss-process=image/resize,P_15/circle,r_90"
    avatarImageWatermark.x = "48"
    avatarImageWatermark.y = "170"
    avatarImageWatermark.g = "sw"


    // 主图
    val imageWatermarkVO = ImageWatermarkParam()
    imageWatermarkVO.image = "https://xxx.oss-cn-shenzhen.aliyuncs.com/poster/posterbetter.png"
    imageWatermarkVO.imageWatermarkList = listOf(avatarImageWatermark)
    imageWatermarkVO.textWatermarks = listOf(nurseWatermark, learnWatermark)
    val posterUrl: String = WatermarkServiceImpl().watermark(imageWatermarkVO)
    println(posterUrl)

}