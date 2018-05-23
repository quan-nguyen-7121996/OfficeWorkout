package com.quancuteexample.officeworkout;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Quan cute on 17/05/2018.
 */

public class CustomListAdapter extends BaseAdapter {

    private List<WorkoutExcercise> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,  List<WorkoutExcercise> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.exerciseView=(GifImageView) convertView.findViewById(R.id.exercise);
            holder.nameView = (TextView) convertView.findViewById(R.id.exerciseName);
            holder.repView = (TextView) convertView.findViewById(R.id.reps);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WorkoutExcercise exercise = this.listData.get(position);
        holder.nameView.setText(exercise.getName());
        holder.repView.setText("x " + exercise.getRep());
        //int imageId = this.getMipmapResIdByName(exercise.getGifName());
        //holder.exerciseView.setImageResource(imageId);
        Glide.with(context)
                .load(exercise.getGifName())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.exerciseView);
        return convertView;

    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        TextView nameView;
        GifImageView exerciseView;
        TextView repView;
    }
}
