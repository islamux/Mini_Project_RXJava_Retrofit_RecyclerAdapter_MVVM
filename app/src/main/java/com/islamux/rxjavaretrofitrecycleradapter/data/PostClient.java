package com.islamux.rxjavaretrofitrecycleradapter.data;

import com.islamux.rxjavaretrofitrecycleradapter.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
// we in the past did all of these in main .
// to prevent create a retrofit build every time , one time only .(clean code)
public class PostClient {
    // base url static(every where in app) final (it will not be changed)
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final PostInterface postInterface;
    private static PostClient INSTANCE;  // if exist use , or create it

    // Now we want when we create the PostClient --> make the builder of retrofit so we need to make the constructor of PostClient
    // so we create an empty constructor
    public PostClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // First step
                // convert from Call that return from retrofit to observable (RXJava)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // make the retrofit fill the postInterface body;
        postInterface = retrofit.create(PostInterface.class);
    }

    // Check INSTANCE if exist use it : Create it
    // by using getter for the INSTANCES
    public static PostClient getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new PostClient();
        }
        return INSTANCE;
    }

    // HERE:  we put our functions to use theme directly
    // make the retrofit fill the postInterface function body;
        public Single<List<PostModel>> getPosts(){
            return postInterface.getPosts();

        }

    // Step 6 to make code more clean (our data return one time so we should use Single , we don't need (next))
    // we make getPost return Single
//    public Single<List<PostModel>> getPosts(){
//        return postInterface.getPosts();
//
//    }

}
