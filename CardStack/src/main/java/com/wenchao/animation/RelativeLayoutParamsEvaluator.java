package com.wenchao.animation;

import android.animation.TypeEvaluator;
import android.widget.RelativeLayout.LayoutParams
        ;

import com.wenchao.cardstack.CardUtils;

public class RelativeLayoutParamsEvaluator implements TypeEvaluator<LayoutParams> {

    @Override
    public LayoutParams evaluate(float fraction, LayoutParams start,
                                 LayoutParams end) {

        LayoutParams result = CardUtils.cloneParams(start);
        result.leftMargin += ((end.leftMargin-start.leftMargin)*fraction);
        result.rightMargin += ((end.rightMargin-start.rightMargin)*fraction);
        result.topMargin += ((end.topMargin-start.topMargin)*fraction);
        result.bottomMargin += ((end.bottomMargin-start.bottomMargin)*fraction);
        return result;
    }

}
