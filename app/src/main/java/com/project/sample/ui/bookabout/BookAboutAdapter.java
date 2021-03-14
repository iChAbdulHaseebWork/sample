package com.project.sample.ui.bookabout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.sample.R;
import com.project.sample.data.model.BookAbout;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAboutAdapter extends RecyclerView.Adapter<BookAboutAdapter.ItemViewHolder> {

    // FOR DATA
    private List<BookAbout> items;

    // CONSTRUCTOR
    public BookAboutAdapter() {
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_book_about, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
        viewHolder.updateWithItem(position,this.items.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.items!=null)
            return this.items.size();
        else
            return 0;
    }

    public BookAbout getItem(int position){
        return this.items.get(position);
    }

    public void updateData(List<BookAbout> items){
        this.items = items;
        this.notifyDataSetChanged();
    }

    // VIEW HOLDER
    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_heading) TextView tvHeading;
        @BindView(R.id.tv_detail) TextView tvDetail;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void updateWithItem(int position, BookAbout item){
            tvHeading.setText(item.getHeading());
            tvDetail.setText(item.getDetail());
        }

    }
}
