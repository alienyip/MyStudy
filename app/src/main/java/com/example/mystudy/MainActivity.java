package com.example.mystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.mystudy.auditProgressView.AuditProgressViewActivity;
import com.example.mystudy.autoScrollBack.AutoScrollBackActivity;
import com.example.mystudy.cehuaDemo.CehuaActivity;
import com.example.mystudy.expandableLinearLayout.ExpandableActivity;
import com.example.mystudy.imagePicker.ImagePickerActivity;
import com.example.mystudy.itemDecoration.ItemDecorationActivity;
import com.example.mystudy.loadButton.LoadButtonActivity;
import com.example.mystudy.matisse.MatisseActivity;
import com.example.mystudy.niceDialog.NiceDialogActivity;
import com.example.mystudy.popupWindow.PopupWindowActivity;
import com.example.mystudy.refreshView.RefreshViewActivity;
import com.example.mystudy.springAnimation.SpringAnimationActivity;
import com.example.mystudy.stretchableFloatingBtn.StretchableFloatingBtnActivity;
import com.example.mystudy.tabLayout.TabLayoutActivity;
import com.example.mystudy.ultimateBar.UltimateBarActivity;
import com.example.mystudy.viewPagerTitle.ViewPagerTitleActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Demo> demoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =  findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        initdemos();
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
        Demo springAnimation = new Demo("SpringAnimation", SpringAnimationActivity.class, "http://blog.csdn.net/qq_34902522/article/details/77651799");
        demoList.add(springAnimation);
        Demo niceDialog = new Demo("NiceDialog", NiceDialogActivity.class,"http://www.jianshu.com/p/0529433d4522");
        demoList.add(niceDialog);
        Demo stretchableFloatingBtn = new Demo("StretchableFloatingBtn", StretchableFloatingBtnActivity.class, "http://blog.csdn.net/vroymond/article/details/76472551");
        demoList.add(stretchableFloatingBtn);
        Demo viewPagerTitle = new Demo("ViewPagerTitle", ViewPagerTitleActivity.class, "http://www.jianshu.com/p/77e811e1c987");
        demoList.add(viewPagerTitle);
        Demo autoScrollBack = new Demo("AutoScrollBack", AutoScrollBackActivity.class, "https://github.com/gaoneng102/AutoScrollBackLayout");
        demoList.add(autoScrollBack);
        Demo matisse = new Demo("Matisse", MatisseActivity.class, "http://www.jianshu.com/p/382346bf0aa9");
        demoList.add(matisse);
        Demo ultimateBar = new Demo("UltimateBar", UltimateBarActivity.class, "http://www.jianshu.com/p/b4d5a307f793");
        demoList.add(ultimateBar);
        Demo refreshView = new Demo("RefreshView", RefreshViewActivity.class, "http://www.jianshu.com/p/1a82cdab2249");
        demoList.add(refreshView);
        Demo auditProgressView = new Demo("AuditProgressView", AuditProgressViewActivity.class, "http://blog.csdn.net/qq_33553515/article/details/78356028");
        demoList.add(auditProgressView);
        Demo imagePicker = new Demo("ImagePicker", ImagePickerActivity.class, "http://www.jianshu.com/p/8561b1d1f763");
        demoList.add(imagePicker);
        Demo tabLayout = new Demo("TabLayout", TabLayoutActivity.class, "http://blog.csdn.net/qq_27258799/article/details/78413926");
        demoList.add(tabLayout);

    }
}
