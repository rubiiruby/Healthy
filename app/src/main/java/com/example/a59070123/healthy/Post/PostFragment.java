package com.example.a59070123.healthy.Post;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a59070123.healthy.Comment.CommentFragment;
import com.example.a59070123.healthy.MenuFragment;
import com.example.a59070123.healthy.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PostFragment extends Fragment {

    ArrayList<Post> posts = new ArrayList<>();
    ListView postList;
    PostAdapter postAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_post, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postList = getActivity().findViewById(R.id.post_list);
        getJsonData();
        btnBack();
    }

    void getJsonData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts")
                    .build();
            Response responses = client.newCall(request).execute();

            JSONArray jArray = new JSONArray(responses.body().string());
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.getJSONObject(i);
                Post post = new Post();
                post.setUserId(String.valueOf(object.getInt("userId")));
                post.setId(String.valueOf(object.getInt("id")));
                post.setTitle(object.getString("title"));
                post.setBody(object.getString("body"));
                posts.add(post);
            }

            setAdapter();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    void setAdapter() {
        postAdapter = new PostAdapter(getActivity(), R.layout.fragment_post_item, posts);
        postList.setAdapter(postAdapter);

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommentFragment commentFragment = new CommentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("postId", posts.get(position).getId());
                Log.wtf("position", "position" + position);
                commentFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, commentFragment).addToBackStack(null).commit();
            }
        });
    }

    void btnBack(){
        /////////////BTN Back
        TextView btn_comment_back = (TextView) getView().findViewById(R.id.post_btn_back);
        btn_comment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sleep", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
