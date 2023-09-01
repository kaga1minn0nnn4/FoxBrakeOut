package to.msn.wings.foxbrakeout

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Point
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class BreakOutView(context: Context, surfaceView: SurfaceView) : SurfaceView(context), SurfaceHolder.Callback {
    private val surfaceHolder = surfaceView.holder
    private val service = Executors.newSingleThreadScheduledExecutor()

    private val breakOutDraw = BreakOutDraw(getWindowSize(), surfaceHolder)

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
            breakOutDraw,
            1,
            1,
            TimeUnit.MILLISECONDS
        )
    }

    fun onTouch(event: MotionEvent): Boolean {
        breakOutDraw.onTouch(event)
        return true
    }

}