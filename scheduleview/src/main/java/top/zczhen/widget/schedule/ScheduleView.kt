package top.zczhen.widget.schedule

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import top.zczhen.widget.schedule.adapter.IScheduleAdapter
import top.zczhen.widget.schedule.adapter.IScheduleViewHolder
import top.zczhen.widget.schedule.exception.WrongCursorException
import top.zczhen.widget.schedule.ktextend.dp2px
import top.zczhen.widget.schedule.util.ItemMeasurer

class ScheduleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        val DAY_OF_WEEKS = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")
    }

    private var xAxisLayout: LinearLayout
    private var yAxisLayout: LinearLayout

    private var rvCourse: RecyclerView

    var axisTextSize: Float = 12f
        set(value) {
            if (value != field) {
                field = value
                changeAxisTextSize(value)
            }
        }

    var courseCount: Int = 12
        set(value) {
            field = value
            initYAxis()
        }

    var adapter: IScheduleAdapter<out IScheduleViewHolder>? = null
        set(value) {
            value?.let {
                // 检查course是否合法
                for (i in 0 until value.itemCount) {
                    val course = value.getCourse(i)
                    checkCourse(course)
                }
                field = value
                rvCourse.adapter = value
            }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.schedule_view, this, true)
        xAxisLayout = findViewById(R.id.ll_x_axis)
        yAxisLayout = findViewById(R.id.ll_y_axis)
        rvCourse = findViewById(R.id.rv_course)

        initXAxis()
        initYAxis()
        initRvCourse()
    }

    private fun initRvCourse() {
        val itemMeasurer = ItemMeasurer(context, xAxisLayout, yAxisLayout, rvCourse)
        val layoutManager = ScheduleLayoutManager(itemMeasurer)
        rvCourse.layoutManager = layoutManager
    }

    private fun checkCourse(course: Course) {
        if (course.day >= 7 || course.day < 0) {
            throw WrongCursorException("cursor day is out of range, require [0,7)")
        }
        if (course.start < 0 || course.end < 0 || course.start >= courseCount || course.end >= courseCount) {
            throw WrongCursorException("cursor range is [0, cursorCount:[${courseCount}])")
        }
    }


    private fun changeAxisTextSize(textSize: Float) {
        setXAxisTextSize(textSize)
        setYAxisTextSize(textSize)
    }

    private fun setYAxisTextSize(textSize: Float) {
        for (i in 0 until yAxisLayout.childCount) {
            val child = yAxisLayout.getChildAt(i)
            if (child is TextView)
                child.textSize = textSize
        }
    }

    private fun setXAxisTextSize(textSize: Float) {
        for (i in 0 until xAxisLayout.childCount) {
            val child = yAxisLayout.getChildAt(i)
            if (child is TextView)
                child.textSize = textSize
        }
    }

    /**
     * 初始化横向轴
     * @author zczhen
     * @date 2021/11/22
     * @version 1
     */
    private fun initXAxis() {
        for (element in DAY_OF_WEEKS) {
            val child = TextView(context)
            val layoutParams = LinearLayout
                .LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)
            layoutParams.weight = 1f
            layoutParams.leftMargin = if (DAY_OF_WEEKS.first() != element) {
                dp2px(1).toInt()
            } else {
                0
            }
            child.layoutParams = layoutParams
            child.setBackgroundColor(Color.parseColor("#30000000"))
            child.gravity = Gravity.CENTER
            child.text = element
            child.setTextColor(Color.WHITE)
            child.textSize = axisTextSize
            xAxisLayout.addView(child)

        }
    }

    /**
     * 初始化纵向轴
     * @author zczhen
     * @date 2021/11/22
     * @version 1
     */
    private fun initYAxis() {
        yAxisLayout.removeAllViews()
        for (i in 1..courseCount) {
            val child = TextView(context)
            val layoutParams = LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
            layoutParams.weight = 1f
            layoutParams.topMargin = if (i != 1) dp2px(1).toInt() else 0
            child.layoutParams = layoutParams
            child.setBackgroundColor(Color.parseColor("#30000000"))
            child.gravity = Gravity.CENTER
            child.text = "$i"
            child.setTextColor(Color.WHITE)
            child.textSize = axisTextSize
            yAxisLayout.addView(child)
        }
    }

}