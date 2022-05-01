package io.zczhendev.scheduleview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import top.zczhen.widget.schedule.Course
import top.zczhen.widget.schedule.adapter.ArrayScheduleAdapter
import top.zczhen.widget.schedule.adapter.IScheduleViewHolder

class ScheduleAdapter(array: List<ScheduleItem>) :
    ArrayScheduleAdapter<ScheduleItem, ScheduleAdapter.ViewHolder>(array) {

    companion object {
        private const val SINGLE_LINE_LEN = 4
    }

    private lateinit var context: Context
    var onItemClickListener: OnItemClickListener? = null

    override fun getCourse(position: Int): Course {
        val item = getItem(position)
        return object : Course {
            override val day: Int
                get() = (item.dayOfWeek ?: 1) - 1
            override val start: Int
                get() = (item.startWhat ?: 1) - 1
            override val end: Int
                get() = (item.endWhat ?: 1) - 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!this::context.isInitialized) {
            context = parent.context
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view, getCourse(viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        // 设置点击时间
        setListener(holder, position)

        val itemView = holder.itemView
        // 设置ItemView背景
        val bgDrawable = GradientDrawable()
        bgDrawable.setColor(item.bgColor)
        val radio = 5f
        bgDrawable.cornerRadius = DimenUtils.dp2px(radio, context)
        itemView.background = bgDrawable

        // 设置TextView
        val tv = holder.tv
        val nameText = formatName(item.name)
        tv.text = nameText
    }

    private fun setListener(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(it, getItem(position))
        }
    }

    /**
     * 格式化课程名称，实现换行、去除备注
     * 如：web前端开发(HTML+CSS+JavaScript) 显示为 web前端\n开发
     */
    private fun formatName(text: String?): String {
        var newText = StringBuilder(text ?: "")
        // 去掉最后一个括号内的备注文本
        val leftBracketIndex = newText.lastIndexOf("(")
        if (leftBracketIndex > 0)
            newText = StringBuilder(newText.substring(0, leftBracketIndex))

        // 插入换行符
        val len = newText.length
        // 插入换行符之后会影响字符下标，因此采用倒叙遍历规避
        for (i in (SINGLE_LINE_LEN until len step SINGLE_LINE_LEN).reversed()) {
            newText.insert(i, "\n")
        }
        return newText.toString()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(itemView: View, course: Course) : IScheduleViewHolder(itemView, course) {
        val tv: TextView = itemView.findViewById(R.id.tv)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, item: ScheduleItem)
    }


}