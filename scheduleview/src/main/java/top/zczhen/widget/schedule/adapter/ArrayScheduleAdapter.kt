package top.zczhen.widget.schedule.adapter

import android.annotation.SuppressLint

abstract class ArrayScheduleAdapter<T, VH : IScheduleViewHolder>(array: List<T>) :
    IScheduleAdapter<VH>() {

    private val array: MutableList<T> = mutableListOf()

    init {
        this.array.addAll(array)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(array: List<T>) {
        this.array.clear()
        if (array.isNotEmpty()) {
            this.array.addAll(array)
            notifyDataSetChanged()
        }
    }

    fun addCourse(t: T) {
        array.add(t)
        notifyItemInserted(array.size - 1)
    }

    fun addCourses(data: List<T>) {
        if (data.isNotEmpty()) {
            val addStartPosition = this.array.size
            val addCount = data.size
            this.array.addAll(data)
            notifyItemRangeInserted(addStartPosition, addCount)
        }
    }

    fun removeCourse(t: T) {
        val removePosition = array.indexOf(t)
        array.remove(t)
        notifyItemRemoved(removePosition)
    }

    fun getItem(position: Int): T {
        return array[position]
    }

    override fun getItemCount(): Int {
        return array.size
    }

}