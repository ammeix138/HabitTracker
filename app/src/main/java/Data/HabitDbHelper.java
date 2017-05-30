package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static Data.HabitContract.*;

/**
 * Created by ammei on 5/18/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /**
     * Name of the database file within SQLite program.
     */
    private static final String DATABASE_NAME = "MyHabits.db";

    /**
     * Database version, if you change the
     * database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE" + HabitEntry.TABLE_NAME + "("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
                + HabitEntry.COLUMN_DATE + " TEXT"
                + HabitEntry.COLUMN_WO_TYPE + " INTEGER DEFAULT 0"
                + HabitEntry.COLUMN_CAL_BURNED + " INTEGER NOT NULL"
                + HabitEntry.COLUMN_STEP_COUNT + " INTEGER NOT NULL"
                + HabitEntry.COLUMN_DAILY_REFLECTION + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
