package com.example.ammei.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import Data.HabitDbHelper;

import static Data.HabitContract.HabitEntry;

public class CatalogActivity extends AppCompatActivity {

    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    TextView mEmptyViewDisplayText;

    /**
     * Database helper which will provide access to the "MyHabits" database.
     */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // EmptyView will display to the user upon initial start-up, saying there is no UI.
        mEmptyViewDisplayText = (TextView) findViewById(R.id.emptyView);
        mEmptyViewDisplayText.setText(R.string.no_ui);

        mDbHelper = new HabitDbHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     *
     */
    private Cursor readHabits() {
        // Create and or open a database to read from it.
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_DATE,
                HabitEntry.COLUMN_WO_TYPE,
                HabitEntry.COLUMN_CAL_BURNED,
                HabitEntry.COLUMN_STEP_COUNT,
                HabitEntry.COLUMN_DAILY_REFLECTION
        };

        // Performs query on the habits table.
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.emptyView);

        try {

            // Displays number of rows in the Cursor
            displayView.setText("Number of rows currently in table: " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_DATE +
                    HabitEntry.COLUMN_WO_TYPE +
                    HabitEntry.COLUMN_CAL_BURNED +
                    HabitEntry.COLUMN_STEP_COUNT +
                    HabitEntry.COLUMN_DAILY_REFLECTION);

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);
            int workoutColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_WO_TYPE);
            int calColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_CAL_BURNED);
            int stepColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_STEP_COUNT);
            int reflColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DAILY_REFLECTION);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                int currentWorkout = cursor.getInt(workoutColumnIndex);
                int currentCalBurned = cursor.getInt(calColumnIndex);
                int currentSteps = cursor.getInt(stepColumnIndex);
                String currentReflection = cursor.getString(reflColumnIndex);
                // Display the values from each column of the current
                // row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentDate + " - " + "\n" +
                        currentWorkout + " - " +
                        currentCalBurned + " - " +
                        currentSteps + " - " +
                        currentReflection));

            }
        } finally {
            // Get Cursor
            return cursor;
        }
    }

    /**
     * Temporary helper method displays info within the onscreen TextView regarding current state
     * of the database.
     */
    public void displayDatabaseInfo() {
        Cursor cursor = readHabits();
        cursor.close();
    }

    private void insertHabit() {
        // Gets the database in write mode.
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and the users catalogued habits are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_DATE, "May 17, 2017");
        values.put(HabitEntry.COLUMN_WO_TYPE, HabitEntry.WT_UNKNOWN);
        values.put(HabitEntry.COLUMN_CAL_BURNED, 456);
        values.put(HabitEntry.COLUMN_STEP_COUNT, 23877);
        values.put(HabitEntry.COLUMN_DAILY_REFLECTION, "This is my daily reflection...");

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        Log.v(LOG_TAG, "New Rod Id: " + newRowId);
    }
}
