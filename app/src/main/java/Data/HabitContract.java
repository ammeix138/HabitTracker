package Data;

import android.provider.BaseColumns;

/**
 * Created by ammei on 1/10/2017.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        /**
         * This shows the actual table name "habits"
         */
        public final static String TABLE_NAME = "habits";

        /**
         * This displays the different names of the columns within the data table "habits".
         */
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_WO_TYPE = "workout";
        public final static String COLUMN_CAL_BURNED = "calories_burned";
        public final static String COLUMN_STEP_COUNT = "step_count";
        public final static String COLUMN_DAILY_REFLECTION = "reflection";

        /**
         * Type of workout/exercise to be selected by the user
         * that would be displayed with a spinner menu.
         */
        public static final int WT_UNKNOWN = 0;
        public static final int WT_CARDIOVASCULAR = 1;
        public static final int WT_STRENGTH = 2;
        public static final int WT_AEROBIC = 3;
        public static final int WT_COMBO = 4;

    }
}
