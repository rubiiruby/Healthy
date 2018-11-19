package com.example.a59070123.healthy.Comment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import com.example.a59070123.healthy.Post.PostFragment;
import com.example.a59070123.healthy.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;

public class CommentFragment extends Fragment {

    ArrayList<Comment> comments = new ArrayList<>();
    ListView commentList;
    String postId;
    CommentAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_comment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postId = getArguments().getString("postId");
        commentList = getActivity().findViewById(R.id.comment_list);
        getJsonData();
        btnBack();
    }

    void getJsonData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            OkHttpClient client = new OkHttpClient();
            String url = "https://jsonplaceholder.typicode.com/posts/" + postId + "/comments";
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response responses = client.newCall(request).execute();

            JSONArray jArray = new JSONArray(responses.body().string());
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.getJSONObject(i);
                Comment comment = new Comment();
                comment.setPostId(Integer.toString(object.getInt("postId")));
                comment.setId(Integer.toString(object.getInt("id")));
                comment.setBody(object.getString("body"));
                comment.setName(object.getString("name"));
                comment.setEmail(object.getString("email"));

                comments.add(comment);
            }
            setAdapter();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    void setAdapter() {
        adapter = new CommentAdapter(getActivity(), R.layout.fragment_comment_item, comments);
        commentList.setAdapter(adapter);
    }

    void btnBack(){
        /////////////BTN Back
        TextView btn_comment_back = (TextView) getView().findViewById(R.id.comment_btn_back);
        btn_comment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sleep", "Click ADD BACK");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new PostFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
