package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Point
import android.util.Log

class BlockArray(private val row: Int, private val col: Int, private val size: Point) {

    private val blocks = Array(row * col) { i ->
        val x = calcBlockPositionX( i % col)
        val y = calcBlockPositionY( (i / col).toInt())
        Block.initFromCoordinate(x, y)
    }

    private fun calcBlockPositionX(j: Int): Float {
        val x: Float = ((j + 1) * size.x / (col + 1)).toFloat()
        return x
    }

    private fun calcBlockPositionY(i: Int): Float {
        val y: Float = ((i + 1) * (size.y / 2) / (row + 1)).toFloat()
        return y
    }

    fun draw(canvas: Canvas) {
        for (i in 0..(row * col - 1)) {
            blocks[i].draw(canvas)
        }
    }
}