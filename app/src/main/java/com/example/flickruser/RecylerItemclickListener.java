package com.example.flickruser;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;


class RecylerItemclickListener  extends RecyclerView.SimpleOnItemTouchListener{
    private static final String TAG = "RecylerItemclickListen";
    interface OnRecyclerClickListener{
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int position);
    }
    private final OnRecyclerClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    public RecylerItemclickListener(Context context ,final RecyclerView recyclerView, OnRecyclerClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && mListener !=null){
                    Log.d(TAG, "onSingleTapUp: calling the long press");
                    mListener.onItemClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
                return true;
            }

            public void onLongPress(MotionEvent e) {

                Log.d(TAG, "onLongPress: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView !=null && mListener !=null){
                    Log.d(TAG, "onLongPress: calling Listener.onLongClick");
                    mListener.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent: starts");
        if(mGestureDetector !=null){
            boolean result = mGestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent return : "+ result);
            return result;
        }
        else{
            Log.d(TAG, "onInterceptTouchEvent return : false");
            return false;
        }
    }
}
