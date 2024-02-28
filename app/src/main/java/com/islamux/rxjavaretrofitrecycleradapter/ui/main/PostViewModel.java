package com.islamux.rxjavaretrofitrecycleradapter.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.islamux.rxjavaretrofitrecycleradapter.data.PostClient;
import com.islamux.rxjavaretrofitrecycleradapter.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/* Data <- ViewMode ->  Main(view)
 Purpose of PostVIewModel 2 things
     1- data from model
      instance of data | we need a inside these viewModel inside it the list of posts that return from retrofit
     2-  main (view)
     push it to MainActivity--> PostViewModel postViewModel using Callback*/
/* Main Purpose of these classe are:
* 1- obserable
* 2- observer
* 3- connect them by operators ()*/
public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";


    MutableLiveData<List<PostModel>> postMutableLiveData= new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();
    // we need now func to get posts from retrofit
    // Call back
    public void getPosts() {
        // Step 3 --> to convert from Call to rxjava
        // remove ".enqueue(new Callback<>...) and make the return Observable (reactivex)" and save it to variable observable
        // before to change to Observable<List<PostModel>> observable = PostClient.getINSTANCE().getPosts()
        //Observable observable = PostClient.getINSTANCE().getPosts();

        // to change from main thread that make error remove ";" after getPosts() then make Scheduler
//        Observable<List<PostModel>> observable = PostClient.getINSTANCE().getPosts()
//                .observeOn(AndroidSchedulers.mainThread());

        // Step 6 (final)to make code more clean replace observable with Single (our data return one time so we should use Single , we don't need (next))
        // we make getPost return Single
        Single<List<PostModel>> observable = PostClient.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

       // Step 4
        // Make observer (reactivex)
        // Now retrofit when it return any data assign it to variable of mutableLiveData

        // way 2 to make an (observer) that return List directly without make casting to List
//        Observer<List<PostModel>> observer = new Observer<List<PostModel>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(List<PostModel> postModels) {
//                postMutableLiveData.setValue(postModels);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("TAG","getPost: " + e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        // way 2 to make (observer) with casting data -> postMutableLiveData.setValue((List<PostModel>) value); // -> cast value to list OR Change the return of Observer to Observer<List>
//        Observer observer = new Observer() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object value) {
//                postMutableLiveData.setValue((List<PostModel>) value); // -> cast value to list OR Change the return of Observer to Observer<List>
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        // Step 5 ( without shortcuts) to connect Observer with Observable by (Operators)
        //observable.subscribe(observer);
        // Step 5 :(connect observer with observable) ( shorthand: instead using observer using lambda func inside observable.subscribe() -> casting to list () AND -> Observable<List<PostModel>> observable = PostClient.getINSTANCE().getPosts()
        //observable.subscribe(o->postMutableLiveData.setValue(o));
        // Step 5 (connect observer with observable): ( shorthand: instead using observer using lambda func inside observable.subscribe() without casting
        observable.subscribe(o-> postMutableLiveData.setValue(o),e-> Log.d("TAG","getPosts: "+e));

        // Step 6 to make code more clean (our data return one time so we should use Single , we dont need (next))
        // So we make getPost
        // Way 1 , Way 2  (remove way 1 and way 2 )
        // way 3
        // to disable casting make Observable<List<PostModel>>

    }
}