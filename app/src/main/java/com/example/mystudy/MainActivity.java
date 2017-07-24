package com.example.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mystudy.cehuaDemo.CehuaActivity;
import com.example.mystudy.expandableLinearLayout.ExpandableActivity;
import com.example.mystudy.itemDecoration.ItemDecorationActivity;
import com.example.mystudy.loadButton.LoadButtonActivity;
import com.example.mystudy.ncalendardemo.NCalendarActivity;
import com.example.mystudy.popupWindow.PopupWindowActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Demo> demoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdemos();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(demoList);
        recyclerView.setAdapter(adapter);
    }

    private void initdemos() {
        Demo popupWindow = new Demo("PopupWindow", PopupWindowActivity.class, "http://www.jianshu.com/p/799dbb86f908");
        demoList.add(popupWindow);
        Demo loadButton = new Demo("LoadButton", LoadButtonActivity.class, "http://blog.csdn.net/briblue/article/details/72470116");
        demoList.add(loadButton);
        Demo itemDecoration = new Demo("ItemDecoration", ItemDecorationActivity.class, "http://blog.csdn.net/briblue/article/details/70161917");
        demoList.add(itemDecoration);
        Demo cehuaDemo = new Demo("cehuaDemo", CehuaActivity.class, "http://www.jianshu.com/p/fb202d67c26b");
        demoList.add(cehuaDemo);
        Demo expandable = new Demo("Expandable", ExpandableActivity.class, "http://blog.csdn.net/chay_chan/article/details/72810770");
        demoList.add(expandable);
        Demo ncalendar = new Demo("NCalendar", NCalendarActivity.class, "http://blog.csdn.net/y12345654321/article/details/73331253");
        demoList.add(ncalendar);
    }
}
