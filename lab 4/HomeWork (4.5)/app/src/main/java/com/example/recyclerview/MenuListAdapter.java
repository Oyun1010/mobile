package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;

public class MenuListAdapter  extends RecyclerView.Adapter<MenuListAdapter.MenuViewHolder> {

    private final String[] mNameList;
    private final String[] description;
    private final LinkedList<Integer> mImageList;

//    private LayoutInflater mInflater;

    Context context;
    public MenuListAdapter(Context context,String[] nameList, LinkedList<Integer> mImageList, String[] description) {
        this.context = context;
        this.mNameList = nameList;
        this.mImageList = mImageList;
        this.description = description;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View mItemView = mInflater.inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        holder.menuNameView.setText(mNameList[position]);
        holder.description.setText(description[position]);
        holder.menuImageView.setImageResource(mImageList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("LEN 0: ", String.valueOf(mNameList.length));
        Log.d("LEN 1: ", String.valueOf(description.length));
        Log.d("LEN 2: ", String.valueOf(mImageList.size()));
        return mNameList.length;
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView menuNameView;
        public final TextView description;
        public final ImageView menuImageView;

        final MenuListAdapter mAdapter;

        public MenuViewHolder(@NonNull View itemView, MenuListAdapter adapter)  {
            super(itemView);
            menuNameView = itemView.findViewById(R.id.menu_name);
            menuImageView = itemView.findViewById(R.id.imageview);
            description = itemView.findViewById(R.id.description);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            int mPosition = getAdapterPosition();

            Intent intent = new Intent(context.getApplicationContext(), SecondActivity.class);
            intent.putExtra("name", mNameList[mPosition]);
            intent.putExtra("image", mImageList.get(mPosition));
            intent.putExtra("index", mPosition);
            context.getApplicationContext().startActivity(intent);


        }
    }
}
