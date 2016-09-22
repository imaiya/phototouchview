package com.unco.phototouchview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.unco.photoliarary.PhotoListener;
import com.unco.photoliarary.PhotoTouchView;
import com.unco.photoliarary.SaveImageCall;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/9/14 11:15
 * @Action :查看图片页面
 *
 * =================中康================
 */
public class PhotoTouchActivity extends AppCompatActivity implements PhotoListener {
    private PhotoTouchView mTouchView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initToolBar();
        mTouchView = (PhotoTouchView) findViewById(R.id.photo_view);
        ArrayList<String> imageList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.images)));
        mTouchView.addListener(this);//点击事件
        mTouchView.showImages(imageList);
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            mTouchView.saveCurrentImage(new SaveImageCall() {
                @Override
                public void onSucce() {
                    Toast.makeText(PhotoTouchActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFault() {
                    Toast.makeText(PhotoTouchActivity.this, "保存失败,请稍后再试", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void photoClick(int index, String url) {//单击
        finish();
    }

    @Override
    public void photoLongClick(int index, String url) {//长按
        new AlertDialog.Builder(this)
                .setItems(new String[]{"保存到手机", "分享"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mTouchView.saveCurrentImage(new SaveImageCall() {
                                @Override
                                public void onSucce() {
                                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFault() {
                                    Toast.makeText(getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "执行分享", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", null).create().show();
    }
}  