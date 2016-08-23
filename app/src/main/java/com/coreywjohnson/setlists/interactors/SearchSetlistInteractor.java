package com.coreywjohnson.setlists.interactors;

import android.util.Log;

import com.coreywjohnson.setlists.data.SetlistService;
import com.coreywjohnson.setlists.interfaces.PaginatableRequest;
import com.coreywjohnson.setlists.interfaces.PaginatableRequestListener;
import com.coreywjohnson.setlists.models.Setlists;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corey on 25-Apr-16.
 */
public class SearchSetlistInteractor implements PaginatableRequest<Setlists.Setlist> {
    private SetlistService mSetlistService;
    private Call<Setlists> mRequest;
    private String mName = null;
    private String mCity = null;
    private String mVenue = null;
    private String mDate = null;
    private String mYear = null;
    private String mTour = null;
    private PaginatableRequestListener<Setlists.Setlist> mListener;

    @Inject
    public SearchSetlistInteractor(SetlistService setlistService) {
        mSetlistService = setlistService;
    }

    public void setName(String query) {
        mName = query;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public void setVenue(String venue) {
        mVenue = venue;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public void setTour(String tour) {
        mTour = tour;
    }

    @Override
    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
            mRequest = null;
        }
    }

    @Override
    public void loadPage(int pageNo) {
        cancel();
        mRequest = mSetlistService.searchSetlists(mName, mCity, mVenue, mDate, mYear, mTour, pageNo);
        mRequest.enqueue(new Callback<Setlists>() {
            @Override
            public void onResponse(Call<Setlists> call, Response<Setlists> response) {
                if (response.isSuccessful()) {
                    mListener.onSuccess(response.body().getSetlist(), Integer.parseInt(response.body().getTotal()));
                } else {
                    Log.i("Error", response.message());
                    mListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Setlists> call, Throwable t) {
                if (!call.isCanceled()) {
                    Log.i("Search Request", "Failed");
                    Log.e("Failure", t.getMessage());
                    mListener.onError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void setListener(PaginatableRequestListener<Setlists.Setlist> listener) {
        mListener = listener;
    }
}
