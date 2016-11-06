package com.l.smartcityuniversalmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> objectsOfType;
    private Map<String, List<String>> types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        fillData();

    }

    private void fillData() {
        int counter = 0;
        List<String[]> tmp = new ArrayList<>();
        List<String> list = new ArrayList<>();
        objectsOfType = new ArrayList<>();
        types = new HashMap<>();
        String mass[] = getResources().getStringArray(R.array.types),
                food[] = getResources().getStringArray(R.array.products),
                clearness[] = getResources().getStringArray(R.array.cleaners),
                electronic[] = getResources().getStringArray(R.array.electronics),
                books[] = getResources().getStringArray(R.array.papers);

        tmp.add(food);
        tmp.add(clearness);
        tmp.add(electronic);
        tmp.add(books);

        for (String param : mass)
            objectsOfType.add(param);

        for (String[] mas : tmp) {
            for (String toAdd : mas)
                list.add(toAdd);
            types.put(objectsOfType.get(counter++), list);
            list.clear();
        }
    }
}
