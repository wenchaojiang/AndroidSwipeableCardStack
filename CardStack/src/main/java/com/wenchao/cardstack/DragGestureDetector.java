package com.wenchao.cardstack;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

//detect both tap and drag
public class DragGestureDetector implements View.OnTouchListener {
    public static String DEBUG_TAG = "DragGestureDetector";
    private GestureDetectorCompat mGestureDetector;
    private DragListener mListener;
    private boolean mStarted = false;
    private MotionEvent mOriginalEvent;

    @Override public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                if(mStarted) {
                    mListener.onDragEnd(mOriginalEvent, event);
                }
                mStarted = false;
                break;
            case (MotionEvent.ACTION_DOWN) :
                //need to set this, quick tap will not generate drap event, so the
                //originalEvent may be null for case action_up
                //which lead to null pointer
                mOriginalEvent = event;
                break;
        }
        return true;
    }

    public interface DragListener {
        boolean onDragStart(MotionEvent e1, MotionEvent e2, float distanceX,
                                float distanceY);
        boolean onDragContinue(MotionEvent e1, MotionEvent e2, float distanceX,
                                   float distanceY);
        boolean onDragEnd(MotionEvent e1, MotionEvent e2);

        boolean onTapUp();
    }

    public DragGestureDetector(Context context, DragListener myDragListener){
        mGestureDetector = new GestureDetectorCompat(context,new MyGestureListener());
        mListener = myDragListener;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                float distanceY) {
            if(mListener == null) return true;
            if(!mStarted){
                mListener.onDragStart(e1,e2,distanceX,distanceY);
                mStarted = true;
            }
            else{
                mListener.onDragContinue(e1,e2,distanceX,distanceY);
            }
            mOriginalEvent = e1;
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            return mListener.onTapUp();
        }
    }


}
