package kr.co.MarineLife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    Context context;
    private final String [] values;
    private final int [] images;
    LayoutInflater layoutInflater;
//    Dialog myDialog = new Dialog(this.context);

    public GridAdapter(Context context, String[] values, int[] images) {
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Holder holder = new Holder();
        View rowView;

        rowView = layoutInflater.inflate(R.layout.single_item, null);
        holder.tv =( TextView) rowView.findViewById(R.id.textview);
        holder.img = (ImageView) rowView.findViewById(R.id.imageview);

        holder.tv.setText(values[position]);
        holder.img.setImageResource(images[position]);

        return rowView;
    }
    public class Holder
    {
        TextView tv;
        ImageView img;
    }
}
