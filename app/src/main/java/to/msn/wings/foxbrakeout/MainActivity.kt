package to.msn.wings.foxbrakeout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.SurfaceView
import android.widget.TextView
import java.util.Timer

class MainActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        val breakOutView = BreakOutView(this, surfaceView)

        surfaceView.setOnTouchListener { v, event ->
            breakOutView.onTouch(event)
        }
    }
}