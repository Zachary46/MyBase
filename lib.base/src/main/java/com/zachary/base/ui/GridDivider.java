package com.zachary.base.ui;

import android.content.Context;
import android.support.annotation.ColorInt;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public class GridDivider extends Y_DividerItemDecoration {

    private GridDivider(Context context, int lineWidthDp, @ColorInt int colorRGB) {
        super(context, lineWidthDp, colorRGB);
    }

    @Override
    public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
        //顺序:left, top, right, bottom
        boolean[] booleans = {false, false, false, false};
        switch (itemPosition % 3) {
            case 0:
            case 1:
                //每一行前两个显示rignt和bottom
                booleans[2] = true;
                booleans[3] = true;
                break;
            case 2:
                //最后一个只显示bottom
                booleans[3] = true;
                break;
            default:
                break;
        }

        return booleans;
    }
}
