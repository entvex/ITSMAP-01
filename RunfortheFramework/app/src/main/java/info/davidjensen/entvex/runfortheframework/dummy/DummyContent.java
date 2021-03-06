package info.davidjensen.entvex.runfortheframework.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ArnoldSchwarzeneggerMoveItem> ITEMS = new ArrayList<ArnoldSchwarzeneggerMoveItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ArnoldSchwarzeneggerMoveItem> ITEM_MAP = new HashMap<String, ArnoldSchwarzeneggerMoveItem>();

    private static final int COUNT = 25;



    static {
        // Add some sample items.
        addItem(new ArnoldSchwarzeneggerMoveItem("0","djilsadName","ds21321Detail"));
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ArnoldSchwarzeneggerMoveItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ArnoldSchwarzeneggerMoveItem createDummyItem(int position) {
        return new ArnoldSchwarzeneggerMoveItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ArnoldSchwarzeneggerMoveItem {
        public final String id;
        public final String content;
        public final String details;

        public ArnoldSchwarzeneggerMoveItem(String id, String movieName, String details) {
            this.id = id;
            this.content = movieName;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
