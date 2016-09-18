package com.hendiware.advancedrecyclerview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(API.class).getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                final List<Post> postList = response.body();
                adapter = new PostsAdapter(postList, MainActivity.this);
                recyclerview.setAdapter(adapter);

                final Post post = new Post();
                post.setTitle("لحن الحياه");
                post.setBody("وقـالت لـي الأرضُ - لمـا سـألت:\tأيــا أمُّ هــل تكــرهين البشــرْ?\n" +
                        "أُبــارك فـي النـاس أهـلَ الطمـوح\tومــن يســتلذُّ ركــوبَ الخــطرْ\n" +
                        "وألْعــنُ مــن لا يماشــي الزمـانَ\tويقنـــع بــالعيْشِ عيشِ الحجَــرْ\n" +
                        "هــو الكــونُ حـيٌّ, يحـبُّ الحيـاة\tويحــتقر المَيْــتَ, مهمــا كــبُرْ\n" +
                        "فـلا الأفْـق يحـضن ميْـتَ الطيـورِ\tولا النحــلُ يلثــم ميْــتَ الزهـرْ\n" +
                        "ولــولا أمُومــةُ قلبِــي الــرّؤوم\tلَمَــا ضمّــتِ الميْـتَ تلـك الحُـفَرْ\n" +
                        "فــويلٌ لمــن لــم تشُــقه الحيـا\tة, مِــن لعنــة العــدم المنتصِـرْ!");

                new CountDownTimer(4000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        postList.set(0, post);
                        adapter.notifyItemChanged(0);
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }


    public interface API {
        @GET("posts")
        Call<List<Post>> getPosts();
    }
}
