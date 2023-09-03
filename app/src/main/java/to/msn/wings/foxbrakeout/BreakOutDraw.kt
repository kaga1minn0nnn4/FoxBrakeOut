package to.msn.wings.foxbrakeout

import android.graphics.Point
import android.graphics.PorterDuff
import android.view.MotionEvent
import android.view.SurfaceHolder

class BreakOutDraw(private val windowSize: Point, private val surfaceHolder: SurfaceHolder): Runnable {

    private var mb = MoveBall.initMoving(windowSize)
    private var racket = Racket.initRacket(windowSize)

    private var touchPointX: Float = 400f

    private val blockArray = BlockArray(6, 8, windowSize)

    private fun draw() {
        mb.step(racket, blockArray)
        racket.move(touchPointX)

        var canvas = surfaceHolder.lockCanvas()

        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        mb.draw(canvas)
        racket.draw(canvas)
        blockArray.draw(canvas)

        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun onTouch(event: MotionEvent): Boolean {
        touchPointX = event.x
        return true
    }

    override fun run() {
        draw()
    }
}