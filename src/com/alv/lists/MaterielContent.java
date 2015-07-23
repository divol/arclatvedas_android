package com.alv.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alv.app.MyApplication;
import com.alv.app.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MaterielContent {

    /**
     * An array of sample (dummy) items.
     */
	
	public static Vector<String> MATERIEL_TYPES = new Vector<String>();
	 
	 
    public static List<DummyItem> MATERIEL_ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> MATERIEL_ITEM_MAP = new HashMap<String, DummyItem>();

    static {
    	String materiel = MyApplication.getContext().getResources().getString(R.string.materiel);

    	String arrows = MyApplication.getContext().getResources().getString(R.string.arrows);

        addItem(new DummyItem("1", materiel,""));
        addItem(new DummyItem("2", arrows,""));
    }

    private static void addItem(DummyItem item) {
    	MATERIEL_ITEMS.add(item);
    	MATERIEL_ITEM_MAP.put(item.id, item);
    	MATERIEL_TYPES.add(item.content);
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
