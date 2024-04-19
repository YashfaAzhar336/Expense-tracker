package com.shahab12344.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String TABLEFORINCOME = "incomeside";
    private static final String TABLEOFEXPENSE = "Expense";
    private static final String TABLEOFDEBIT = "Debit";
    private static final String TABLEOFPEND = "PENDING";
    private static final String TABLEOFWISHLIST = "WISHLIST";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";



//    income
    public static final String COLUMN_INCOME_TYPE = "income_type";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_INCOME_AMOUNT = "income_amount";
    public static final String COLUMN_USERNAME_INCOME = "username";


    //expense column
    private static final String COLUMN_EXPENSE_TYPE = "expense_type";
    private static final String COLUMN_ex_DATE = "exdate";
    private static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";
    private static final String COLUMN_EXPENSE_USER = "expense_user";


    //debit column
    private static final String COLUMN_DEBIT_TYPE = "DEBIT_type";
    private static final String COLUMN_DEBIT_DATE = "DEBITdate";
    private static final String COLUMN_DEBIT_AMOUNT = "Damount";
    private static final String COLUMN_DEBIT_USER = "DEBIT_user";

    //pending column
    private static final String COLUMN_PEND_TYPE = "PEND_type";
    private static final String COLUMN_PEND_DATE = "PENDTdate";
    private static final String COLUMN_PEND_AMOUNT = "PEND_amount";
    private static final String COLUMN_PEND_USER = "PEND_user";

    //WISH column
    private static final String COLUMN_WISH_TYPE = "WISH_type";
    private static final String COLUMN_WISH_DATE = "WISHdate";
    private static final String COLUMN_WISH_AMOUNT = "WISH_amount";
    private static final String COLUMN_WISH_USER = "WISH_user";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);

    // income
        String createIncomeTableQuery = "CREATE TABLE " + TABLEFORINCOME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INCOME_TYPE + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_INCOME_AMOUNT + " REAL, " +
                COLUMN_USERNAME_INCOME + " TEXT)";
        db.execSQL(createIncomeTableQuery);


     //expense
        String createexpense = "CREATE TABLE " + TABLEOFEXPENSE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPENSE_TYPE + " TEXT, " +
                COLUMN_ex_DATE + " TEXT, " +
                COLUMN_EXPENSE_AMOUNT + " REAL, " +
                COLUMN_EXPENSE_USER + " TEXT)";
        db.execSQL(createexpense);

        //expense
        String createdebit= "CREATE TABLE " + TABLEOFDEBIT + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DEBIT_TYPE + " TEXT, " +
                COLUMN_DEBIT_DATE + " TEXT, " +
                COLUMN_DEBIT_AMOUNT + " REAL, " +
                COLUMN_DEBIT_USER + " TEXT)";
        db.execSQL(createdebit);

        //pending
        String createpend= "CREATE TABLE " + TABLEOFPEND + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PEND_TYPE + " TEXT, " +
                COLUMN_PEND_DATE + " TEXT, " +
                COLUMN_PEND_AMOUNT + " REAL, " +
                COLUMN_PEND_USER + " TEXT)";
        db.execSQL(createpend);

        //WISH
        String createpen= "CREATE TABLE " + TABLEOFWISHLIST + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WISH_TYPE + " TEXT, " +
                COLUMN_WISH_DATE + " TEXT, " +
                COLUMN_WISH_AMOUNT + " REAL, " +
                COLUMN_WISH_USER + " TEXT)";
        db.execSQL(createpen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLEFORINCOME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLEOFEXPENSE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLEOFDEBIT);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLEOFPEND);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLEOFWISHLIST);
        onCreate(db);
    }

    public boolean addUser(String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }


////    income table
public boolean addIncome(String incomeType, String date, double incomeAmount, String username) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_INCOME_TYPE, incomeType);
    values.put(COLUMN_DATE, date);
    values.put(COLUMN_INCOME_AMOUNT, incomeAmount);
    values.put(COLUMN_USERNAME_INCOME, username);
    long result = db.insert(TABLEFORINCOME, null, values);
    return result != -1;
}

    public double calculateTotalIncome(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(" + COLUMN_INCOME_AMOUNT + ") FROM " + TABLEFORINCOME +
                " WHERE " + COLUMN_USERNAME_INCOME + "=?";
        String[] selectionArgs = { user };
        Cursor cursor = db.rawQuery(query, selectionArgs);
        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        return total;
    }

    ////    expense table
    public boolean addExpense(String expenseType, String date, double expenseAmount, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSE_TYPE, expenseType);
        values.put(COLUMN_ex_DATE, date);
        values.put(COLUMN_EXPENSE_AMOUNT, expenseAmount);
        values.put(COLUMN_EXPENSE_USER, username);
        long result = db.insert(TABLEOFEXPENSE, null, values);
        return result != -1;
    }

    ////    debit table
    public boolean addDebit(String DebitType, String date, double DebitAmount, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEBIT_TYPE, DebitType);
        values.put(COLUMN_DEBIT_DATE, date);
        values.put(COLUMN_DEBIT_AMOUNT, DebitAmount);
        values.put(COLUMN_DEBIT_USER, username);
        long result = db.insert(TABLEOFDEBIT, null, values);
        return result != -1;
    }

    ////    pending table
    public boolean addpending(String DebitType, String date, double DebitAmount, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PEND_TYPE, DebitType);
        values.put(COLUMN_PEND_DATE, date);
        values.put(COLUMN_PEND_AMOUNT, DebitAmount);
        values.put(COLUMN_PEND_USER, username);
        long result = db.insert(TABLEOFPEND, null, values);
        return result != -1;
    }


    public double calculateTotaldebit(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(" + COLUMN_DEBIT_AMOUNT + ") FROM " + TABLEOFDEBIT +
                " WHERE " + COLUMN_DEBIT_USER + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{user});
        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        return total;
    }

    public double calculateTotalexpense(String usernam) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(" + COLUMN_EXPENSE_AMOUNT + ") FROM " + TABLEOFEXPENSE +
                " WHERE " + COLUMN_EXPENSE_USER + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{usernam});
        double total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        return total;
    }



    ////   WISH table
    public boolean addWish(String wishType, String wishsubtype, double wishAmount, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WISH_TYPE, wishType);
        values.put(COLUMN_WISH_DATE, wishsubtype);
        values.put(COLUMN_WISH_AMOUNT, wishAmount);
        values.put(COLUMN_WISH_USER, username);
        long result = db.insert(TABLEOFWISHLIST, null, values);
        return result != -1;
    }

    public Cursor getAllPENDByUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLEOFPEND +
                " WHERE " + COLUMN_PEND_USER + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{user});

        return cursor;
    }

    public Cursor getAlldebitByUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLEOFDEBIT +
                " WHERE " + COLUMN_DEBIT_USER + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{user});

        return cursor;
    }

    public Cursor getAllexpenseByUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLEOFEXPENSE +
                " WHERE " + COLUMN_EXPENSE_USER + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{user});

        return cursor;
    }

    public Cursor getAllwishByUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLEOFWISHLIST +
                " WHERE " + COLUMN_WISH_USER + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{user});

        return cursor;
    }
}
