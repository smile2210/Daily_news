package com.example.dailynews.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dailynews.Adapter.MainListAdapter;
import com.example.dailynews.ApiClient;
import com.example.dailynews.ApiInterface;
import com.example.dailynews.MainActivity;
import com.example.dailynews.Model.Article;
import com.example.dailynews.Model.MainData;
import com.example.dailynews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dailynews.MainActivity.nointernet;

public class MainFragment extends Fragment {

    RecyclerView list;
    public static List<Article> articleList = new ArrayList<Article>();
    SwipeRefreshLayout swipe;
    MainListAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        list = view.findViewById(R.id.list);
        swipe = view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                articleList.clear();
                getData();
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        });
        getData();

        return view;
    }
    private void getData() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        final Call<MainData> mainDataCall = apiInterface.getBusinessData("in","general","3b0c68bd2e3d49888add81efb8d0fd99");
        mainDataCall.enqueue(new Callback<MainData>() {
            @Override
            public void onResponse(Call<MainData> call, Response<MainData> response) {
                if (response.isSuccessful()) {
                    MainData data =  response.body();
                    for (int i = 0; i < data.getArticles().size(); i++) {
                        Article article = new Article();
                        article.setTitle(data.getArticles().get(i).getTitle());
                        article.setUrlToImage(data.getArticles().get(i).getUrlToImage());
                        article.setPublishedAt(data.getArticles().get(i).getPublishedAt());
                        article.setDescription(data.getArticles().get(i).getDescription());
                        article.setAuthor(data.getArticles().get(i).getAuthor());
                        article.setContent(data.getArticles().get(i).getContent());
                        article.setUrl(data.getArticles().get(i).getUrl());
                        articleList.add(article);
                    }
                }
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                adapter = new MainListAdapter(getActivity(),articleList);
                list.setAdapter(adapter);
                list.setLayoutManager(manager);

            }

            @Override
            public void onFailure(Call<MainData> call, Throwable t) {
                nointernet.setVisibility(View.VISIBLE);
            }
        });
    }
}