package com.denet.test.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.denet.test.R

open class SpaceItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    protected open val spaceHorizontalSize by lazy {
        context.resources.getDimensionPixelSize(R.dimen.space_item_decoration_default)
    }
    protected open val spaceVerticalSize by lazy {
        context.resources.getDimensionPixelSize(R.dimen.space_item_decoration_default)
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
        context.resources.getDimension(R.dimen.space_item_decoration_bottom)
        outRect.top = spaceVerticalSize
    }
    protected open fun setBottomSpace(outRect: Rect, position: Int, itemCount: Int){
        outRect.bottom = spaceVerticalSize
    }
}