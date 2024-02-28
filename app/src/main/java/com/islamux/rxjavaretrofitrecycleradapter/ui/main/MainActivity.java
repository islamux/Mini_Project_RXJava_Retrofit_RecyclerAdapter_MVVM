package com.islamux.rxjavaretrofitrecycleradapter.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.islamux.rxjavaretrofitrecycleradapter.pojo.PostModel;
import com.islamux.rxjavaretrofitrecycleradapter.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    PostViewModel postViewModel; // pushed from ViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // PostModel (to enable MVVM)
//          // old code not working
//          //postViewModel = new ViewModelProviders.of(this).get(PostViewModel.class);
            postViewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(PostViewModel.class);
            // final step : after postViewModel.postModuleMutableDat... // Observer
            postViewModel.getPosts();

            // Recycler (post_item) (1- item in xml 2-adapter  3- put recycler inside main(activity that w'll use))
            RecyclerView recyclerView = findViewById(R.id.recycler);

            // PostAdapter | make a new adapter
            final PostsAdapter adapter = new PostsAdapter();  // ????
            postViewModel.getPosts();

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

            // Now we need to get data from retrofit and when it return we make the data of adapter is the data that retrun from retrofit

            // Now last thing is Callback that we make it not in main but in viewModel
            // Now we need to observe on mutableLiveData and when data get inside it i put the data in adapter
            //Observer here is not the Observer of RXJava ***
            postViewModel.postMutableLiveData.observe(this, postModels -> adapter.setPostsList((ArrayList<PostModel>) postModels));
    }
}


// old code
//mWordViewModel = new ViewModelProvider.of(this).get(WordViewModel.class);
// new code
//mWordViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(WordViewModel.class);