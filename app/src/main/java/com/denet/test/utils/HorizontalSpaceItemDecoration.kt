package com.denet.test.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class SpaceItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    protected open val spaceHorizontalSize by lazy {
        dpToPx(8)
    }
    protected open val spaceVerticalSize by lazy {
        dpToPx(8)
    }

    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        setLeftSpace(outRect, position, itemCount)
        setRightSpace(outRect, position, itemCount)
        setTopSpace(outRect, position, itemCount)
        setBottomSpace(outRect, position, itemCount)
    }

    protected open fun setLeftSpace(outRect: Rect, position: Int, itemCount: Int){
        outRect.left = spaceHorizontalSize
    }
    protected open fun setRightSpace(outRect: Rect, position: Int, itemCount: Int){
        outRect.right = spaceHorizontalSize
    }
    protected open fun setTopSpace(outRect: Rect, position: Int, itemCount: Int){
        outRect.top = spaceVerticalSize
    }
    protected open fun setBottomSpace(outRect: Rect, position: Int, itemCount: Int){
        outRect.bottom = spaceVerticalSize
    }
}