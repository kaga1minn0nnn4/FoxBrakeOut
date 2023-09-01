package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class Racket(private var x_: Float, private var size: Point): RectangleObject() {

    override var x = x_
    override var y = size.y - 100f

    override val p: Paint = Paint().apply {
        color = Color.BLACK
    }

    override val height: Float = 60f
    override val width: Float = 200f

    fun move(newCenterX: Float) {
        val min = width / 2
        val max = size.x - width / 2

        if (newCenterX < min) x = min
        if (newCenterX > max) x = max

        x = newCenterX
    }

    companion object {
        fun initRacket(size: Point): Racket {
            return Racket(200f, size)
        }
    }
}