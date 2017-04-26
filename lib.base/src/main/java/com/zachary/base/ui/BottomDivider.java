package com.zachary.base.ui;

import android.content.Context;
import android.support.annotation.ColorInt;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public class BottomDivider extends Y_DividerItemDecoration {

    public BottomDivider(Context context, int lineWidthDp, @ColorInt int colorRGB) {
        super(context, lineWidthDp, colorRGB);
    }

    @Override
    public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
        //顺时针顺序:left, top, right, bottom
        boolean[] isOffset = {false, false, false, true};//默认只有bottom显示分割线

        return isOffset;
    }
}