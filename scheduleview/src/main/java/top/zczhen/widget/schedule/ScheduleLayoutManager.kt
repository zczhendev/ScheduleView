package top.zczhen.widget.schedule

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import top.zczhen.widget.schedule.util.ItemMeasurer

internal class ScheduleLayoutManager(private val itemMeasurer: ItemMeasurer) :
    RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }



    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        recycler?.let {
            // 回收视图
            detachAndScrapAttachedViews(it)
            // 如果没有Item则停止操作
            if (itemCount == 0) {
                return
            }

            for (i in 0 until itemCount) {
                val view = it.getViewForPosition(i)
                val course = view.getTag(R.id.tag_course) as Course
                addView(view)
                measureChildWithMargins(view, 0, 0)
                val widthSpace = itemMeasurer.calculateCursorItemWidth(course.day)
                val heightSpace = itemMeasurer.calculateCursorItemHeight(course.start, course.end)
                val x = itemMeasurer.calculateCursorItemX(course.day)
                val y = itemMeasurer.calculateCourseItemY(course.start)

                layoutDecoratedWithMargins(view, x, y, x + widthSpace, y + heightSpace)

            }

        }


    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return 0
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return 0
    }

}