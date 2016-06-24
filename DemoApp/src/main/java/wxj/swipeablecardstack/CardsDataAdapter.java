package wxj.swipeablecardstack;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardsDataAdapter extends ArrayAdapter<String> {

    private Context context;

    public CardsDataAdapter(Context context) {
        super(context, R.layout.card_content);
        this.context = context;
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        TextView v = (TextView)(contentView.findViewById(R.id.content));
        v.setText(getItem(position));
        ImageView imageView = (ImageView) contentView.findViewById(R.id.image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(context, "Image clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return contentView;
    }

}

