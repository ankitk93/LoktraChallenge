package github.ankit.com.githubcommitlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import github.ankit.com.githubcommitlist.model.GitHub;

public class DetailActivity extends AppCompatActivity {

    public static final String INTENT_DETAIL = "intent_detsil_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv = (TextView) findViewById(R.id.tv_text);

        GitHub gitHub = (GitHub) getIntent().getSerializableExtra(INTENT_DETAIL);
        tv.setText(gitHub.getCommentsUrl());
    }
}
