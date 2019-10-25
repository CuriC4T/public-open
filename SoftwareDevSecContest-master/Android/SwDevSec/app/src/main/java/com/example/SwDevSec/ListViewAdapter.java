package com.example.SwDevSec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.SwDevSec.Activity.DTO.SerDTO;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private List<SerDTO> list;

    public ListViewAdapter(Context context, List<SerDTO> list){
        // MainActivity 에서 데이터 리스트를 넘겨 받는다.
        this.list = list;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.addr = (TextView) convertView.findViewById(R.id.addr);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.image1);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 각 셀에 넘겨받은 텍스트 데이터를 넣는다.
        viewHolder.addr.setText( list.get(position).getAddr() );
        viewHolder.title.setText( list.get(position).getTitle() );
        viewHolder.image1.setImageBitmap(list.get(position).getFirstimageBM());
        return convertView;
    }

    class ViewHolder{
        public TextView addr;
        public TextView title;
        public ImageView image1;
    }
}
