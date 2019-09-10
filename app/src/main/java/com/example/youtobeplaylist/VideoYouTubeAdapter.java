package com.example.youtobeplaylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoYouTubeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<VideoYoutube> youtubeList;

    public VideoYouTubeAdapter(Context context, int layout, List<VideoYoutube> youtubeList) {
        this.context = context;
        this.layout = layout;
        this.youtubeList = youtubeList;
    }

    @Override
    public int getCount() {
        return youtubeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgThumbnail;
        TextView txtTitle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTitle = (TextView) view.findViewById(R.id.textViewTitle);
            holder.imgThumbnail = (ImageView) view.findViewById(R.id.imageviewThumbnaili);
            view.setTag(holder);
        }else  {
            holder = (ViewHolder) view.getTag();
        }

        VideoYoutube videoYoutube = youtubeList.get(i);
        holder.txtTitle.setText(videoYoutube.getTittle());
        Picasso.get().load(videoYoutube.getThumbnail()).into(holder.imgThumbnail);

        return view;
    }
}
