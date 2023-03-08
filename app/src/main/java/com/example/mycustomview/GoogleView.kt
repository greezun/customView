package com.example.mycustomview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class GoogleView(
    context: Context,
    attrs: AttributeSet? = null,
    defaultAttrs: Int = 0
) : View(context, attrs, defaultAttrs) {

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
        canvas.apply {
            val centerX = (width / 2).toFloat()
            val centerY = (height / 2).toFloat()
            val oval = createOval(centerX, centerY)
            val textBoundRect = Rect()
            drawColor(Color.WHITE)

            arcs.forEach {
                painter.color = it.paintColor
                drawArc(oval, it.startAnge, it.sweepAngle, false, painter)
            }
            painter.color = arcs[3].paintColor
            drawLine(centerX, centerY, centerX + LOGO_WIDTH/2 + PAINTER_STOKE_WIDTH / 3, centerY, painter)

            textPainter.getTextBounds(TEXT,0, TEXT.length,textBoundRect)
            val textWidth = textPainter.measureText(TEXT)
            val textHeight = textBoundRect.height()

            canvas.drawText(TEXT, centerX+ LOGO_WIDTH/2+ 50f, centerY+textHeight/2, textPainter )

        }
    }

    private fun createOval(centerX: Float, centerY: Float): RectF {
        return RectF(
            centerX - LOGO_WIDTH/2,
            centerY - LOGO_HEIGHT/2,
            centerX + LOGO_WIDTH/2,
            centerY + LOGO_HEIGHT/2
        )

    }

    private companion object {
        private const val TEXT = "Google"
        private const val RECT_SIZE = 100f
        private const val LOGO_WIDTH = 150f
        private const val LOGO_HEIGHT = 100f
        private const val PAINTER_STOKE_WIDTH = (LOGO_WIDTH+ LOGO_HEIGHT)/8


    }
}