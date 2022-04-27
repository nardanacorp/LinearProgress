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
    var radiusTopLeft: Int = -1
    var radiusTopRight: Int = -1
    var radiusBottomLeft: Int = -1
    var radiusBottomRight: Int = -1
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
            this.radiusTopLeft = ta.getInteger(R.styleable.ProgressBars_radiusTopLeft,0)
            this.radiusTopRight = ta.getInteger(R.styleable.ProgressBars_radiusTopRight,0)
            this.radiusBottomLeft = ta.getInteger(R.styleable.ProgressBars_radiusBottomLeft,0)
            this.radiusBottomRight = ta.getInteger(R.styleable.ProgressBars_radiusBottomRight,0)
            this.titlesize = ta.getDimension(R.styleable.ProgressBars_titlesize,18f)
            this.backgroundcolor = ta.getColor(R.styleable.ProgressBars_Progressbackground,
                ContextCompat.getColor(context,R.color.teal_200))
            this.backgroundto = ta.getColor(R.styleable.ProgressBars_ProgressTo,
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
            corners = floatArrayOf(this.radiusTopLeft.toFloat(),this.radiusTopLeft.toFloat(),this.radiusTopRight.toFloat(),this.radiusTopRight.toFloat(),this.radiusBottomRight.toFloat(),this.radiusBottomRight.toFloat(),this.radiusBottomLeft.toFloat(),this.radiusBottomLeft.toFloat())
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

    fun get(): Int
    {
        return this.colortitle
    }

    fun getradiusAll(): Int
    {
        return this.radiusall
    }

    fun gettitlesize(): Float
    {
        return this.titlesize
    }

    fun gettoprogress(): Int
    {
        return this.toprogress
    }

    fun getbackgroundto(): Int
    {
        return this.backgroundto
    }

    fun getradiusTopLeft(): Int
    {
        return this.radiusTopLeft
    }

    fun gettypeface(): Typeface?
    {
        return this.typeface!!
    }

    fun getradiusTopRight(): Int
    {
        return this.radiusTopRight
    }

    fun getradiusBottomRight(): Int
    {
        return this.radiusBottomRight
    }

    fun getradiusBottomLeft(): Int
    {
        return this.radiusBottomLeft
    }

    fun gettitleprogress(): String
    {
        return this.titleprogress.toString()
    }

    fun getbackgroundcolor(): Int
    {
        return this.backgroundcolor
    }

    fun getlayoutwidth(): Int
    {
        return this.layoutwidth
    }

    fun getlayoutheight(): Int
    {
        return this.layoutheight
    }

    fun setradiusAll(value: Int)
    {
        this.radiusall = value
        refresh()
    }

    fun settitleprogress(value: String)
    {
        this.titleprogress = value
        refresh()
    }

    fun setcolortitle(value: Int)
    {
        this.colortitle = value
        refresh()
    }

    fun settypeface(value: Typeface)
    {
        this.typeface = value
        refresh()
    }

    fun setradiusTopLeft(value: Int)
    {
        this.radiusTopLeft = value
        refresh()
    }

    fun setradiusTopRight(value: Int)
    {
        this.radiusTopRight = value
        refresh()
    }

    fun setradiusBottomRight(value: Int)
    {
        this.radiusBottomRight = value
        refresh()
    }

    fun setradiusBottomLeft(value: Int)
    {
        this.radiusBottomLeft = value
        refresh()
    }

    fun settoprogress(value: Int)
    {
        this.toprogress = value
        refresh()
    }

    fun settitlesize(value: Float)
    {
        this.titlesize = value
        refresh()
    }

    fun setbackgroundcolor(value: Int)
    {
        this.backgroundcolor = value
        refresh()
    }

    fun setlayoutwidth(value: Int)
    {
        this.layoutwidth = value
        refresh()
    }

    fun setlayoutheight(value: Int)
    {
        this.layoutheight = value
        refresh()
    }

    fun setbackgrounddto(value: Int)
    {
        this.backgroundto = value
        refresh()
    }

    fun refresh()
    {
        invalidate()
        requestLayout()
    }
}