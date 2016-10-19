## PhotoTouchView ##
> 该库是一个Android图片查看缩放控件,在`PinchImageView`基础上进一步封装,主要功能:
> 
> - 作为一个控件,可以在任意场景下使用(`Activity`,`Fragment`,`ViewGroup`)
> - 网络和本地图片查看,支持双指缩放,双击缩放等
> - 支持单图及多图查看
> - 一键保存到本地相册
> - 单击长按事件

## 引用 ##
最新版本号[![](https://jitpack.io/v/chenzhenboy/phototouchview.svg)](https://jitpack.io/#chenzhenboy/phototouchview)
### Gradle ###

Project.gradle

    allprojects {
    	repositories {
        	jcenter()
        	maven { url "https://jitpack.io" }
    	}
	}

app.gradle

    compile 'com.github.chenzhenboy:phototouchview:1.1.1'

### Maven ###
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
and

	<dependency>
	    <groupId>com.github.chenzhenboy</groupId>
	    <artifactId>phototouchview</artifactId>
	    <version>1.1.1</version>
	</dependency>

## 使用 ##
1. 在布局xml中加入该自定义控件

        <com.unco.photoliarary.PhotoTouchView
        android:id="@+id/photo_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

2. java代码中传入图片集合即可

		mTouchView = (PhotoTouchView) findViewById(R.id.photo_view);
    	mTouchView.showImages(imageList);
    数据源支持:

	    mTouchView.showImages(imageList);//String数组,默认当前显示第一张
        mTouchView.showImages(imageList, 2);//String数组,指定当前显示index为2

        //实现ImageUrl接口的Bean
        List<TestBean> beanList = new ArrayList<>();
        beanList.add(new TestBean());
        mTouchView.showImages(beanList);
        mTouchView.showImages(beanList,2);//指定index
        
        mTouchView.showOneImage("url");//单张
		
		



## 扩展功能 ##
1. 保存当前图片到本地相册

    	mTouchView.saveCurrentImage(new SaveImageCall() {
                @Override
                public void onSucce() {//保存成功
                    Toast.makeText(PhotoTouchActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFault() {//保存失败
                    Toast.makeText(PhotoTouchActivity.this, "保存失败,请稍后再试", Toast.LENGTH_SHORT).show();
                }
            });

2. 图片的点击和长按事件

		mTouchView.addListener(new PhotoListener() {
            @Override
            public void photoClick(int index, String url) {
                
            }

            @Override
            public void photoLongClick(int index, String url) {

            }
        });
        
3. 图片翻页回调

        mTouchView.setChangeListener(new PhotoChangeListener() {
             @Override
             public void onPageChanged(int position) {
                  //do str
                  }
             });
             
4. 指示器

        mTouchView.setHideIndicator(true);//隐藏自带指示器(默认不隐藏)

## 效果图 ##

![](http://i.imgur.com/f03lEmW.jpg)

## 关于作者 ##

- 简	书:[uncochen](http://www.jianshu.com/users/1695117cc969 )
- 新浪微博:[@Chen丶振](http://weibo.com/724132180 )
- Email:18620156376@163.com



我会慢慢完善这个控件,加入更多的易用的API.大家有任何建议或者发现Bug都可以提Issues,也可以给我发邮件.

## Thx ##

#### [PinchImageView](https://github.com/boycy815/PinchImageView) ####

## License ##

    Copyright 2016 chenzhenboy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
