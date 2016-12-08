package com.unco.photoliarary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.unco.photoliarary.Indicator.DotIndicator;
import com.unco.photoliarary.callback.PhotoChangeListener;
import com.unco.photoliarary.callback.PhotoListener;
import com.unco.photoliarary.callback.SaveImageCall;
import com.unco.photoliarary.tool.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/9/14 10:14
 * @Action :PhotoTouchView,图片缩放朱控件
 *
 * =================中康================
 */
public class PhotoTouchView extends LinearLayout implements TouchViewPager.OnPageChangeListener {
    private Context mContext;
    private TouchViewPager mViewPager;
    private ImageTouchAdapter mAdapter;

    private ArrayList<String> mImageList;
    private int mCurrentIndex;
    private DotIndicator mIndicator;
    public static final String TRANSITION_NAME="tran_iamge";


    private boolean hideIndicator = false;


    private PhotoChangeListener mChangeListener;

    /*<=========================================共有方法===============================================>*/

    /**
     * 点击和长按监听
     *
     * @param listener
     */
    public void addListener(PhotoListener listener) {
        mAdapter.addListener(listener);
    }

    /**
     * 获取当前浏览的图片下标
     *
     * @return
     */
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    /**
     * 设置当前浏览的下标
     *
     * @param currentIndex
     */
    public void setCurrentIndex(int currentIndex) {
        if (mImageList != null && mImageList.size() > currentIndex) {
            mCurrentIndex = currentIndex;
            mViewPager.setCurrentItem(mCurrentIndex);
            mIndicator.setCurrentPage(mCurrentIndex);
        }
    }

    /**
     * 显示一组图片,当前展示第一张
     *
     * @param imageList
     */
    public void showImages(ArrayList<String> imageList) {
        mImageList.clear();
        mImageList.addAll(imageList);
        mIndicator.initData(imageList.size(), 0);
        mAdapter.notifyDataSetChanged();
        mCurrentIndex = 0;
        mViewPager.setCurrentItem(mCurrentIndex);
        mIndicator.setCurrentPage(mCurrentIndex);
    }

    /**
     * 显示一组图片,当前展示第一张
     *
     * @param beanList 图片的数据源,由一组Bean组成,bean必须实现ImageUrl接口
     */
    public void showImages(List<? extends ImageUrl> beanList) {
        mImageList.clear();
        for (ImageUrl bean : beanList) {
            mImageList.add(bean.getUrl());
        }
        mIndicator.initData(beanList.size(), 0);
        mAdapter.notifyDataSetChanged();
        if (mImageList != null && mImageList.size() > mCurrentIndex)
            mViewPager.setCurrentItem(mCurrentIndex);

    }

    /**
     * 显示一组图片并指定当前显示位置
     *
     * @param beanList
     * @param currentIndex
     */
    public void showImages(List<? extends ImageUrl> beanList, int currentIndex) {
        mImageList.clear();
        for (ImageUrl bean : beanList) {
            mImageList.add(bean.getUrl());
        }
        mIndicator.initData(beanList.size(), 0);
        mAdapter.notifyDataSetChanged();
        if (mImageList != null && mImageList.size() > currentIndex) {
            mCurrentIndex = currentIndex;
            mViewPager.setCurrentItem(mCurrentIndex);
            mIndicator.setCurrentPage(mCurrentIndex);
        }
    }

    /**
     * 显示一组图片并指定当前显示位置
     *
     * @param imageList
     * @param currentIndex
     */
    public void showImages(ArrayList<String> imageList, int currentIndex) {
        mImageList.clear();
        mImageList.addAll(imageList);
        mIndicator.initData(imageList.size(), 0);
        mAdapter.notifyDataSetChanged();
        if (mImageList != null && mImageList.size() > currentIndex) {
            mCurrentIndex = currentIndex;
            mViewPager.setCurrentItem(mCurrentIndex);
            mIndicator.setCurrentPage(mCurrentIndex);
        }
    }

    public void showOneImage(String url) {
        mImageList.clear();
        mImageList.add(url);
        mIndicator.initData(1, 0);
        mAdapter.notifyDataSetChanged();
        mCurrentIndex = 0;
        mViewPager.setCurrentItem(mCurrentIndex);
    }

    /**
     * 更改数据源后可以手动刷新
     */
    public void update() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 保存当前浏览的图片到本地相册
     */
    public void saveCurrentImage(SaveImageCall imageCall) {
        saveCurrent2local(imageCall);
    }

    /**
     * 翻页事件
     *
     * @param changeListener
     */
    public void setChangeListener(PhotoChangeListener changeListener) {
        mChangeListener = changeListener;
    }

    /**
     * 是否隐藏指示器,默认不隐藏
     *
     * @param hideIndicator
     */
    public void setHideIndicator(boolean hideIndicator) {
        this.hideIndicator = hideIndicator;
        mIndicator.setVisibility(this.hideIndicator ? INVISIBLE : VISIBLE);
    }

    /*<========================================================================================>*/
    public PhotoTouchView(Context context) {
        this(context, null);
    }

    public PhotoTouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        includeLayout();
        initViewPager();
    }

    private void initViewPager() {
        mImageList = new ArrayList<>();
        mAdapter = new ImageTouchAdapter(mContext, mImageList, mViewPager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void includeLayout() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_photo, null);
        mViewPager = (TouchViewPager) view.findViewById(R.id.viewpager);
        mIndicator = (DotIndicator) view.findViewById(R.id.Indicator);
        this.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentIndex = position;
        mIndicator.setCurrentPage(position);
        if (mChangeListener != null) mChangeListener.onPageChanged(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void saveCurrent2local(final SaveImageCall imageCall) {
        String url = mImageList.get(getCurrentIndex());
        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BitmapUtil.saveImage2Gallery(mContext, resource, imageCall);
                    }
                }).start();

            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (imageCall != null) imageCall.onFault();
            }
        });
    }
}  