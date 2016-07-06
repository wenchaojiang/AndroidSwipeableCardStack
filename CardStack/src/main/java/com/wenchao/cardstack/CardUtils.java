package com.wenchao.cardstack;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class CardUtils {
    final static int DIRECTION_TOP_LEFT = 0;
    final static int DIRECTION_TOP_RIGHT = 1;
    final static int DIRECTION_BOTTOM_LEFT = 2;
    final static int DIRECTION_BOTTOM_RIGHT = 3;

    public static void scale(View v, int pixel, int gravity) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
        params.leftMargin -= pixel * 3;
        params.rightMargin -= pixel * 3;
        if (gravity == CardAnimator.TOP) {
            params.topMargin += pixel;
        } else {
            params.topMargin -= pixel;
        }
        params.bottomMargin -= pixel;
        v.setLayoutParams(params);
    }

    public static LayoutParams getMoveParams(View v, int upDown, int leftRight) {
        RelativeLayout.LayoutParams original = (RelativeLayout.LayoutParams) v.getLayoutParams();
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(original);
        RelativeLayout.LayoutParams params = cloneParams(original);

        params.leftMargin += leftRight;
        params.rightMargin -= leftRight;
        params.topMargin -= upDown;
        params.bottomMargin += upDown;
        return params;
    }

    public static void move(View v, int upDown, int leftRight) {
        RelativeLayout.LayoutParams params = getMoveParams(v, upDown, leftRight);
        v.setLayoutParams(params);
    }

    public static LayoutParams scaleFrom(View v, LayoutParams params, int pixel, int gravity) {
        Log.d("pixel", "onScroll: " + pixel);
        params = cloneParams(params);
        params.leftMargin -= pixel;
        params.rightMargin -= pixel;
        if (gravity == CardAnimator.TOP) {
            params.topMargin += pixel;
        } else {
            params.topMargin -= pixel;
        }
        params.bottomMargin -= pixel;
        v.setLayoutParams(params);

        return params;
    }

    public static LayoutParams moveFrom(View v, LayoutParams params, int leftRight, int upDown, int gravity) {
        params = cloneParams(params);
        params.leftMargin += leftRight;
        params.rightMargin -= leftRight;

        if (gravity == CardAnimator.BOTTOM) {
            params.bottomMargin += upDown;
            params.topMargin -= upDown;
        } else {
            params.bottomMargin -= upDown;
            params.topMargin += upDown;
        }
        v.setLayoutParams(params);

        return params;
    }

    //a copy method for RelativeLayout.LayoutParams for backward compartibility
    public static RelativeLayout.LayoutParams cloneParams(RelativeLayout.LayoutParams params) {
        RelativeLayout.LayoutParams copy = new RelativeLayout.LayoutParams(params.width, params.height);
        copy.leftMargin = params.leftMargin;
        copy.topMargin = params.topMargin;
        copy.rightMargin = params.rightMargin;
        copy.bottomMargin = params.bottomMargin;
        int[] rules = params.getRules();
        for (int i = 0; i < rules.length; i++) {
            copy.addRule(i, rules[i]);
        }
        //copy.setMarginStart(params.getMarginStart());
        //copy.setMarginEnd(params.getMarginEnd());

        return copy;
    }

    public static float distance(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static int direction(float x1, float y1, float x2, float y2) {
        if (x2 > x1) {//RIGHT
            if (y2 > y1) {//BOTTOM
                return DIRECTION_BOTTOM_RIGHT;
            } else {//TOP
                return DIRECTION_TOP_RIGHT;
            }
        } else {//LEFT
            if (y2 > y1) {//BOTTOM
                return DIRECTION_BOTTOM_LEFT;
            } else {//TOP
                return DIRECTION_TOP_LEFT;
            }
        }
    }


}
