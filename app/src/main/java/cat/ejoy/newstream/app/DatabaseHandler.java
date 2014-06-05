package cat.ejoy.newstream.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by josep on 05/06/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsManager",
            TABLE_PRODUCTS = "products",
            KEY_ID = "id",
            KEY_NAME="name",
            KEY_COUNTRY="country",
            KEY_URL="url";
    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_COUNTRY + " TEXT, " + KEY_URL + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void createProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_COUNTRY, product.getCountry());
        values.put(KEY_URL, product.getUrl());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Product getProduct(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_ID, KEY_NAME, KEY_COUNTRY, KEY_URL}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3) );
        db.close();
        cursor.close();
        return product;
    }

    public void deleteProduct(Product product) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + "=?", new String[] { String.valueOf(product.getId()) });
        db.close();
    }

    public int getProductsCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public int updateProduct(Product product) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_COUNTRY, product.getCountry());
        values.put(KEY_URL, product.getUrl());
        db.insert(TABLE_PRODUCTS, null, values);
        return db.update(TABLE_PRODUCTS, values, KEY_ID + "=?", new String[] { String.valueOf(product.getId()) });
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
        if (cursor.moveToFirst())
        {
            do {
                Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3) );
                products.add(product);
            }
            while (cursor.moveToNext());
        }
        return products;
    }
}
