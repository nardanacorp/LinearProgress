package ir.nardana.linearprogressbar

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.logging.Handler

class LinearProgressBar : View , ValueAnimator.AnimatorUpdateListener{
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
    var toprogressint: Int = -1
    var toprogresspercent: Float = -1f
    var boxshadowsize: Int = -1
    var boxshadowsizex: Int = 0
    var boxshadowsizey: Int = 0
    var boxshadowcolor: Int = -1
    var titleprogress: String? = ""
    var typeface: Typeface? = null
    var backgroundPaint = Paint()
    var toPaint = Paint()
    var textpaint = Paint()
    var Status_To_Percent: Boolean = false
    var animetoprogress = 0f
    var valueanime: ValueAnimator? = null

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

    private fun initial(context: Context?, attrs: AttributeSet?)
    {
        val ta = context!!.theme.obtainStyledAttributes(attrs,R.styleable.LinearProgressBar,0,0)
        try
        {
            this.radiusall = ta.getInteger(R.styleable.LinearProgressBar_RadiusAll,0)
            this.cornerradiusTopLeft = ta.getInteger(R.styleable.LinearProgressBar_RadiusTopLeft,0)
            this.cornerradiusTopRight = ta.getInteger(R.styleable.LinearProgressBar_RadiusTopRight,0)
            this.cornerradiusBottomLeft = ta.getInteger(R.styleable.LinearProgressBar_RadiusBottomLeft,0)
            this.cornerradiusBottomRight = ta.getInteger(R.styleable.LinearProgressBar_RadiusBottomRight,0)
            this.boxshadowsize = ta.getInteger(R.styleable.LinearProgressBar_BoxShadowSize,-1)
            this.boxshadowsizex = ta.getInteger(R.styleable.LinearProgressBar_BoxShadowSizeX,0)
            this.boxshadowsizey = ta.getInteger(R.styleable.LinearProgressBar_BoxShadowSizeY,0)
            this.titlesize = ta.getDimension(R.styleable.LinearProgressBar_TitleSize,(18 * resources.displayMetrics.density))
            this.backgroundcolor = ta.getColor(R.styleable.LinearProgressBar_ProgressBackground,
                ContextCompat.getColor(context,R.color.teal_200))
            this.boxshadowcolor = ta.getColor(R.styleable.LinearProgressBar_BoxShadowColor,
                ContextCompat.getColor(context,R.color.teal_200))
            this.backgroundto = ta.getColor(R.styleable.LinearProgressBar_ProgressToBackground,
                ContextCompat.getColor(context,R.color.teal_200))
            this.colortitle = ta.getColor(R.styleable.LinearProgressBar_ColorTitle,
                ContextCompat.getColor(context,R.color.white))
            this.toprogressint = ta.getInteger(R.styleable.LinearProgressBar_ToProgressInt,-1)
            this.toprogresspercent = ta.getFloat(R.styleable.LinearProgressBar_ToProgressPercent,-1f)
            this.titleprogress = if(ta.getString(R.styleable.LinearProgressBar_TitleProgress) == null) "" else ta.getString(R.styleable.LinearProgressBar_TitleProgress)
        } finally {
            ta.recycle()
        }
        this.Status_To_Percent = this.toprogresspercent > -1f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.layoutwidth = this.measuredWidth
        this.layoutheight = this.measuredHeight
        this.backgroundPaint.style = Paint.Style.FILL
        this.backgroundPaint.color = this.backgroundcolor
        if(this.boxshadowsize > -1)
        {
            this.backgroundPaint.setShadowLayer((this.boxshadowsize * resources.displayMetrics.density),this.boxshadowsizex.toFloat(),this.boxshadowsizey.toFloat(),this.boxshadowcolor)
        }
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
        val rectfto = RectF(0f,0f,animetoprogress,this.layoutheight.toFloat())
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
        val positiony = ((this.layoutheight / 2 + 10)).toFloat()
        var temptextshow = this.titleprogress.toString()
        canvas!!.drawText(temptextshow,positionx,positiony,textpaint)
    }

    private fun createanime(toprogress: Float)
    {
        var newtoprogress = 0f
        if(this.Status_To_Percent)
        {
            newtoprogress = (this.layoutwidth * toprogress) / 100
        }
        else
        {
            newtoprogress = if(toprogress > this.layoutwidth) this.layoutwidth.toFloat() else toprogress.toFloat()
        }
        valueanime = ValueAnimator.ofInt(animetoprogress.toInt(),newtoprogress.toInt())
        valueanime!!.addUpdateListener(this)
        valueanime!!.setInterpolator(LinearInterpolator())
        valueanime!!.setDuration(2000)
        valueanime!!.start()
    }

    override fun onAnimationUpdate(p0: ValueAnimator?) {
        val valueincrease = p0!!.animatedValue.toString().toFloat()
        animetoprogress = if(this.layoutwidth < valueincrease) this.layoutwidth.toFloat() else valueincrease
        this.refresh()
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

    fun getToProgressInt(): Int
    {
        return this.toprogressint
    }

    fun getToProgressPercent(): Float
    {
        return this.toprogresspercent
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

    fun getBoxShadowColor(): Int
    {
        return this.boxshadowcolor
    }

    fun getBoxShadowSize(): Int
    {
        return this.boxshadowsize
    }

    fun getBoxShadowSizeX(): Int
    {
        return this.boxshadowsizex
    }

    fun getBoxShadowSizeY(): Int
    {
        return this.boxshadowsizey
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

    fun setToProgressInt(value: Int)
    {
        this.toprogressint = value
        this.Status_To_Percent = false
        onevaluate(value.toFloat())
        refresh()
    }

    fun setToProgressPercent(value: Float)
    {
        this.toprogresspercent = value
        this.Status_To_Percent = true
        onevaluate(value)
        refresh()
    }

    fun onevaluate(value: Float)
    {
        if(this.layoutwidth == -1)
        {
            this.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    layoutwidth = width
                    createanime(value)
                }
            })
        }
        else
        {
            this.createanime(value)
        }
    }

    fun setBoxShadowColor(value: Int)
    {
        this.boxshadowcolor = value
        refresh()
    }

    fun setBoxShadowSize(value: Int)
    {
        this.boxshadowsize = value
        refresh()
    }

    fun setBoxShadowSizeX(value: Int)
    {
        this.boxshadowsizex = value
        refresh()
    }

    fun setBoxShadowSizeY(value: Int)
    {
        this.boxshadowsizey = value
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