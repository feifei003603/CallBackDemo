
package com.sus.callbackdemo;

import com.sus.callbackdemo.MyAsyncTask.QueryResultListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends Activity {
    
    private static final String TAG = "ListActivity";

    private ListView listView;

    private MyAdapter adapter;

    private ArrayList<String> items = new ArrayList<String>();

    private MyAsyncTask myAsyncTask;
    
    private OtherRetainedFragment dataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.list_activity);
        listView = (ListView) findViewById(R.id.myListView);
        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);
        Log.e(TAG, "onCreate");

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        dataFragment = (OtherRetainedFragment) fm.findFragmentByTag("data");

        // create the fragment and data the first time
        if (dataFragment == null)
        {
            // add the fragment
            dataFragment = new OtherRetainedFragment();
            fm.beginTransaction().add(dataFragment, "data").commit();
        }
        myAsyncTask = dataFragment.getData();
        if (myAsyncTask != null)
        {
            myAsyncTask.setActivity(this);
        } else
        {
            myAsyncTask = new MyAsyncTask(this);
            dataFragment.setData(myAsyncTask);
            myAsyncTask.execute();
        }
        
        myAsyncTask.setQueryResultListener(new QueryResultListener() {

            @Override
            public void onQueryResultSuccess(ArrayList<String> result) {
                adapter.setItems(result);
            }

            @Override
            public void onQueryResultFailed() {

            }
        });
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle state)
    {
        super.onRestoreInstanceState(state);
        Log.e(TAG, "onRestoreInstanceState");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        myAsyncTask.setActivity(null);
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();

    }

    public void onTaskCompleted() {
        items = myAsyncTask.getItems();
        adapter.setItems(items);
    }

}
