package com.example.darko.stravel;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SingleLinkParseDataIntentService extends IntentService {

    TableEventsNew oneElementOfTableEvents;


    private static final String ACTION_DOWNLOAD = "com.example.darko.stravel.action.ACTIONDOWNLOAD";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.darko.stravel.extra.PARAM1";


    public SingleLinkParseDataIntentService() {
        super("SingleLinkParseDataIntentService");
    }


    public static void startActionDownloadData(Context context, String param1) {
        Intent intent = new Intent(context, SingleLinkParseDataIntentService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(EXTRA_PARAM1, param1);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionDownloadData(param1);
            }
        }
    }

    private void handleActionDownloadData(final String urlToSpecificLink) {

        Elements title, metadata, description;
        oneElementOfTableEvents = new TableEventsNew();
        TableEventsNewSigletonArray dataProvider = TableEventsNewSigletonArray.getInstance();

        try {
            Document doc = Jsoup.connect("http://infozona.hr/" + urlToSpecificLink).get();
            //select data from HTML
            title = doc.select("h1");
            metadata = doc.select("b");
            description = doc.select("p");
            for (Element n : title) {
                oneElementOfTableEvents.setNameOfEvent(n.text().toString());
            }
            oneElementOfTableEvents.setDate(metadata.eq(0).text());
            oneElementOfTableEvents.setTime(metadata.eq(1).text());
            oneElementOfTableEvents.setCategory(metadata.eq(2).text());
            oneElementOfTableEvents.setDescription(description.text());
            dataProvider.setElementInEventsTable(oneElementOfTableEvents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
