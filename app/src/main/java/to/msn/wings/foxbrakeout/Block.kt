package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Block(private val x_: Float, private val y_: Float): RectangleObject() {
    override var x = x_
    override var y = y_

    private val BLOCK_SIDE_LENGTH: Float = 100f

    override val height: Float = BLOCK_SIDE_LENGTH
    override val width: Float = BLOCK_SIDE_LENGTH

    override val p: Paint = Paint().apply {
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()

        color = Color.argb(255, r, g, b)
    }

    companion object {
        fun initFromCoordinate(x_: Float, y_: Float): Block {
            return Block(x_, y_)
        }
    }

}