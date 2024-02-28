package com.islamux.rxjavaretrofitrecycleradapter.data;

import com.islamux.rxjavaretrofitrecycleradapter.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
    // Step 2 to change from call to RXJava
    // change call to observable (reactive rxjava)
    //public Observable<List<PostModel>> getPosts();

    // Step 6 (final)to make code more clean (our data return one time so we should use Single , we don't need (next))
    // we make getPost return Single
    Single<List<PostModel>> getPosts();
}
