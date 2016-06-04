package github.ankit.com.githubcommitlist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import github.ankit.com.githubcommitlist.model.GitHub;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;

public class CommitListActivity extends AppCompatActivity {

    private ListView list;
    public List<GitHub> gitHubList = new ArrayList<>();
    private ListAdapter listAdapter;
    ProgressDialog mProgressDialog;
    public static String url = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getGitCommits();
        list = (ListView) findViewById(R.id.list);
        listAdapter = new ListAdapter(CommitListActivity.this,gitHubList);
        list.setAdapter(listAdapter);
    }

    private void getGitCommits() {
        //making object of RestAdapter
        mProgressDialog = ProgressDialog.show(this,null,"please wait...");
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        //Creating Rest Services
        GetCommit getCommit = adapter.create(GetCommit.class);
        getCommit.getLatestCommits(new Callback<List<GitHub>>() {
            @Override
            public void success(List<GitHub> gitHubs, Response response) {
                mProgressDialog.dismiss();
                for(GitHub github : gitHubs){
                    gitHubList.add(github);
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mProgressDialog.dismiss();
            }
        });
    }

    public interface GetCommit{
        @GET("/repos/rails/rails/commits")
        void getLatestCommits(Callback<List<GitHub>> cb);
    }
}
