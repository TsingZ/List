package com.luqingzheng.list;

import com.luqingzheng.list.ListViewAdapter.ViewHolder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView listView;
    private ListViewAdapter adapter;
    private List<Contacts> items = new ArrayList<Contacts>();
    Contacts item;
    CheckBox checkBox;
    ImageView listview_image;
    TextView listview_text,subtext;
    public static String subnm = "This is item ";
    StringBuilder subname = new StringBuilder();
    private final String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initData();

        listview_image = findViewById(R.id.listview_image);
        listview_text = findViewById(R.id.listview_text);
        subtext = findViewById(R.id.subtext);
        listView = findViewById(R.id.list_view);
        checkBox = findViewById(R.id.checkbox);
        adapter = new ListViewAdapter(items,getApplicationContext(),false);
        listView.setAdapter(adapter);

        final TextView edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (!ListViewAdapter.isShow) {
                    Message message = Message.obtain();
                    message.what = 1;
                    handler.sendMessage(message);

                    adapter.notifyDataSetChanged();
                    translateToRight(listView);
                    edit.setText("取消");
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
                        {
                            adapter.notifyDataSetChanged();

                            ViewHolder holder = (ViewHolder) view.getTag();
                            holder.checkBox.toggle();

                            //ListViewAdapter.getIsSelected().put(position, holder.checkBox.isChecked());
                        }
                    });
                    //ListViewAdapter.isShow = true;
                } else {
                    Message message = Message.obtain();
                    message.what = 0;
                    handler.sendMessage(message);

                    //adapter.notifyDataSetChanged();
                    translateToLeft(listView);
                    listView.clearAnimation();
                    edit.setText("选择");
                    listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
                    //ListViewAdapter.isShow = false;
                }
            }
        });

    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
    }

    public void initData() {
        for (int i = 1; i <= 50; i++) {
            for (int j=1;j<=8;j++){
            subname.append(subnm).append(i).append("  ");
            }
            item = new Contacts("Item"+i,subname.toString(),R.drawable.icon);
            items.add(item);
            subname.delete(0,subname.length());
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new  Handler() {

        @Override
        public void handleMessage(Message msg)
        {
            if (msg.what == 1) {
                //adapter = new ListViewAdapter(items, getApplicationContext(), true);
                adapter.notifyDataSetChanged();
                //listView.setAdapter(adapter);
                ListViewAdapter.isShow = true;
                Log.i(TAG,"msg=1");
            } else if (msg.what == 0) {
                //adapter = new ListViewAdapter(items, getApplicationContext(), false);
                adapter.notifyDataSetChanged();
               // listView.setAdapter(adapter);
                ListViewAdapter.isShow = false;
                Log.i(TAG,"msg=0");
            }
        }
    };

    public void translateToRight(View view) {
        Animation translateToRightAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.list_anim_toright);
        view.setAnimation(translateToRightAnimation);
        view.startAnimation(translateToRightAnimation);
    }

    public void translateToLeft(View view) {
        Animation translateToLeftAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.list_anim_toleft);
        view.setAnimation(translateToLeftAnimation);
        view.startAnimation(translateToLeftAnimation);
    }
}