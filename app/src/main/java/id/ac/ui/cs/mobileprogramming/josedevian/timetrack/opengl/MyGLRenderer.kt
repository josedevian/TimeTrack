package id.ac.ui.cs.mobileprogramming.josedevian.timetrack.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLRenderer : GLSurfaceView.Renderer {

    var blueValue = 10.0
    private val DURATION_OF_FLASH = 1000.0 // 1 second

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES20.glClearColor(blueValue.toFloat(), 158.0f, 217.0f, 1.0f)
    }

    override fun onDrawFrame(unused: GL10) {
        // Redraw background color
        GLES20.glClearColor(blueValue.toFloat(), 158.0f, 217.0f, 1.0f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        blueValue = Math.sin(System.currentTimeMillis() * 2 * Math.PI / DURATION_OF_FLASH) * 0.4 + 1.0
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
//        GLES20.glViewport(0, 0, width, height)
    }

}
