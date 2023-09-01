package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class Racket(private val centerX: Float, private val size: Point): RectangleObject(centerX, size.y - 100f) {

    override val p: Paint = Paint().apply {
        color = Color.BLACK
    }

    override val height: Float = 60f
    override val width: Float = 200f

    fun move(newCenterX: Float): Racket {
        val min = width / 2
        val max = size.x - width / 2

        if (newCenterX < min) return Racket(min, size)
        if (newCenterX > max) return Racket(max, size)

        return Racket(newCenterX, size)
    }

    companion object {
        fun initRacket(size: Point): Racket {
            return Racket(200f, size)
        }
    }
}