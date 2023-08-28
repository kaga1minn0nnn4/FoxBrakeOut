package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder

class Ball(private val pointX: Float, private val pointY: Float) {
    private val ballRadius = 50f

    private val p: Paint = Paint().apply {
        color = Color.BLUE
    }

    companion object {
        fun from(pointX_: Float, pointY_: Float): Ball {
            if (pointX_ < 0f) throw IllegalArgumentException("A x-coordinate must be positive: $pointX_")
            if (pointY_ < 0f) throw IllegalArgumentException("A x-coordinate must be positive: $pointY_")

            return Ball(pointX_, pointY_)
        }
    }

    fun move(dX: Float, dY: Float): Ball {
        return from(pointX + dX, pointY + dY)
    }

    fun drawBall(canvas: Canvas) {
        canvas.drawCircle(pointX, pointY, ballRadius, p)
    }

    fun getRadius(): Float {
        return ballRadius
    }

    fun getX(): Float {
        return pointX
    }

    fun getY(): Float {
        return pointY
    }

    fun getTop(): Float {
        return pointY - ballRadius
    }

    fun getBottom(): Float {
        return pointY + ballRadius
    }

    fun getRight(): Float {
        return pointX + ballRadius
    }

    fun getLeft(): Float {
        return pointX - ballRadius
    }

}