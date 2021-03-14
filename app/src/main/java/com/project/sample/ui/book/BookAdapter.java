package com.project.sample.ui.book;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dylogicapps.muslimquranpro.R;
import com.dylogicapps.muslimquranpro.data.model.Book;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ItemViewHolder> {

    private final Context context;
    // CALLBACK
    public interface Listener { void onClickItem(int bookId, String bookName); }
    private final Listener callback;

    // FOR DATA
    private List<Book> items;

    // CONSTRUCTOR
    public BookAdapter(Context context,  Listener callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_book, parent, false);

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

    public Book getItem(int position){
        return this.items.get(position);
    }

    public void updateData(List<Book> items){
        this.items = items;
        this.notifyDataSetChanged();
    }

    // VIEW HOLDER
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_name) TextView tvName;
        @BindView(R.id.tv_desc) TextView tvDesc;
        @BindView(R.id.img) ImageView img;
        @BindView(R.id.content) RelativeLayout content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void updateWithItem(int position, Book item){
            tvName.setText(item.getEngName());
            tvDesc.setText("This book covers almost all aspects of life in providing proper guidance of Islam such as the method of performing prayers & other actions of worship directly from the Islamic prophet, Muhammad (SAW)");
            GradientDrawable sd = (GradientDrawable) img.getBackground().mutate();
            switch (position){
                case 0:
                    sd.setColor(0xff0c1200);
                    break;
                case 1:
                    sd.setColor(0xff455c3f);
                    break;
                case 2:
                    sd.setColor(0xfff6daa6);
                    break;
                case 3:
                    sd.setColor(0xff958b53);
                    break;
                case 4:
                    sd.setColor(0xff301922);
                    break;
                case 5:
                    sd.setColor(0xff293e7f);
                    break;
                case 6:
                    sd.setColor(0xffeaeaea);
                    break;
                case 7:
                    sd.setColor(0xfff0bd5f);
                    break;
                case 8:
                    sd.setColor(0xff8d5753);
                    break;
                case 9:
                    sd.setColor(0xff714d30);
                    break;
                case 10:
                    sd.setColor(0xff915e24);
                    break;
                case 11:
                    sd.setColor(0xff9d560d);
                    break;
                case 12:
                    sd.setColor(0xffbe8080);
                    break;
            }
            sd.invalidateSelf();
            Glide.with(context).load(item.getImagePath()).thumbnail(0.1f).into(img);

        }

        @Override
        public void onClick(View view) {
            if (callback != null) {
                callback.onClickItem(items.get(getAdapterPosition()).getId(), items.get(getAdapterPosition()).getEngName());
            }
        }
    }
}
