package com.example.tomasevic.book_example_1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Message;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import android.os.Handler;
import android.os.Message;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.LogRecord;


public class MainActivity extends Activity
{
    ProgressBar pb;
    int checking = 0;

    protected Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            pb.incrementProgressBy(5);
        }
    };
    AtomicBoolean isRunning = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar)findViewById(R.id.progressBar);

        Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Toast.makeText(MainActivity.this, "Paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Toast.makeText(MainActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
        isRunning.set(false);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Toast.makeText(MainActivity.this, "Restarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Toast.makeText(MainActivity.this, "Resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();

        pb.setProgress(0);

        Thread background = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    for(int i=0; i<20 && isRunning.get(); i++)
                    {
                        Thread.sleep(1000);
                        handler.sendMessage(handler.obtainMessage());
                    }
                }
                catch (Throwable t)
                {
                    //t.printStackTrace();
                }
            }
        });

        isRunning.set(true);
        background.start();
    }
}
