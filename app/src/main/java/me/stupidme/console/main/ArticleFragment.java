package me.stupidme.console.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import me.stupidme.console.R;


public abstract class ArticleFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;
    private List<Article> mArticleList = new LinkedList<>();

    abstract ArticleAdapter getArticleAdapter(List<Article> articles);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        mRecyclerView = view.findViewById(R.id.fragment_article_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        mArticleAdapter = getArticleAdapter(mArticleList);
        mRecyclerView.setAdapter(mArticleAdapter);
        for (int i = 0; i < 20; i++) {
            Article article = new Article("Android", "Hello World!", "Allen Luo", "201-03-31");
            mArticleList.add(article);
        }
        mArticleAdapter.notifyDataSetChanged();
        return view;
    }

}
