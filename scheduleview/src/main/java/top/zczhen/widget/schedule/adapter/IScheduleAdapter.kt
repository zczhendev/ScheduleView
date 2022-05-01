package top.zczhen.widget.schedule.adapter

import androidx.recyclerview.widget.RecyclerView
import top.zczhen.widget.schedule.Course

abstract class IScheduleAdapter<VH : IScheduleViewHolder> : RecyclerView.Adapter<VH>() {

    abstract fun getCourse(position: Int): Course

    override fun getItemViewType(position: Int): Int {
        return position
    }

}