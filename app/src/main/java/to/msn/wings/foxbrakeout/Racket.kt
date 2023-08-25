package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class Racket(private val centerX: Float, private val size: Point) {
    private val p: Paint = Paint().apply {
        color = Color.BLACK
    }

    private val centerY = size.y - 200

    private val halfWidth = 100f
    private val halfHeight = 30f

    private val topEnd = centerY - halfHeight
    private val bottomEnd = centerY + halfHeight

    fun draw(canvas: Canvas) {
        val leftEnd = centerX - halfWidth
        val rightEnd = centerX + halfWidth

        canvas.drawRect(leftEnd, topEnd, rightEnd, bottomEnd, p)
    }

    fun move(newCenterX: Float): Racket {
        return Racket(newCenterX, size)
    }

    companion object {
        fun initRacket(size: Point): Racket {
            return Racket(200f, size)
        }
    }
}