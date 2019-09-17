package example.myapplication24;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    final String TAG = "测试MainActivity";
    DrawerLayout drawerLayout;
    MyDrawerListAdapter drawerListAdapter;
    ArrayList<MyDrawerListItem> drawerListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化DrawerLayout
        drawerLayout = (DrawerLayout)findViewById(R.id.id_drawer_layout);
        drawerLayout.setScrimColor(0xAACCCCCC); // 抽屉拉开时ContentView的灰化颜色

        // 设置DrawerLayout里的菜单ListView
        // 初始化ListView
        drawerListAdapter = new MyDrawerListAdapter();
        ListView drawerListView = (ListView) findViewById(R.id.id_drawer_menu);
        drawerListView.setAdapter(drawerListAdapter); // 设置适配器

        // 点击ListView
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                MyDrawerListItem it = (MyDrawerListItem) drawerListAdapter.getItem(i);
                onDrawerItemClicked(view, it);
            }
        });

        // 初始化抽屉里的菜单项
        drawerListData.add(new MyDrawerListItem("我的超级会员", "cmd1", null));
        drawerListData.add(new MyDrawerListItem("QQ钱包",   "cmd2", null));
        drawerListData.add(new MyDrawerListItem("个性装扮", "cmd3", null));
        drawerListData.add(new MyDrawerListItem("我的收藏", "cmd4", null));
        drawerListData.add(new MyDrawerListItem("我的相册", "cmd5", null));
        drawerListData.add(new MyDrawerListItem("我的文件", "cmd6", null));

    }

    // 抽屉菜单里的菜单项被点击
    private void onDrawerItemClicked(View view, MyDrawerListItem it)
    {
        Log.w(TAG, "点击了菜单项: " + it.cmd);
        drawerLayout.closeDrawer(Gravity.START, true);
    }

    //////////// 适配器 //////////////////
    private class MyDrawerListAdapter extends BaseAdapter
    {
        @Override
        public int getCount()
        {
            return drawerListData.size();
        }

        @Override
        public Object getItem(int position)
        {
            return drawerListData.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // 创建控件
            if (convertView == null)
            {
                convertView = getLayoutInflater()
                        .inflate(R.layout.activity_main_drawer_listitem, parent, false);
            }

            // 获取/显示数据
            MyDrawerListItem it = (MyDrawerListItem) getItem(position);
            ((TextView) convertView.findViewById(R.id.id_item_title)).setText(it.title);
            return convertView;
        }
    }

    static class MyDrawerListItem
    {
        public String title; // 菜单项显示文本
        public String cmd; // 菜单项对应的命令
        public Drawable icon; // 菜单项左侧显示的图标(本例忽略)

        public MyDrawerListItem(){}
        public MyDrawerListItem(String title, String cmd, Drawable icon)
        {
            this.title = title;
            this.cmd = cmd;
            this.icon = icon;
        }
    }
}



