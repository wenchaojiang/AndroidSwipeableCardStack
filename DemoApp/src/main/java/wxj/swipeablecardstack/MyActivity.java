package wxj.swipeablecardstack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wenchao.cardstack.CardAnimator;
import com.wenchao.cardstack.CardStack;


public class MyActivity extends Activity {
    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mCardStack = (CardStack) findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_content);
//        mCardStack.setStackMargin(20);

        mCardAdapter = new CardsDataAdapter(getApplicationContext());
        mCardAdapter.add("test1");
        mCardAdapter.add("test2");
        mCardAdapter.add("test3");
        mCardAdapter.add("test4");
        mCardAdapter.add("test5");
//        mCardAdapter.add("test6");
//        mCardAdapter.add("test7");

        mCardStack.setAdapter(mCardAdapter);

        if (mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
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


        return super.onOptionsItemSelected(item);
    }
}
