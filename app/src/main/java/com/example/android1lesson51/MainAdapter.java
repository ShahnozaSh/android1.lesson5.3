package com.example.android1lesson51;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements PopupMenu.OnMenuItemClickListener {

    public List<Title> list;
    private Context context;
    ItemClickListener listener;
    int position;

    public MainAdapter(List<Title> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setElement(Title title, int position) {
        list.set(position, title);
        this.position = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(list.get(position));
        Log.d("Adapter", "onBindViewHolder" + position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // popups methods
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.popupEdit:
                // Log.d(TAG, "onMenuItemClick: action_pop_up");

                return true;

            case R.id.popupDelete:
                //Log.d(TAG, "onMenuItemClick: action_popup_delete");
                list.remove(position);
                notifyDataSetChanged();
                return true;

            default:
                return false;
        }
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {//патамуша тут текст вию, а нам нада нажать на нее как на кнопку, поял?

        public TextView textView;
        public Title title;
        public TextView textViewDate;
        ImageButton imageButtonPopup;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(this);


            Log.d("Adapter", "onCreateViewHolder");
            textView = itemView.findViewById(R.id.textView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageButtonPopup=itemView.findViewById(R.id.imageButtonPopup);
            imageButtonPopup.setOnClickListener(v -> showPopup(v));
        }

        private void onBind(Title title) {
            this.title = title;
            textView.setText(title.name);
            textViewDate.setText(title.date);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(title, getAdapterPosition());
            }
        }
    }

    public void setOnClick(ItemClickListener mListener) {
        this.listener = mListener;
    }

    public interface ItemClickListener {
        void onItemClick(Title title, int pos);
    }


    //end of mainViewHolder

    private void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

