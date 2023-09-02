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

    private val top = y - (height / 2)
    private val bottom = y + (height / 2)
    private val left = x - (width / 2)
    private val right = x + (width / 2)

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

        if ((bottom in ballRangeY) && (ballX in hitBoxRangeX)) hitBottom = true
        if ((top in ballRangeY) && (ballX in hitBoxRangeX)) hitTop = true
        if ((right in ballRangeX) && (ballY in hitBoxRangeY)) hitRight = true
        if ((left in ballRangeX) && (ballY in hitBoxRangeY)) hitLeft = true

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
        return top
    }

    fun getBottom(): Float {
        return bottom
    }

    fun getLeft(): Float {
        return left
    }

    fun getRight(): Float {
        return right
    }
}