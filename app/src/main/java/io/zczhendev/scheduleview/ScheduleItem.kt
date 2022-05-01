package io.zczhendev.scheduleview

import android.graphics.Color

data class ScheduleItem(
    // 上课教室
    val classroom: String,

    // 课程名称
    val name: String,

    // 开始节次
    val startWhat: Int,

    // 结束节次
    val endWhat: Int,

    // 授课教师
    val teacher: String,

    val dayOfWeek: Int,
    val bgColor: Int
)
