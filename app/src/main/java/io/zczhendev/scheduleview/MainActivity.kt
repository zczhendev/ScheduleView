package io.zczhendev.scheduleview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.zczhen.widget.schedule.ScheduleView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schedule = findViewById<ScheduleView>(R.id.schedule)

        val schedules = listOf<ScheduleItem>(
            ScheduleItem(
                classroom = "L123",
                name = "软件工程",
                startWhat = 1,
                endWhat = 3,
                teacher = "张三",
                dayOfWeek = 1,
                Color.parseColor("#FFA6A6")
            ),
            ScheduleItem(
                classroom = "L567",
                name = "大学英语",
                startWhat = 1,
                endWhat = 5,
                teacher = "里斯",
                dayOfWeek = 2,
                Color.parseColor("#FF32A6")
            ),
            ScheduleItem(
                classroom = "L567",
                name = "Java程序设计",
                startWhat = 6,
                endWhat = 7,
                teacher = "王五",
                dayOfWeek = 3,
                Color.parseColor("#0FA6A6")
            ),
        )
        val adapter = ScheduleAdapter(schedules)

        schedule.adapter = adapter

    }
}