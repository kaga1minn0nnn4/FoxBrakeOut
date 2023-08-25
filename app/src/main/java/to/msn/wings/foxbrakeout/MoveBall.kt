package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import kotlin.random.Random

class MoveBall(private val vX: Float, private val vY: Float, private val ball: Ball, private val size: Point) {

    companion object {
        fun initMoving(size: Point): MoveBall {
            val randV = RandomVelocity.init()
            val initVx = randV.getVerocity()
            val initVy = randV.getVerocity()

            return MoveBall(initVx, initVy, Ball.from(300f, 300f), size)
        }
    }

    private fun isInViewWidth(): Boolean {
        val pX = ball.getX()
        val ballRadius = ball.getRadius()
        val rightEnd = pX + ballRadius
        val leftEnd = pX - ballRadius

        if (size.x < rightEnd) return false
        if (0 > leftEnd) return false

        return true
    }

    private fun isInViewHeight(): Boolean {
        val pY = ball.getY()
        val ballRadius = ball.getRadius()
        val topEnd = pY - ballRadius
        val bottomEnd = pY + ballRadius

        if (size.y < bottomEnd) return false
        if (0 > topEnd) return false

        return true
    }

    fun step(): MoveBall {
        var newVx = vX
        var newVy = vY

        if (!isInViewWidth()) newVx = -vX
        if (!isInViewHeight()) newVy = -vY

        return MoveBall(newVx, newVy, ball.move(newVx, newVy), size)
    }

    fun draw(canvas: Canvas) {
        ball.drawBall(canvas)
    }
}