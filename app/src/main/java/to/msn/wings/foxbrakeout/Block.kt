package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Block(private val x: Float, private val y: Float) {
    private val BLOCK_SIDE_LENGTH: Float = 100f
    private val BLOCK_SIDE_LENGTH_HALF = BLOCK_SIDE_LENGTH / 2

    private val p: Paint = Paint().apply {
        color = Color.RED
    }

    private val top = y - BLOCK_SIDE_LENGTH_HALF
    private val bottom = y + BLOCK_SIDE_LENGTH_HALF
    private val left = x - BLOCK_SIDE_LENGTH_HALF
    private val right = x + BLOCK_SIDE_LENGTH_HALF

    companion object {
        fun initFromCoordinate(x_: Float, y_: Float): Block {
            return Block(x_, y_)
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(left, top, right, bottom, p)
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