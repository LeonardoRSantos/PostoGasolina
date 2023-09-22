package com.example.postogasolina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.postogasolina.R;

public class CustomSpinnerAdapter extends ArrayAdapter<Integer> {

    private final Context context;
    private final Integer[] values;

    public CustomSpinnerAdapter(Context context, Integer[] values) {
        super(context, R.layout.item_spinner_layout, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_spinner_layout, parent, false);

        ImageView imageView = rowView.findViewById(R.id.imageView);
        imageView.setImageResource(values[position]);

        return rowView;
    }
}

