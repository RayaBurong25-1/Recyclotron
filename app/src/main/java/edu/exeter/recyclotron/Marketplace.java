package edu.exeter.recyclotron;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Marketplace extends AppCompatActivity {
    ArrayList<HashMap<String,String>> Stores = new ArrayList<HashMap<String, String>>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace);
        list = (ListView) findViewById(R.id.listView);
        getData();
    }

    private void getData()
    {
//        String temp = Environment.getExternalStorageDirectory().toString();
//        temp = temp + "/Recyclotron";
//        File dir = new File(temp);
//        dir.mkdir();
//        FileInputStream stream;
//        DataInputStream dis;
//        temp = temp + "/list.txt";

        for (int i = 0; i < 10; i++)
        {
            HashMap<String, String> M = new HashMap<String, String>();
            M.put("icon", "name.jpg");
            M.put("name", "Generic Store Name" + i);
        }
        showList();
    }

    protected void showList()
    {
        try
        {
            ListAdapter adapter = new SimpleAdapter(
                    Marketplace.this,Stores,R.layout.marketlist,
                    new String[]{"icon","name"},
                    new int[]{R.id.marketimg,R.id.marketname}
            );
            list.setAdapter(adapter);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
