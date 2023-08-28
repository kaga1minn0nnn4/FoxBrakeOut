package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import kotlin.random.Random

class MoveBall(private val vX: Float, private val vY: Float, private val ball: Ball, private val size: Point) {

    private val COLLISION_MARGIN: Float = 10f

    companion object {
        fun initMoving(size: Point): MoveBall {
            val randV = RandomVelocity.init()
            val initVx = randV.getVerocity()
            val initVy = randV.getVerocity()

            return MoveBall(initVx, initVy, Ball.from(300f, 300f), size)
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
        val bottomEnd = ball.getBottom()

        if (size.y < bottomEnd) return false
        if (0 > topEnd) return false

        return true
    }

    private fun isInMargin(a: Float, b: Float): Boolean {
        val result: Boolean = Math.abs(a - b) < COLLISION_MARGIN
        return result
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

    private fun boundGround(): MoveBall {
        val newVx = vX
        val newVy = -vY
        return MoveBall(newVx, newVy, ball.move(newVx, newVy), size)
    }

    private fun boundWall(): MoveBall {
        val newVx = -vX
        val newVy = vY
        return MoveBall(newVx, newVy, ball.move(newVx, newVy), size)
    }

    private fun isInRacketVerticalLine(racket: Racket): Boolean {
        val racketTop = racket.getTop()
        val racketBottom = racket.getBottom()
        val ballY = ball.getY()

        if (ballY in racketTop..racketBottom) return true

        return false
    }

    private fun isInRacketHorizontalLine(racket: Racket): Boolean {
        val racketLeft = racket.getLeft()
        val racketRight = racket.getRight()
        val ballX = ball.getX()

        if (ballX in racketLeft..racketRight) return true

        return false
    }

    private fun isHittingRacketTop(racket: Racket): Boolean {
        val racketTop = racket.getTop()
        val ballBottom = ball.getBottom()

        if (!isInRacketHorizontalLine(racket)) return false
        if (!isInMargin(racketTop, ballBottom)) return false

        return true
    }

    private fun isHittingRacketLeft(racket: Racket): Boolean {
        val racketLeft = racket.getLeft()
        val ballRight = ball.getRight()

        if (!isInRacketVerticalLine(racket)) return false
        if (!isInMargin(racketLeft, ballRight)) return false

        return true
    }

    private fun isHittingRacketRight(racket: Racket): Boolean {
        val racketRight = racket.getRight()
        val ballLeft = ball.getLeft()

        if (!isInRacketVerticalLine(racket)) return false
        if (!isInMargin(racketRight, ballLeft)) return false

        return true
    }

    fun bound(racket: Racket): MoveBall {
        if (isHittingRacketTop(racket)) return boundGround()
        if (isHittingRacketLeft(racket)) return boundWall()
        if (isHittingRacketRight(racket)) return boundWall()
        return this
    }

}