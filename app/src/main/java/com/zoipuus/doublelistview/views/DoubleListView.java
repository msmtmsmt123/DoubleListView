package com.zoipuus.doublelistview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zoipuus.doublelistview.R;


/**
 * Created by zoipuus on 2016/3/13.
 * double listView
 */
public class DoubleListView extends LinearLayout {

    private OnItemClickListener listener;
    private ListView listLeft;
    private ListView listRight;

    public DoubleListView(Context context) {
        this(context, null);
    }

    public DoubleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews(context);
    }

    public void setAdapters(BaseAdapter adapter1, BaseAdapter adapter2) {
        listLeft.setAdapter(adapter1);
        listRight.setAdapter(adapter2);

        setListViewOnTouchAndScrollListener(listLeft, listRight);
    }

    private void setupViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.list_view_double, this);
        listLeft = (ListView) findViewById(R.id.list_left);
        listRight = (ListView) findViewById(R.id.list_right);
    }

    private void setOnItemClickListener(ListView listLeft, ListView listRight) {
        setOnItemClickListener(listLeft);
        setOnItemClickListener(listRight);
    }

    private void setOnItemClickListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onItemClick(parent, view, position, id);
                }
            }
        });
    }

    private void setListViewOnTouchAndScrollListener(ListView listLeft, ListView listRight) {
        setScrollListener(listLeft, listRight);
        setScrollListener(listRight, listLeft);
    }

    private void setScrollListener(final ListView listView1, final ListView listView2) {
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                View subView = view.getChildAt(0);
                if (subView != null) {
                    final int top = subView.getTop();
                    if (listView2.getChildAt(0) != null) {
                        final int firstVisibleItem = view.getFirstVisiblePosition();
                        if (top > 0) {
                            listView2.scrollTo(0, -top);
                        } else {
                            listView1.setSelectionFromTop(firstVisibleItem, top);
                            listView2.setSelectionFromTop(firstVisibleItem, top);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View subView = view.getChildAt(0);
                if (subView != null) {
                    final int top = subView.getTop();
                    if (listView2.getChildAt(0) != null) {
                        if (top > 0) {
                            listView2.scrollTo(0, -top);
                        } else {
                            listView1.setSelectionFromTop(firstVisibleItem, top);
                            listView2.setSelectionFromTop(firstVisibleItem, top);
                        }
                    }
                }
            }
        });
    }

    public void setListener(OnItemClickListener listener) {
        setOnItemClickListener(listLeft, listRight);
        this.listener = listener;
    }

    public interface OnItemClickListener {
        /**
         * @param parent   listView
         * @param view     Item
         * @param position position of item int the listView
         * @param id       position of the adapter
         */
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }
}
