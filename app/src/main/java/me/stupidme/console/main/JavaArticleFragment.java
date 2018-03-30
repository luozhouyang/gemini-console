package me.stupidme.console.main;

import java.util.List;

/**
 * Created by allen on 18-3-30.
 */
public class JavaArticleFragment extends ArticleFragment {
    @Override
    ArticleAdapter getArticleAdapter(List<Article> articles) {
        return new ArticleAdapter(articles);
    }
}
