package top.zczhen.widget.schedule.ktextend

import android.content.Context
import kotlin.math.round

fun Context.dp2px(dp: Int): Float {
    return round(resources.displayMetrics.density * dp)
}