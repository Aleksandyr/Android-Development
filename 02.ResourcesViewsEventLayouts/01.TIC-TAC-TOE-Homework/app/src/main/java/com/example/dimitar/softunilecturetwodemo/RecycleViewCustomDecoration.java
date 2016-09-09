package com.example.dimitar.softunilecturetwodemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dimitar on 3.9.2016 г..
 */

public class RecycleViewCustomDecoration extends RecyclerView.ItemDecoration {

    Paint whitePaint, blackPaint;
    int offset;

    public RecycleViewCustomDecoration() {
        offset = 1;

        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);

        whitePaint.setStyle(Paint.Style.FILL);
        blackPaint.setStyle(Paint.Style.FILL);

        whitePaint.setStrokeWidth(1f);
        blackPaint.setStrokeWidth(1f);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(offset, offset, offset, offset);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child),
                    layoutManager.getDecoratedTop(child),
                    layoutManager.getDecoratedRight(child),
                    layoutManager.getDecoratedBottom(child),
                    blackPaint);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + offset,
                    layoutManager.getDecoratedTop(child) + offset,
                    layoutManager.getDecoratedRight(child) - offset,
                    layoutManager.getDecoratedBottom(child) - offset,
                    whitePaint);

        }

    }
}
