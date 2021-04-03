package com.linuxea.aliyunOSSTool.watermark

/**
 * 图片水印
 *
 * @author linuxea
 */
class ImageWatermarkParam {
    /**
     * 主图
     */
    lateinit var image: String

    /**
     * 图片水印列表
     */
    var imageWatermarkList: List<ImageWatermark> = listOf()

    /**
     * 文本水印列表
     */
    var textWatermarks: List<TextWatermark> = listOf()

    /**
     * 通用参数
     */
    open class BaseWatermark {
        /**
         * 指定图片水印或水印文字的透明度
         */
        var t: String? = null

        /**
         * 指定水印在图片中的位置
         */
        var g: String? = null

        /**
         * 指定水印的水平边距， 即距离图片边缘的水平距离。这个参数只有当水印位置是左上、左中、左下、右上、右中、右下才有意义
         */
        var x: String? = null

        /**
         * 指定水印的垂直边距，即距离图片边缘的垂直距离， 这个参数只有当水印位置是左上、中上、右上、左下、中下、右下才有意义
         */
        var y: String? = null

        /**
         * 指定水印的中线垂直偏移。当水印位置在左中、中部、右中时，可以指定水印位置根据中线往上或者往下偏移
         */
        var voffset: String? = null
    }

    class ImageWatermark : BaseWatermark() {
        /**
         * 水印图 用于指定作为图片水印Object的完整名称，Object名称需进行Base64编码
         */
        lateinit var image: String
    }

    class TextWatermark : BaseWatermark() {
        /**
         * 水印文本
         */
        lateinit var text: String

        /**
         * 文本颜色
         */
        var color: String? = null

        /**
         * 文本大小
         */
        var size: String? = null

        /**
         * 字体
         */
        var type: String? = null
    }
}