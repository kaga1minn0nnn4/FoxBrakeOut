package to.msn.wings.foxbrakeout

import android.graphics.Canvas
import android.graphics.Point
import android.util.Log

class BlockArray(private val row: Int, private val col: Int, private val size: Point) {

    private val blocks = MutableList(row * col) { i ->
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
        blocks.forEach {
            it.draw(canvas)
        }
    }

    fun applyHittingMethod(ball: Ball, method: (Boolean, Boolean, Boolean, Boolean) -> Unit) {
        var hittingBlockNumber = -1

        for ((index, block) in blocks.withIndex()) {
            block.applyHittingMethod(ball) { hitTop, hitBottom, hitLeft, hitRight ->
                method(hitTop, hitBottom, hitLeft, hitRight)
                hittingBlockNumber = index
            }

            if (hittingBlockNumber != -1) break
        }

        if (hittingBlockNumber != -1) {
            blocks.removeAt(hittingBlockNumber)
        }
    }
}