package com.denet.test.screens.list_nodes.adapter

import android.content.Context
import android.graphics.Rect
import com.denet.test.utils.SpaceItemDecoration

class ListNodesItemDecoration(private val context: Context): SpaceItemDecoration(context) {

    override fun setBottomSpace(outRect: Rect, position: Int, itemCount: Int) {
        if (position == itemCount - 1) {
            outRect.bottom = spaceVerticalSize * 4
        } else {
            super.setBottomSpace(outRect, position, itemCount)
        }
    }

}