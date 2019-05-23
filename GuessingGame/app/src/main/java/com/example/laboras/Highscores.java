package com.example.laboras;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class Player {
    String id;
    String username;
    String score;

    public Player() {
    }

    public Player(String id, String username, String score) {
        this.id = id;
        this.username = username;
        this.score=score;
    }
    public String getId()
    {
        return  id;
    }
    public void setId()
    {
        this.id=id;
    }
    public String getUsername()
    {
        return  username;
    }
    public void setUsername()
    {
        this.username=username;
    }
    public String getScore()
    {
        return  score;
    }
    public void setScore()
    {
        this.score=score;
    }
}

class PlayerAdapter extends ArrayAdapter<Player> {

    public PlayerAdapter(Context context, List<Player> objects) {
        super(context, R.layout.inflater_input, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.inflater_input,null);
        }

        TextView id = (TextView) v.findViewById(R.id.idView);
        TextView username = (TextView) v.findViewById(R.id.usernameView);
        TextView score = (TextView) v.findViewById(R.id.scoreView);

        Player player = getItem(position);

        id.setText(player.getId());
        username.setText(player.getUsername());
        score.setText(player.getScore());

        return v;
    }

}
public class Highscores extends Activity {

    public PlayerAdapter adapter;
    public ListView mListView;
    DatabaseHelper db;
    List<Player> leaderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        leaderList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        db = new DatabaseHelper(this);
        mListView = (ListView)findViewById(R.id.leader_list) ;
        viewAll();
    }


    public void viewAll()
    {
        int counter=1;
        Cursor res = db.getData();

        while(res.moveToNext()&& counter<=50)
        {
            Player player = new Player(Integer.toString(counter),res.getString(1),res.getString(2));
            leaderList.add(player);
            counter++;
        }
        adapter = new PlayerAdapter(this, leaderList);
        mListView.setAdapter(adapter);
    }

    public void showData(String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(Message);
        builder.show();
    }

    public void deleteData()
    {
        db.deleteData();
    }
}
