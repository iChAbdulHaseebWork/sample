package com.project.sample.ui.book;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.sample.MainActivity;
import com.project.sample.R;
import com.project.sample.ui.bookmark.HadithBookmarkActivity;
import com.project.sample.ui.hadithcontent.HadithContentActivity;
import com.project.sample.ui.hadithnote.HadithAllNoteActivity;
import com.project.sample.ui.kitab.KitabActivity;
import com.project.sample.viewmodel.BookViewModel;
import com.project.sample.viewmodel.HadithContentViewModel;
import com.project.sample.viewmodel.HadithNoteViewModel;
import com.project.sample.viewmodel.KitabViewModel;
import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookFragment extends Fragment implements BookAdapter.Listener, AppBarLayout.OnOffsetChangedListener{

    private KitabViewModel kitabViewModel;
    private BookViewModel bookViewModel;
    private HadithContentViewModel hadithContentViewModel;
    private HadithNoteViewModel hadithNoteViewModel;

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.4f;
    private boolean mIsTheTitleVisible = true;

    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.header) LinearLayout header;
    @BindView(R.id.tv_book_count) TextView tvBookCount;
    @BindView(R.id.tv_bookmark_count) TextView tvBookmarkCount;
    @BindView(R.id.tv_note_count) TextView tvNoteCount;
    @BindView(R.id.collapsing_title) TextView collapsingTitle;
    @BindView(R.id.main_appbar)
    AppBarLayout main_appbar;

    private BookAdapter adapter;
    private static final String TAG = "SimpleGestureListener";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        configureViewModels();
        View root = inflater.inflate(R.layout.fragment_book, container, false);

        ButterKnife.bind(this,root);
        configureRecyclerView();
        ((com.project.sample.MainActivity)getActivity()).setToolbarTitle("Hadith Collection");
        bookViewModel.getAllBooks().observe(getActivity(), books -> {
            //update RecyclerView
            adapter.updateData(books);
            tvBookCount.setText(getResources().getQuantityString(R.plurals.book_count,books.size(),books.size()));
        });
        hadithContentViewModel.getBookmarkCount().observe(getActivity(), integer -> {
            tvBookmarkCount.setText(getResources().getQuantityString(R.plurals.item_count,integer,integer));
        });
        hadithNoteViewModel.getNoteCount().observe(getActivity(), integer -> {
            tvNoteCount.setText(getResources().getQuantityString(R.plurals.item_count,integer,integer));
        });
        main_appbar.addOnOffsetChangedListener(this);
        return root;
    }


    private void configureViewModels(){
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        kitabViewModel = new ViewModelProvider(this).get(KitabViewModel.class);
        hadithContentViewModel = new ViewModelProvider(this).get(HadithContentViewModel.class);
        hadithNoteViewModel = new ViewModelProvider(this).get(HadithNoteViewModel.class);
    }

    private void configureRecyclerView(){
        this.adapter = new BookAdapter(getActivity(),this);
        this.rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvData.setAdapter(adapter);
    }

    @OnClick(R.id.action_bookmark)
    public void onClickBookMark(){
        startActivity(new Intent(getActivity(), HadithBookmarkActivity.class));
    }

    @OnClick(R.id.action_notes)
    public void onClickNotes(){
        startActivity(new Intent(getActivity(), HadithAllNoteActivity.class));
    }

    @Override
    public void onClickItem(int bookId, String bookName) {
        kitabViewModel.getKitabsCountOfBook(bookId).observe(this, integer -> {
            if(integer>0) {
                startActivity(new Intent(getActivity(), KitabActivity.class).putExtra("bookId", bookId));
            }
            else {
                startActivity(new Intent(getActivity(), HadithContentActivity.class)
                        .putExtra("bookId", bookId)
                        .putExtra("bookName",bookName));
            }
        });

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if(!mIsTheTitleVisible) {
                ((com.project.sample.MainActivity)getActivity()).hideToolbarTitle(false);
                mIsTheTitleVisible = true;
            }
            // (1.0 / 0.55) = 1.81
            // (1.0 / 0.6) = 1.66
            // (1.0 / 0.65) = 1.54
            ((com.project.sample.MainActivity)getActivity()).setToolbarTitleAlpha((1.66f*percentage)-0.66f);
        } else {
            // (1.0 / 0.45) = 2.22
            // (1.0 / 0.4) = 2.5
            //(1.0 / 0.35) = 2.86
            collapsingTitle.setAlpha(1-(percentage*2.5f));
            tvBookCount.setAlpha(1-(percentage*(2.5f*2.f)));
            if (mIsTheTitleVisible) {
                ((com.project.sample.MainActivity)getActivity()).hideToolbarTitle(true);
                mIsTheTitleVisible = false;
            }
        }
    }
}