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