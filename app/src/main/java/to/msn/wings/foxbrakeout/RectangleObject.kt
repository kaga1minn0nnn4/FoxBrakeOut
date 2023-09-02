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

        val rectTop = getTop()
        val rectBottom = getBottom()
        val rectLeft = getLeft()
        val rectRight = getRight()

        val hitBoxRangeX = rectLeft - ballRadius .. rectRight + ballRadius
        val hitBoxRangeY = rectTop - ballRadius .. rectBottom + ballRadius

        var hitTop = false
        var hitBottom = false
        var hitLeft = false
        var hitRight = false

        if ((rectBottom in ballRangeY) && (ballX in hitBoxRangeX)) hitBottom = true
        if ((rectTop in ballRangeY) && (ballX in hitBoxRangeX)) hitTop = true
        if ((rectRight in ballRangeX) && (ballY in hitBoxRangeY)) hitRight = true
        if ((rectLeft in ballRangeX) && (ballY in hitBoxRangeY)) hitLeft = true

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

    private fun getTop(): Float {
        return y - (height / 2)
    }

    private fun getBottom(): Float {
        return y + (height / 2)
    }

    private fun getLeft(): Float {
        return x - (width / 2)
    }

    private fun getRight(): Float {
        return x + (width / 2)
    }
}