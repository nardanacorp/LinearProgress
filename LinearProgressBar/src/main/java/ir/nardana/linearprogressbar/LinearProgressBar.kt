package ir.nardana.linearprogressbar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class LinearProgressBar : View {
    var titlesize: Float = 18f
    var backgroundcolor: Int = -1
    var colortitle: Int = -1
    var backgroundto: Int = -1
    var radiusall: Int = -1
    var cornerradiusTopLeft: Int = -1
    var cornerradiusTopRight: Int = -1
    var cornerradiusBottomLeft: Int = -1
    var cornerradiusBottomRight: Int = -1
    var layoutwidth: Int = -1
    var layoutheight: Int = -1
    var toprogress: Int = -1
    var titleprogress: String? = ""
    var typeface: Typeface? = null
    var backgroundPaint = Paint()
    var toPaint = Paint()
    var textpaint = Paint()

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    {
        initial(context,attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initial(context,attrs)
    }

    fun initial(context: Context?, attrs: AttributeSet?)
    {
        val ta = context!!.theme.obtainStyledAttributes(attrs,R.styleable.ProgressBars,0,0)
        try
        {
            this.radiusall = ta.getInteger(R.styleable.ProgressBars_radiusall,0)
            this.cornerradiusTopLeft = ta.getInteger(R.styleable.ProgressBars_radiusTopLeft,0)
            this.cornerradiusTopRight = ta.getInteger(R.styleable.ProgressBars_radiusTopRight,0)
            this.cornerradiusBottomLeft = ta.getInteger(R.styleable.ProgressBars_radiusBottomLeft,0)
            this.cornerradiusBottomRight = ta.getInteger(R.styleable.ProgressBars_radiusBottomRight,0)
            this.titlesize = ta.getDimension(R.styleable.ProgressBars_titlesize,18f)
            this.backgroundcolor = ta.getColor(R.styleable.ProgressBars_Progressbackground,
                ContextCompat.getColor(context,R.color.teal_200))
            this.backgroundto = ta.getColor(R.styleable.ProgressBars_ProgressToBackground,
                ContextCompat.getColor(context,R.color.teal_200))
            this.colortitle = ta.getColor(R.styleable.ProgressBars_colortitle,
                ContextCompat.getColor(context,R.color.teal_200))
            this.toprogress = ta.getInteger(R.styleable.ProgressBars_ToProgress,0)
            this.titleprogress = ta.getString(R.styleable.ProgressBars_titleprogress)
        } finally {
            ta.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.layoutwidth = this.measuredWidth
        this.layoutheight = this.measuredHeight
        this.backgroundPaint.style = Paint.Style.FILL
        this.backgroundPaint.color = this.backgroundcolor
        this.backgroundPaint.setShadowLayer(20f,10f,10f,this.backgroundcolor)
        val rectf = RectF(0f,0f,this.layoutwidth.toFloat(),this.layoutheight.toFloat())
        val corners: FloatArray
        if(this.radiusall > 0)
        {
            corners = floatArrayOf(this.radiusall.toFloat(),this.radiusall.toFloat(),this.radiusall.toFloat(),this.radiusall.toFloat(),this.radiusall.toFloat(),this.radiusall.toFloat(),this.radiusall.toFloat(),this.radiusall.toFloat())
        }
        else
        {
            corners = floatArrayOf(this.cornerradiusTopLeft.toFloat(),this.cornerradiusTopLeft.toFloat(),this.cornerradiusTopRight.toFloat(),this.cornerradiusTopRight.toFloat(),this.cornerradiusBottomRight.toFloat(),this.cornerradiusBottomRight.toFloat(),this.cornerradiusBottomLeft.toFloat(),this.cornerradiusBottomLeft.toFloat())
        }
        var pathbacground = Path()
        pathbacground.addRoundRect(rectf,corners, Path.Direction.CW)
        canvas!!.drawPath(pathbacground,this.backgroundPaint)
        this.toPaint.style = Paint.Style.FILL
        this.toPaint.color = this.backgroundto
        val rectfto = RectF(0f,0f,this.toprogress.toFloat(),this.layoutheight.toFloat())
        var topath = Path()
        topath.addRoundRect(rectfto,corners, Path.Direction.CW)
        canvas!!.drawPath(topath,this.toPaint)
        textpaint.textSize = this.titlesize
        textpaint.setColor(this.colortitle)
        if(typeface != null)
        {
            textpaint.setTypeface(typeface)
        }
        textpaint.textAlign = Paint.Align.CENTER
        val positionx = (canvas.width / 2).toFloat()
        canvas!!.drawText(this.titleprogress.toString(),positionx,((this.layoutheight / 2 + 10)).toFloat(),textpaint)
    }

    fun getColorTitle(): Int
    {
        return this.colortitle
    }

    fun getRadiusAll(): Int
    {
        return this.radiusall
    }

    fun getTitleSize(): Float
    {
        return this.titlesize
    }

    fun getToProgress(): Int
    {
        return this.toprogress
    }

    fun getBackgroundTo(): Int
    {
        return this.backgroundto
    }

    fun getRadiusTopLeft(): Int
    {
        return this.cornerradiusTopLeft
    }

    fun getRadiusTopRight(): Int
    {
        return this.cornerradiusTopRight
    }

    fun getRadiusBottomRight(): Int
    {
        return this.cornerradiusBottomRight
    }

    fun getRadiusBottomLeft(): Int
    {
        return this.cornerradiusBottomLeft
    }

    fun getTypeFace(): Typeface?
    {
        return this.typeface!!
    }

    fun getTitleProgress(): String
    {
        return this.titleprogress.toString()
    }

    fun getBackColor(): Int
    {
        return this.backgroundcolor
    }

    fun getLayoutWidth(): Int
    {
        return this.layoutwidth
    }

    fun getLayoutHeight(): Int
    {
        return this.layoutheight
    }

    fun setColorTitle(value: Int)
    {
        this.colortitle = value
        refresh()
    }

    fun setRadiusAll(value: Int)
    {
        this.radiusall = value
        refresh()
    }

    fun setTitleProgress(value: String)
    {
        this.titleprogress = value
        refresh()
    }

    fun setTypeFace(value: Typeface)
    {
        this.typeface = value
        refresh()
    }

    fun setRadiusTopLeft(value: Int)
    {
        this.cornerradiusTopLeft = value
        refresh()
    }

    fun setRadiusTopRight(value: Int)
    {
        this.cornerradiusTopRight = value
        refresh()
    }

    fun setRadiusBottomRight(value: Int)
    {
        this.cornerradiusBottomRight = value
        refresh()
    }

    fun setRadiusBottomLeft(value: Int)
    {
        this.cornerradiusBottomLeft = value
        refresh()
    }

    fun setToProgress(value: Int)
    {
        this.toprogress = value
        refresh()
    }

    fun setTitleSize(value: Float)
    {
        this.titlesize = value
        refresh()
    }

    fun setBackColor(value: Int)
    {
        this.backgroundcolor = value
        refresh()
    }

    fun setBackgroundTo(value: Int)
    {
        this.backgroundto = value
        refresh()
    }

    fun setLayoutWidth(value: Int)
    {
        this.layoutwidth = value
        refresh()
    }

    fun setLayoutHeight(value: Int)
    {
        this.layoutheight = value
        refresh()
    }

    fun refresh()
    {
        invalidate()
        requestLayout()
    }
}