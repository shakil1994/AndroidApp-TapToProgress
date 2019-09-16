package com.example.shakil.testkotlintaptoprogress

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.Display
import android.view.View
import android.view.WindowManager
import androidx.annotation.NonNull

class Circle(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val paint = Paint()
    private val rectf = RectF()
    var angle: Float = 0.toFloat()
    private var endangle: Int = 0

    init {
        init(context)
    }

    private fun init(context: Context) {
        val dimensionPixelSize =
            context.resources.getDimensionPixelSize(R.dimen.curve_width).toFloat()
        val dimensionPixelSize2 =
            context.resources.getDimensionPixelSize(R.dimen.curve_radius).toFloat() - 4.0f * dimensionPixelSize
        val screenSize = getScreenSize(context)
        val f = dimensionPixelSize / 2.0f
        val f2 = (screenSize.x / 2).toFloat() - dimensionPixelSize - dimensionPixelSize2
        this.paint.isAntiAlias = true
        this.paint.style = Paint.Style.STROKE
        this.paint.strokeWidth = dimensionPixelSize
        this.endangle = ((screenSize.x / 2).toFloat() - dimensionPixelSize).toInt()
        this.rectf.set(f2, f, dimensionPixelSize2 * 2.0f + f2, dimensionPixelSize2 * 2.0f + f)
        this.angle = 54.5f
    }

    fun setCurveColor(i: Int, i2: Int) {
        this.paint.shader = LinearGradient(
            0.0f,
            0.0f,
            this.endangle.toFloat(),
            this.endangle.toFloat(),
            i,
            i2,
            Shader.TileMode.MIRROR
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(this.rectf, 175.0f, this.angle, false, this.paint)
    }

    companion object {

        fun getScreenSize(@NonNull context: Context): Point {
            val defaultDisplay =
                (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            val point = Point()
            defaultDisplay.getSize(point)
            return point
        }
    }
}
