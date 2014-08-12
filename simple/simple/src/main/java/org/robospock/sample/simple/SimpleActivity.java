package org.robospock.sample.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.SortedSet;
import java.util.TreeSet;


public class SimpleActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    String[] countriesArray;

    String[] currentArray;

    public EditText searchEt;
    public View searchBtn;
    public ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_acitivity);

        listView = (ListView) findViewById(R.id.list_results);

        countriesArray = getResources().getStringArray(R.array.countries_array);

        currentArray = countriesArray;

        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countriesArray);

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);

        searchEt = (EditText) findViewById(R.id.search_et);

        searchBtn = findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, currentArray[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String find = searchEt.getText().toString().toLowerCase();

        SortedSet<String> strings = searchStrings(find, countriesArray);

        currentArray = new String[strings.size()];
        strings.toArray(currentArray);

        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currentArray);
        listView.setAdapter(simpleAdapter);
    }

    public static SortedSet<String> searchStrings(String find, String[] array) {
        SortedSet<String> strings = new TreeSet<String>();

        for (String value : array) {
            if (value.toLowerCase().contains(find)) {
                strings.add(value);
            }
        }
        return strings;
    }
}
