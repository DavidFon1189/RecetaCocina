package com.enginecore.app.recetascocina

import android.graphics.Bitmap
import android.graphics.Point
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class Utils {

    companion object {
        fun statusBar(context: AppCompatActivity){
            val window = context.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = context.resources.getColor(R.color.purple_app)
        }
    }
}