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
        val leftEnd = getLeft()
        val rightEnd = getRight()

        canvas.drawRect(leftEnd, topEnd, rightEnd, bottomEnd, p)
    }

    fun move(newCenterX: Float): Racket {
        return Racket(newCenterX, size)
    }

    fun getTop(): Float {
        return topEnd
    }

    fun getBottom(): Float {
        return bottomEnd
    }

    fun getRight(): Float {
        val rightEnd = centerX + halfWidth
        return rightEnd
    }

    fun getLeft(): Float {
        val leftEnd = centerX - halfWidth
        return leftEnd
    }

    companion object {
        fun initRacket(size: Point): Racket {
            return Racket(200f, size)
        }
    }
}