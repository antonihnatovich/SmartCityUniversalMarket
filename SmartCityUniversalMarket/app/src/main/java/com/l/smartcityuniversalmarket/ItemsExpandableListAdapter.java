package com.l.smartcityuniversalmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ItemsExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> objectsOfType;
    Map<String, List<String>> types;

    public ItemsExpandableListAdapter(Context context, List<String> objectsOfType, Map<String, List<String>> types) {
        this.context = context;
        this.objectsOfType = objectsOfType;
        this.types = types;
    }

    @Override
    public int getGroupCount() {
        return objectsOfType.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return types.get(objectsOfType.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return objectsOfType.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return types.get(objectsOfType.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String type = (String) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_general, null);
        }

        TextView textGeneral = (TextView) view.findViewById(R.id.textview_general);
        textGeneral.setText(type);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String podType = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_child, null);
        }
        TextView textChild = (TextView) view.findViewById(R.id.textview_child);
        textChild.setText(podType);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
