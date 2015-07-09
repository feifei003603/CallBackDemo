
package com.sus.callbackdemo;


import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

public class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<String>> {

    private QueryResultListener queryResultListener;

    private ArrayList<String> items;

    private ListActivity activity;

    private LoadingDialog mLoadingDialog;

    private boolean isCompleted;

    public MyAsyncTask(ListActivity activity) {
        this.activity = activity;
    }

    public void setQueryResultListener(QueryResultListener queryResultListener) {
        this.queryResultListener = queryResultListener;
    }

    @Override
    protected void onPreExecute() {
        mLoadingDialog = new LoadingDialog();
        mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
        super.onPreExecute();
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        items = loadData();
        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        isCompleted = true;

        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        if (activity != null) {
            if (queryResultListener != null) {
                if (result == null) {
                    queryResultListener.onQueryResultFailed();
                } else {
                    queryResultListener.onQueryResultSuccess(result);
                }
            }
        }
        super.onPostExecute(result);
    }
    
    public ArrayList<String> getItems(){
        return items;
    }

    private ArrayList<String> loadData() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return new ArrayList<String>(Arrays.asList("接", "口", "回", "调", "详", "解"));
    }
    
    /**
     * 设置Activity，因为Activity会一直变化
     * 
     * @param activity
     */
    public void setActivity(ListActivity activity)
    {
        // 如果上一个Activity销毁，将与上一个Activity绑定的DialogFragment销毁
        if (activity == null)
        {
            mLoadingDialog.dismiss();
        }
        // 设置为当前的Activity
        this.activity = activity;
        // 开启一个与当前Activity绑定的等待框
        if (activity != null && !isCompleted)
        {
            mLoadingDialog = new LoadingDialog();
            mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
        }
        // 如果完成，通知Activity
        if (isCompleted)
        {
            notifyActivityTaskCompleted();
        }
    }
    
    private void notifyActivityTaskCompleted()
    {
        if (null != activity)
        {
            activity.onTaskCompleted();
        }
    }

    public interface QueryResultListener {
        public void onQueryResultFailed();

        public void onQueryResultSuccess(ArrayList<String> result);
    }

}
