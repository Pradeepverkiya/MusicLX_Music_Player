package com.musiclx.musiclx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongInfo> songs;
    Context context;

    OnItemClickListener onItemClickListener;

    SongAdapter(Context context, ArrayList<SongInfo> songs){
        this.context = context;
        this.songs = songs;
    }
     public  interface OnItemClickListener {
      void onItemClick(Button b, View v, SongInfo obj, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View myview = LayoutInflater.from(context).inflate(R.layout.row_songs,parent,false);
        return new SongHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongHolder songHolder, final int i)
    {
       final SongInfo c= songs.get(i);
       songHolder.songName.setText(c.songName);
       songHolder.artistName.setText(c.artistName);
       songHolder.btnAction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (onItemClickListener != null){

                   onItemClickListener.onItemClick(songHolder.btnAction, v,c,i);
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder
    {
        TextView songName,artistName;
        Button btnAction;
        public SongHolder(View itemView) {
            super(itemView);
            songName= itemView.findViewById(R.id.tvSongName);
            artistName= itemView.findViewById(R.id.tvArtistName);
            btnAction= itemView.findViewById(R.id.tvbtnAction);
        }
    }
}
