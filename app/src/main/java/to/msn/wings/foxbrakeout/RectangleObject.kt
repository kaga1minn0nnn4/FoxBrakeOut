package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point

abstract class RectangleObject {
    abstract var x: Float
    abstract var y: Float
    abstract val width: Float
    abstract val height: Float

    abstract val p: Paint

    fun applyHittingMethod(ball: Ball, method: (Boolean, Boolean, Boolean, Boolean) -> Unit) {
        val ballX = ball.getX()
        val ballY = ball.getY()
        val ballTop = ball.getTop()
        val ballBottom = ball.getBottom()
        val ballLeft = ball.getLeft()
        val ballRight = ball.getRight()

        val ballRangeX = ballLeft..ballRight
        val ballRangeY = ballTop..ballBottom

        val ballRadius = ball.getRadius()

        val hitBoxRangeX = getLeft() - ballRadius .. getRight() + ballRadius
        val hitBoxRangeY = getTop() - ballRadius .. getBottom() + ballRadius

        var hitTop = false
        var hitBottom = false
        var hitLeft = false
        var hitRight = false

        if ((getBottom() in ballRangeY) && (ballX in hitBoxRangeX)) hitBottom = true
        if ((getTop() in ballRangeY) && (ballX in hitBoxRangeX)) hitTop = true
        if ((getRight() in ballRangeX) && (ballY in hitBoxRangeY)) hitRight = true
        if ((getLeft() in ballRangeX) && (ballY in hitBoxRangeY)) hitLeft = true

        if (hitTop || hitBottom || hitLeft || hitRight) {
            method(hitTop, hitBottom, hitLeft, hitRight)
        }
    }

    fun draw(canvas: Canvas) {
        val top = getTop()
        val bottom = getBottom()
        val left = getLeft()
        val right = getRight()

        canvas.drawRect(left, top, right, bottom, p)
    }

    fun getCenterX(): Float {
        return x
    }

    fun getCenterY(): Float {
        return y
    }
    fun getTop(): Float {
        return y - (height / 2)
    }

    fun getBottom(): Float {
        return y + (height / 2)
    }

    fun getLeft(): Float {
        return x - (width / 2)
    }

    fun getRight(): Float {
        return x + (width / 2)
    }
}