package com.example.movies10;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class MovieDetailViewModel extends AndroidViewModel {
    private static final String TAG = "MovieDetailedViewModel";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final MovieDAO movieDAO;



    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDAO = MovieDatabase.getInstance(application).movieDAO();
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public LiveData<Movie> getFavoriteMovie(int movieId){
        return movieDAO.getFavoriteMovie(movieId);
    }

    public void insertMovie(Movie movie){
        Disposable disposable3 = movieDAO.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable3);
    }
    public void removeMovie(int movieId){
        Disposable disposable4 = movieDAO.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable4);
    }


    public void loadTrailer(int id){
        Disposable disposable = APIFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        return trailerResponse.getTrailers().getTrailersList();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) throws Throwable {
                        trailers.setValue(trailerList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, "Error loading trailers");
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadReview(int id){
        Disposable disposable2 =  APIFactory.apiService.loadReview(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReviewResponse>() {
                    @Override
                    public void accept(ReviewResponse reviewResponse) throws Throwable {
                        reviews.setValue(reviewResponse.getReviewList());
                        Log.d(TAG, reviewResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, "Didn't succeed to lad reviews");
                    }
                });

        compositeDisposable.add(disposable2);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
