package com.example.socialos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public  class AppListActivity extends Activity {
    private PackageManager manager;
    private List<AppInfo> apps;
    private ListView list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);
        loadApps();
        loadListView();
        addClickListener();
    }

    private void addClickListener() {list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = manager.getLaunchIntentForPackage(apps.get(position).packageName.toString());
            AppListActivity.this.startActivity(intent);
        }
    });
    }

    private void loadListView() {
    list =  findViewById(R.id.app_list);
    ArrayAdapter<AppInfo> adapter = new ArrayAdapter<AppInfo>(this, R.layout.activity_app_layout, apps) {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderItem viewHolderItem = null;

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.activity_app_layout,parent,false);
                viewHolderItem = new ViewHolderItem();
                viewHolderItem.icon  =  convertView.findViewById(R.id.icon);
                viewHolderItem.label =  convertView.findViewById(R.id.label);
                viewHolderItem.name  =  convertView.findViewById(R.id.name);

                convertView.setTag(viewHolderItem);

            }else{
                viewHolderItem = (ViewHolderItem) convertView.getTag();
            }
            AppInfo appInfo = apps.get(position);
            if (appInfo != null){
                viewHolderItem.icon.setImageDrawable(appInfo.icon);
                viewHolderItem.label.setText(appInfo.label);
                viewHolderItem.name.setText(appInfo.packageName);

            }
            return convertView;
        }


    }; list.setAdapter(adapter);
}

    private void loadApps() {
        manager = getPackageManager();
        apps = new ArrayList<AppInfo>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(intent,0);
        for (ResolveInfo r1 : availableActivities){
            AppInfo appInfo = new AppInfo();
            appInfo.label = r1.loadLabel(manager);
            appInfo.packageName = r1.activityInfo.packageName;
            appInfo.icon = r1.activityInfo.loadIcon(manager);

            apps.add(appInfo);
        }

    }
    final class ViewHolderItem {
        ImageView icon;
        TextView label;
        TextView name;


    }
}