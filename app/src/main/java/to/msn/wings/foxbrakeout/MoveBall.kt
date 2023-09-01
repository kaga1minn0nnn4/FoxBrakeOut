package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import kotlin.random.Random

class MoveBall(private var vX: Float, private var vY: Float, private val size: Point) {
    private val INITIAL_BALL_POS_X = 300f
    private val INITIAL_BALL_POS_Y = 300f
    private var ball = Ball.from(INITIAL_BALL_POS_X, INITIAL_BALL_POS_Y)

    companion object {
        fun initMoving(size: Point): MoveBall {
            val randV = RandomVelocity.init()
            val initVx = randV.getVerocity()
            val initVy = randV.getVerocity()

            return MoveBall(initVx, initVy, size)
        }
    }

    private fun isInViewWidth(): Boolean {
        val rightEnd = ball.getRight()
        val leftEnd = ball.getLeft()

        if (size.x < rightEnd) return false
        if (0 > leftEnd) return false

        return true
    }

    private fun isInViewHeight(): Boolean {
        val topEnd = ball.getTop()
        if (0 > topEnd) return false
        return true
    }

    private fun isGameOver(): Boolean {
        val bottomEnd = ball.getBottom()
        if (size.y > bottomEnd) return false
        return true
    }

    fun step(racket: Racket) {
        val colDetect = CollisionDetection()

        if (isGameOver()) {
            ball = Ball.from(INITIAL_BALL_POS_X, INITIAL_BALL_POS_Y)
            return
        }

        if (!isInViewWidth()) vX = -vX
        if (!isInViewHeight()) vY = -vY
        if (colDetect.isCollision(ball, racket)) vY = -vY

        ball.move(vX, vY)
    }

    fun draw(canvas: Canvas) {
        ball.drawBall(canvas)
    }

}