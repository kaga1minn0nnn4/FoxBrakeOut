package to.msn.wings.foxbrakeout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Point
import android.graphics.PorterDuff
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.logging.Handler

class BreakOutView(context: Context, surfaceView: SurfaceView) : SurfaceView(context), SurfaceHolder.Callback {
    private val surfaceHolder = surfaceView.holder
    private val service = Executors.newSingleThreadScheduledExecutor()

    private val windowSize = getWindowSize()

    private var mb = MoveBall.initMoving(windowSize)
    private var racket = Racket.initRacket(windowSize)

    private var touchPointX: Float = 400f

    private val blockArray = BlockArray(6, 8, getWindowSize())

    init {
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT)
        surfaceView.setZOrderOnTop(true)
        surfaceHolder.addCallback(this)
    }

    private fun getWindowSize(): Point {
        val size = Point().also {
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.apply {
                getSize(
                    it
                )
            }
        }

        return size
    }

    override fun surfaceChanged(
        holder: SurfaceHolder,
        format: Int, width: Int, height: Int
    ) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        service.shutdown()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        service.scheduleAtFixedRate(
            object : Runnable {
                override fun run() {
                    draw()
                }
            },
            1,
            1,
            TimeUnit.MILLISECONDS
        )
    }

    private fun draw() {
        mb.step()
        racket = racket.move(touchPointX)
        mb.bound(racket)

        var canvas = Canvas()
        canvas = surfaceHolder.lockCanvas()

        canvas.drawColor(0, PorterDuff.Mode.CLEAR)

        mb.draw(canvas)

        racket.draw(canvas)

        blockArray.draw(canvas)

        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun onTouch(event: MotionEvent): Boolean {
        touchPointX = event.x
        Log.d("x", "$touchPointX")
        return true
    }

}