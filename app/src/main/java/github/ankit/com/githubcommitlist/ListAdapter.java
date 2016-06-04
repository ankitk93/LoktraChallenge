package github.ankit.com.githubcommitlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import github.ankit.com.githubcommitlist.model.GitHub;

/**
 * Created by techie93 on 6/3/2016.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<GitHub> gitHubList;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context, List<GitHub> gitHubList ) {
        this.context = context;
        this.gitHubList = gitHubList;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gitHubList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder h;
        if(view==null){
            view = layoutInflater.inflate(R.layout.list_item,parent,false);
            h = new ViewHolder(view);
            view.setTag(h);
        }else {
            h = (ViewHolder) view.getTag();
        }
        GitHub github = gitHubList.get(position);
        h.tvPersonName.setText(github.getCommit().getAuthor().getName());
        h.tvCommitMessage.setText(github.getCommit().getMessage());

        return view;
    }

    private class ViewHolder {
        public TextView tvPersonName,tvCommitMessage;
        public ViewHolder(View view) {
            tvPersonName = (TextView) view.findViewById(R.id.tv_person_name);
            tvCommitMessage = (TextView) view.findViewById(R.id.tv_commit_message);
        }
    }
}
