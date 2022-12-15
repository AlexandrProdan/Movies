package com.example.movies10;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
    MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int page = 1;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void loadMovies(){
        Disposable disposable = APIFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Throwable {
                        List<Movie> loadedMovies = movies.getValue();
                        if(loadedMovies!=null){
                            loadedMovies.addAll(movieResponse.getMovies());
                            movies.setValue(loadedMovies);
                        }else {
                            movies.setValue(movieResponse.getMovies());
                        }
                        page++;

                        Log.d(TAG, "accept: Movies Loaded successfully ");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, "accept: Didn't succeed to load the movies");
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
