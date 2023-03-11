package com.example.mycustomview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomeButtonView(
    context: Context,
    attrs: AttributeSet? = null,
    defaultAttrs: Int = 0,
            defStyle: Int = 0
) : View(context, attrs, defaultAttrs, defStyle) {
    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributesSet,
        defStyleAttr,
        0
    )

    constructor(context: Context, attributesSet: AttributeSet?) : this(
        context, attributesSet, 0
    )

    constructor(context: Context) : this(context, null)

    private val backgroundPainter = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val painter = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = PAINTER_STOKE_WIDTH
    }

    private val textPainter = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        color = Color.BLACK
        textSize = 100f
    }


    private val arcs = GoogleArcs().getArcs()


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        Log.i("myTag", "onDraw")

        canvas.apply {
            val startX = getStartXCoordinate(width)
            val centerX = (width / 2).toFloat()
            val centerY = (height / 2).toFloat()
            val oval = createOval(startX, centerY)
            val textBoundRect = Rect()

            val backgroundRect = RectF()
            backgroundRect.set(0f, 0f, width.toFloat(), height.toFloat())
            drawRoundRect(backgroundRect, 10f, 10f, backgroundPainter)


            arcs.forEach {
                painter.color = it.paintColor
                drawArc(oval, it.startAnge, it.sweepAngle, false, painter)
            }
            painter.color = arcs[3].paintColor
            drawLine(
                startX + LOGO_WIDTH / 2,
                centerY,
                startX + LOGO_WIDTH + PAINTER_STOKE_WIDTH / 3,
                centerY,
                painter
            )

            textPainter.getTextBounds(TEXT, 0, TEXT.length, textBoundRect)
            val textWidth = textPainter.measureText(TEXT)
            val textHeight = textBoundRect.height()

            canvas.drawText(
                TEXT,
                startX + LOGO_WIDTH + SPACE,
                centerY + textHeight / 2,
                textPainter
            )

        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i("myTag", "onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(800, 200)
    }

    private fun getStartXCoordinate(width: Int): Float {
        val totalLength = textPainter.measureText(TEXT) + SPACE + LOGO_WIDTH
        return width / 2 - totalLength / 2

    }

    private fun createOval(centerX: Float, centerY: Float): RectF {
        return RectF(
            centerX,
            centerY - LOGO_HEIGHT / 2,
            centerX + LOGO_WIDTH,
            centerY + LOGO_HEIGHT / 2
        )
    }

    private companion object {
        private const val TEXT = "GOOGLE"
        private const val LOGO_WIDTH = 100f
        private const val LOGO_HEIGHT = 100f
        private const val PAINTER_STOKE_WIDTH = (LOGO_WIDTH + LOGO_HEIGHT) / 8
        private const val SPACE = 50f


    }
}