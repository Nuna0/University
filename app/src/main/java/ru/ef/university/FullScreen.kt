package ru.ef.university

import android.app.Activity
import android.view.View


class FullScreen {
    private var context: Activity? = null
    private lateinit var views: Array<View>

    /**
     * @param context
     * @param views to hide/show
     */
    fun FullScreenHelper(context: Activity?, views: Array<View>) {
        this.context = context
        this.views = views
    }

    /**
     * call this method to enter full screen
     */
    fun enterFullScreen() {
        val decorView: View = context!!.window.decorView
        hideSystemUi(decorView)
        for (view: View in views) {
            view.setVisibility(View.GONE)
            view.invalidate()
        }
    }

    /**
     * call this method to exit full screen
     */
    fun exitFullScreen() {
        val decorView: View = context!!.window.decorView
        showSystemUi(decorView)
        for (view: View in views) {
            view.setVisibility(View.VISIBLE)
            view.invalidate()
        }
    }

    private fun hideSystemUi(mDecorView: View) {
        mDecorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )
    }

    private fun showSystemUi(mDecorView: View) {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }
}