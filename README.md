AndroidSwipeableCardStack
=========================
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidSwipeableCardStack-green.svg?style=true)](https://android-arsenal.com/details/1/2724)

##RAE Change log:

- 添加循环滚动
- 添加旋转配置
- 添加卡片方向配置

##RAE Configuration

可以在布局文件进行初始化配置：

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                tools:context=".MyActivity">

    <com.wenchao.cardstack.CardStack
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clipChildren="false"
        android:clipToPadding="false"
        android:gravity="center"
        android:padding="10dp"
        app:card_enable_loop="true"
        app:card_enable_rotation="true"
        app:card_gravity="top"
        app:card_margin="10dp"
        app:card_stack_size="4"/>

</RelativeLayout>

```

配置参数说明：


```xml
   <declare-styleable name="CardStack">
        <!--卡片背景颜色-->
        <attr name="card_backgroundColor" format="color"/>
        <!-- 显示的卡片数量-->
        <attr name="card_stack_size" format="integer"/>
        <!--是否无限循环卡片模式-->
        <attr name="card_enable_loop" format="boolean"/>
        <!--卡片之间的间距-->
        <attr name="card_margin" format="dimension"/>
        <!--卡片拖动时是否跟着旋转-->
        <attr name="card_enable_rotation" format="boolean"/>
        <!--卡片方向：向上、向下-->
        <attr name="card_gravity">
            <enum name="top" value="48"/>
            <enum name="bottom" value="80"/>
        </attr>
    </declare-styleable>
```

也可以在代码中设置：
```java

	// 重置
	if (id == R.id.action_reset) {
	    mCardStack.reset(true);
	    return true;
	}
	
	// 底部
	if (id == R.id.action_bottom) {
	    mCardStack.setStackGravity(mCardStack.getStackGravity() == CardAnimator.TOP ? CardAnimator.BOTTOM : CardAnimator.TOP);
	    mCardStack.reset(true);
	    return true;
	}
	
	// 循环
	if (id == R.id.action_loop) {
	    mCardStack.setEnableLoop(!mCardStack.isEnableLoop());
	    mCardStack.reset(true);
	}
	
	// 是否允许旋转
	if (id == R.id.action_rotation) {
	    mCardStack.setEnableRotation(!mCardStack.isEnableRotation());
	    mCardStack.reset(true);
	}
	
	// 可见个数
	if (id == R.id.action_visibly_size) {
	    mCardStack.setVisibleCardNum(mCardStack.getVisibleCardNum() + 1);
	}
	
	// 间隔
	if (id == R.id.action_span) {
	    mCardStack.setStackMargin(mCardStack.getStackMargin() + 10);
	}

```

Change log:
Now it is compatible with api level 14
Investigating compatibility with api level 13



![image](https://github.com/raee/AndroidSwipeableCardStack/raw/RAE-DEV/pics/demo.gif)



A tinder like swipeable card stack component. Provide "swipe to like" effects. Easy to customize card views.


See youtube demo : https://www.youtube.com/watch?v=YsMnLJeouf8&feature=youtu.be
A Demo App is also included in the source.


Installation
---
Use jitpack
```groovy
repositories {
   maven { url "https://jitpack.io" }
}

dependencies {
   compile 'com.github.wenchaojiang:AndroidSwipeableCardStack:0.*.*'
}
```
OR manually

1. Download released .aar file
[Download current release] (https://github.com/wenchaojiang/AndroidSwipeableCardStack/releases/)

2. put it into your project lib dir, "libs" for example.

3. put following lines to your gradle.build file
```groovy
repositories {
    flatDir {
        dirs 'libs'
    }
}

dependencies {
    compile(name:'android-card-stack-0.1.0', ext:'aar')
}
```

Configuration
-----


Put CardStack in your layout file

```xml
 <com.wenchao.cardstack.CardStack
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding = "20dp"
        android:clipChildren="false"
        android:clipToPadding="false"
    />
```

Create your card view layout file.

Example: card_layout.xml, contain only a TextView
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/content"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
    />

</LinearLayout>
```

Implement your own adapter for the card stack. The CardStack will accept ArrayAdapter.
The Following example extends a simple ArrayAdapter<String>, overriding ```getView()``` to supply your customized card layout

```java
public class CardsDataAdapter extends ArrayAdapter<String> {

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        //supply the layout for your card
        TextView v = (TextView)(contentView.findViewById(R.id.content));
        v.setText(getItem(position));
        return contentView;
    }

}
```
Get the CardStack instance in your activity

```java
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mCardStack = (CardStack)findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_content);
        mCardStack.setStackMargin(20);
        
  }
  ```
  
Finally, set the adapter 


```java
    mCardAdapter = new CardsDataAdapter(getApplicationContext(),0);
    mCardAdapter.add("test1");
    mCardAdapter.add("test2");
    mCardAdapter.add("test3");
    mCardAdapter.add("test4");
    mCardAdapter.add("test5");
    
    mCardStack.setAdapter(mCardAdapter);
```


Listening to card stack event 
----
implement CardStack.CardEventListener, and set it as listener ```mCardStack.setListener(yourListener);   ```

```java
Class YourListener extends CardStack.CardEventListener{
    //implement card event interface
    @Override
    public boolean swipeEnd(int direction, float distance) {
        //if "return true" the dismiss animation will be triggered 
        //if false, the card will move back to stack
        //distance is finger swipe distance in dp
        
        //the direction indicate swipe direction
        //there are four directions
        //  0  |  1
        // ----------
        //  2  |  3
        
        return (distance>300)? true : false;
    }

    @Override
    public boolean swipeStart(int direction, float distance) {
    
        return true;
    }

    @Override
    public boolean swipeContinue(int direction, float distanceX, float distanceY) {
        
        return true;
    }

    @Override
    public void discarded(int id, int direction) {
       //this callback invoked when dismiss animation is finished. 
    }
    
    @Override
    public void topCardTapped() {
         //this callback invoked when a top card is tapped by user. 
    }
}
```


TODO
----

1. deploy to maven central
2. compatibility with api level 13
