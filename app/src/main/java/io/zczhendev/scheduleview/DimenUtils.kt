package io.zczhendev.scheduleview

import android.content.Context
import kotlin.math.round

object DimenUtils {
    fun dp2px(dp: Float, context: Context): Float {
        return round(context.resources.displayMetrics.density * dp)
    }
}