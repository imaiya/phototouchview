package com.unco.photoliarary;

/**
 * =================中康================
 *
 * @Author: 陈振
 * @Email : 18620156376@163.com
 * @Time : 2016/9/21 16:18
 * @Action :
 *
 * =================中康================
 */
public interface PhotoListener {
    void photoClick(int index, String url);//单击

    void photoLongClick(int index, String url);//长按
}  