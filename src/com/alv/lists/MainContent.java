 package com.alv.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alv.app.MyApplication;
import com.alv.app.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MainContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> MAIN_ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> MAIN_ITEM_MAP = new HashMap<String, DummyItem>();
    
    static {
        // Add 3 sample items.
    	
    	String informations = MyApplication.getContext().getResources().getString(R.string.informations);
    	String mandats = MyApplication.getContext().getResources().getString(R.string.mandats);
    	String photos = MyApplication.getContext().getResources().getString(R.string.photos);
    	String materiel = MyApplication.getContext().getResources().getString(R.string.materiel);
    	String selecteurdefleche = MyApplication.getContext().getResources().getString(R.string.title_activity_charte);
    	String distance = MyApplication.getContext().getResources().getString(R.string.distance);
    	String title_activity_tir = MyApplication.getContext().getResources().getString(R.string.title_activity_tir);
    	String graphic = MyApplication.getContext().getResources().getString(R.string.graphique);
    	String about = MyApplication.getContext().getResources().getString(R.string.about);

    	
        addItem(new DummyItem("1", informations,"http://arclatvedas.free.fr/index.php?option=com_content&view=article&id=194&tmpl=component"));
        addItem(new DummyItem("2", mandats,"http://arclatvedas.free.fr/index.php?option=com_content&view=article&id=228&tmpl=component"));
        addItem(new DummyItem("3", photos,"https://www.flickr.com/photos/arclatvedas/"));
        addItem(new DummyItem("4", materiel,""));
        addItem(new DummyItem("5", selecteurdefleche,""));
        addItem(new DummyItem("6", distance,""));
        addItem(new DummyItem("7", title_activity_tir,""));
        addItem(new DummyItem("8", graphic,""));
        addItem(new DummyItem("9", about,"http://arclatvedas.free.fr/index.php?option=com_content&view=article&id=20&tmpl=component"));
    }

    private static void addItem(DummyItem item) {
    	MAIN_ITEMS.add(item);
    	MAIN_ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public String strurl;

        public DummyItem(String id, String content,String strurl) {
            this.id = id;
            this.content = content;
            this.strurl = strurl;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
