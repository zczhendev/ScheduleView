package top.zczhen.widget.schedule.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import top.zczhen.widget.schedule.Course
import top.zczhen.widget.schedule.R

abstract class IScheduleViewHolder(itemView: View, course: Course) :
    RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setTag(R.id.tag_course, course)
    }

}