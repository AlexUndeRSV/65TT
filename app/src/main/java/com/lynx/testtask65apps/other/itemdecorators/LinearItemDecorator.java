package com.lynx.testtask65apps.other.itemdecorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearItemDecorator extends RecyclerView.ItemDecoration {

    private final int space;

    public LinearItemDecorator(final int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent, final RecyclerView.State state) {

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = space;
            outRect.top = space;
            outRect.right = space;
        } else if (parent.getChildLayoutPosition(view) == state.getItemCount() - 1) {
            outRect.left = space;
            outRect.top = space / 3 * 2;
            outRect.right = space;
            outRect.bottom = space;
        } else {
            outRect.left = space;
            outRect.top = space / 2;
            outRect.right = space;
        }
    }
}
