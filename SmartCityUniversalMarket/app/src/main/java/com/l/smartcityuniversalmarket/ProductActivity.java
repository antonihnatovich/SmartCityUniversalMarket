package com.l.smartcityuniversalmarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {

    private ExpandableListView expandableListView;
    private List<String> objectsOfType;
    private Map<String, List<String>> types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        fillData();
        expandableListView.setAdapter(new ItemsExpandableListAdapter(this, objectsOfType, types));
        expandableListView.setOnChildClickListener(this);

    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        switch (i){
            case 0 :{
                switch (i1){
                    case 0 :{
                        Toast.makeText(this, "Hello from " +i+" lvl "+" prel" + i1, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1: {
                        Toast.makeText(this, "Hello from " +i+" lvl "+" prel" + i1, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 2: {
                        Toast.makeText(this, "Hello from " +i+" lvl "+" prel" + i1, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3:{
                        Toast.makeText(this, "Hello from " +i+" lvl "+" prel" + i1, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }
        return true;
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
                books[] = getResources().getStringArray(R.array.papers),
                other[] = getResources().getStringArray(R.array.other);

        tmp.add(food);
        tmp.add(clearness);
        tmp.add(electronic);
        tmp.add(books);
        tmp.add(other);

        for (String param : mass)
            objectsOfType.add(param);

        for (String[] mas : tmp) {
            for (String toAdd : mas)
                list.add(toAdd);
            types.put(objectsOfType.get(counter++), list);
            list = new ArrayList<>();
        }
    }
}
