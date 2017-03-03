package com.wenchao.cardstack;

import android.util.Log;

public class DefaultStackEventListener implements CardStack.CardEventListener {

    private float mThreshold;

    public DefaultStackEventListener(int i) {
        mThreshold = i;
    }

    @Override
    public boolean swipeEnd(int section, float distance) {

        Log.d("rae", "swipeEnd:" + section + "-" + distance);

        return distance > mThreshold;
    }

    @Override
    public boolean swipeStart(int section, float distance) {

        Log.d("rae", "swipeStart:" + section + "-" + distance);
        return false;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {

        Log.d("rae", "swipeContinue:" + section + "-" + distanceX + "-" + distanceY);

        return false;
    }

    @Override
    public void discarded(int mIndex, int direction) {

        Log.d("rae", "discarded:" + mIndex + "-" + direction);
    }

    @Override
    public void topCardTapped() {
        Log.d("rae", "topCardTapped");
    }


}
