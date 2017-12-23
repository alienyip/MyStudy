package com.example.android.autoscrollback;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by Nature on 2017/11/9.
 */

public class ScrollUtil {

    public static void smoothScrollListViewToTop(final View scrollView, ScrollToTopListener listener) {
        if (scrollView == null) {
            return;
        }
        if (scrollView instanceof AbsListView) {
            smoothScrollListViewToTop((AbsListView) scrollView, listener);
        } else if (scrollView instanceof RecyclerView) {
            smoothScrollListViewToTop((RecyclerView) scrollView, listener);
        }
    }

    public static void smoothScrollListViewToTop(final AbsListView listView, final ScrollToTopListener listener) {
        if (listView == null) {
            return;
        }
        listView.smoothScrollToPositionFromTop(0,0);
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(0);
                if (listener != null) {
                    listener.scrollComplete();
                }
            }
        }, 300);
    }

    public static void smoothScrollListViewToTop(final RecyclerView recyclerView, final ScrollToTopListener listener) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.smoothScrollToPosition(0);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.scrollComplete();
                }
            }
        }, 300);
    }

    public interface ScrollToTopListener {
        void scrollComplete();
    }


}
