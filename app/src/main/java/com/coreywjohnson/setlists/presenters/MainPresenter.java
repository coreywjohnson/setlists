package com.coreywjohnson.setlists.presenters;

import com.coreywjohnson.setlists.interactors.SearchArtistInteractor;
import com.coreywjohnson.setlists.models.SearchResponse;
import com.coreywjohnson.setlists.views.MainView;

/**
 * Created by corey on 24-Apr-16.
 */
public class MainPresenter extends BasePresenter implements SearchArtistInteractor.SearchArtistCallback {
    private MainView mainView;
    private SearchArtistInteractor mInteractor;

    public MainPresenter(MainView view) {
        mainView = view;
    }

    public void onSearch(String query) {
        mInteractor = new SearchArtistInteractor(this);
        mInteractor.execute(query);
    }

    @Override
    public void onSuccess(SearchResponse searchResponse) {
        mainView.addItems(searchResponse.getSetlists().getSetlist());
    }

    @Override
    public void onError(String error) {

    }
}
