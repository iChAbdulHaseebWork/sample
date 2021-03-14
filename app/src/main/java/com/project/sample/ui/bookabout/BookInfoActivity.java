package com.project.sample.ui.bookabout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.sample.R;
import com.project.sample.viewmodel.BookAboutViewModel;
import com.project.sample.viewmodel.BookViewModel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookInfoActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;
    private BookAboutViewModel bookAboutViewModel;

    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.tv_eng_name) TextView tvEngName;
    @BindView(R.id.tv_arabic_name) TextView tvArabicName;
    @BindView(R.id.img) ImageView img;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private BookAboutAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureViewModels();
        setContentView(R.layout.activity_book_info);
        ButterKnife.bind(this);
        configureToolbar();
        configureRecyclerView();
        bookAboutViewModel.getBookAbout(getIntent().getIntExtra("bookId",0))
                .observe(this, bookAbouts -> adapter.updateData(bookAbouts));
        bookViewModel.getBook(getIntent().getIntExtra("bookId",0)).observe(this, bookInfo -> {
            tvEngName.setText(bookInfo.getEngName());
            tvArabicName.setText(bookInfo.getArabicName());
            Glide.with(this).load(bookInfo.getImagePath()).thumbnail(0.1f).into(img);
        });
    }

    private void configureViewModels(){
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookAboutViewModel = new ViewModelProvider(this).get(BookAboutViewModel.class);
    }

    private void configureRecyclerView(){
        this.adapter = new BookAboutAdapter();
        this.rvData.setLayoutManager(new LinearLayoutManager(this));
        this.rvData.setAdapter(adapter);
    }

    private void configureToolbar(){
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        title.setText(R.string.toolbar_overview);
    }

    @OnClick(R.id.action_back)
    public void OnClickBack(){
        finish();
    }
}