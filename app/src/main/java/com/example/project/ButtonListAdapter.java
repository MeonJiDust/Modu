package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ButtonListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> data1;
    ArrayList<String> data2;
    public ButtonListAdapter(Context context, ArrayList<String> data1, ArrayList<String> data2){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data1 = data1;
        this.data2 = data2;
    }
    @Override
    public int getCount() {
        return data1.size();
    }

    @Override
    public Object getItem(int position) {
        return data1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.list_layout, null);

        TextView title = view.findViewById(R.id.title);
        title.setText(data1.get(position));

        TextView contents = view.findViewById(R.id.contents);
        contents.setText(data2.get(position));

        Button buttonView = view.findViewById(R.id.apply_button);

        buttonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "지원이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
