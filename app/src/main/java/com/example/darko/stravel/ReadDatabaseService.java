package com.example.darko.stravel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ReadDatabaseService extends Service {
    private DatabaseAccess databaseAccess;
    private DatabaseSingleton databaseSingleton;

    public ReadDatabaseService() {
    }

    @Override
    public void onCreate() {
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseSingleton = DatabaseSingleton.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        databaseAccess.open();
        databaseSingleton.setTableAtmToilets(databaseAccess.getATMsAndToilets());
        databaseSingleton.setTableBarShoppings(databaseAccess.getBarsAndShops());
        databaseSingleton.setTableRestaurants(databaseAccess.getRestaurants());
        databaseSingleton.setTablePHPs(databaseAccess.getPHP());
        databaseSingleton.setTableBeaches(databaseAccess.getBeaches());
        databaseSingleton.setTableTransport(databaseAccess.getTransport());
        databaseAccess.close();
        stopSelf();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
