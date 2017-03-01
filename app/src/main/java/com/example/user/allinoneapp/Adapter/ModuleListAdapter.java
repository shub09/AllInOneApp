package com.example.user.allinoneapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.allinoneapp.Model.DataModel;
import com.example.user.allinoneapp.R;

import java.util.List;

/**
 * Created by user on 2/7/2017.
 */

public class ModuleListAdapter extends ArrayAdapter<DataModel> {

        private List<DataModel> dataSet;
        Context mContext;

        // View lookup cache
        private static class ViewHolder {
            TextView txtName, txtUnreadMsg;
            ImageView imgIcon;
        }

        public ModuleListAdapter(List<DataModel> data, Context context) {
            super(context, R.layout.row_modules, data);
            this.dataSet = data;
            this.mContext=context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataModel dataModel = getItem(position);
            ViewHolder viewHolder;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.row_modules, parent, false);

                viewHolder.txtName = (TextView) convertView.findViewById(R.id.txv_module_name);
                viewHolder.txtUnreadMsg = (TextView) convertView.findViewById(R.id.unread_msg);
                viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txtName.setText(dataModel.getName());
            viewHolder.txtUnreadMsg.setText(dataModel.getUnreadMsg());
            viewHolder.imgIcon.setImageResource(dataModel.getIcon());

            return convertView;
        }
    }

