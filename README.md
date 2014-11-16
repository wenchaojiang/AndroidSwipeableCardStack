AndroidSwipeableCardStack
=========================
![image](https://raw.githubusercontent.com/wenchaojiang/AndroidSwipeableCardStack/master/pics/image.png ==250x)



A tinder like swipeable card stack component. Provide "swipe to like" effects. Esay to costomize card views.

!! currently only compatible to api level 19. There is a plan to make it compatible with at least api level 13 very soon.

See youtube demo : https://www.youtube.com/watch?v=YsMnLJeouf8&feature=youtu.be
A Demo App is also included in the source.


Installation
----

1. Download released .aar file
[Download current release] (https://github.com/wenchaojiang/AndroidSwipeableCardStack/releases/download/0.1.0/android-card-stack-0.1.0.aar)

2. put it into your project lib dir, "libs" for example.

3. put following lines to your gradle.build file
```
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

Example: card_layout.xml, contain only a textview
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
The Following example extends a simeple ArrayAdapter<Stirng>, overriding ```getView()``` to supply your costomized card layout

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
    public void discarded(int direction,int direction) {
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
