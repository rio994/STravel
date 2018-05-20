package com.example.darko.stravel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;

public class DownloadDataForEventsService extends Service {

    LinksDataSingleton linksDataSingleton;
    //buffer for Table EventLinks retrieved from LinksDataSingleton
    ArrayList<TableEventLinks> eventLinks;

    @Override
    public void onCreate() {
        linksDataSingleton = LinksDataSingleton.getInstance();
        eventLinks = linksDataSingleton.getTableEventLinks();
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //creating queue for IntentService
      for(int i =0; i<eventLinks.size();i++)
        SingleLinkParseDataIntentService.startActionDownloadData(this,eventLinks.get(i).getEventLink());
      stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public DownloadDataForEventsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }



}
