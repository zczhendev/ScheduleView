package top.zczhen.widget.schedule.util

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import top.zczhen.widget.schedule.ktextend.dp2px

internal class ItemMeasurer(
    private val context: Context,
    private val xAxisLayout: ViewGroup,
    private val yAxisLayout: ViewGroup,
    private val rvCourse: RecyclerView
) {

    /**
     * 获取课程容器原点在屏幕中的位置
     */
    private val cursorLocationOnScreen: IntArray = IntArray(2)
        get() {
            rvCourse.getLocationOnScreen(field)
            return field
        }

    private val cursorLayoutX: Int
        get() = cursorLocationOnScreen[0]

    /**
     * 获取课程容器的Y位置
     */
    private val cursorLayoutY: Int
        get() = cursorLocationOnScreen[1]

    /**
     * 计算出课程item的顶点X
     */
    fun calculateCursorItemX(day: Int): Int {
        val dayTitleView = xAxisLayout.getChildAt(day)
        // 获取对应日期标题视图在屏幕中顶点的位置
        val dayTitleViewLocation = IntArray(2)
        dayTitleView.getLocationOnScreen(dayTitleViewLocation)
        val dayTitleX = dayTitleViewLocation[0]
        // 将课程视图容器的X减去标题试图容器的X即可得到对应标题X在课程容器中的位置
        return dayTitleX - cursorLayoutX + context.dp2px(1).toInt()
    }

    /**
     * 计算出课程item的顶点Y
     */
    fun calculateCourseItemY(start: Int): Int {
        val startCursorTitleView = yAxisLayout.getChildAt(start)
        val startCursorTitleLocation = IntArray(2)
        startCursorTitleView.getLocationOnScreen(startCursorTitleLocation)
        val startCursorY = startCursorTitleLocation[1]
        return startCursorY - cursorLayoutY + context.dp2px(1).toInt()
    }

    /**
     * 计算出课程item的宽度
     */
    fun calculateCursorItemWidth(day: Int): Int {
        val dayTitleView = xAxisLayout.getChildAt(day)
        return dayTitleView.measuredWidth - context.dp2px(2).toInt()
    }

    /**
     * 计算出课程item的高度
     */
    fun calculateCursorItemHeight(start: Int, end: Int): Int {
        val startCursorTitleView = yAxisLayout.getChildAt(start)
        val endCursorTitleView = yAxisLayout.getChildAt(end)
        val top = startCursorTitleView.y.toInt()
        val bottom = endCursorTitleView.y.toInt() + endCursorTitleView.measuredHeight
        return bottom - top - 2
    }

}