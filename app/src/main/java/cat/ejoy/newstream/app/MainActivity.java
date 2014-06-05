package cat.ejoy.newstream.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{

    List<Product> Products = new ArrayList<Product>();
    ListView productListView;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productListView = (ListView) findViewById(R.id.listView);
        dbHandler = new DatabaseHandler(getApplicationContext());
        populateList();

        /*ListView listView = (ListView) findViewById(R.id.listView);
        String[] items = {"Pais1","Pais2","Pais3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        */
    }

    private void populateList() {
        ArrayAdapter<Product> adapter = new ProductListAdapter();
        productListView.setAdapter(adapter);
    }

    private class ProductListAdapter extends ArrayAdapter<Product> {
        public ProductListAdapter() {
            super(MainActivity.this, R.layout.listview_item, Products);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            Product currentProduct = Products.get(position);

            TextView name = (TextView) view.findViewById(R.id.textViewName);
            name.setText(currentProduct.getName());
            TextView country = (TextView) view.findViewById(R.id.textViewCountry);
            country.setText(currentProduct.getName());
            TextView url = (TextView) view.findViewById(R.id.textViewUrl);
            url.setText(currentProduct.getUrl());

            return view;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        //Log.i("HelloListView","You clicked" + id + " at position: " + position);
        //Toast.makeText(this, "You clicked" + id + " at position: " + position, Toast.LENGTH_SHORT).show();
        //News hola = new News("Nom","Pais","url");
        //Toast.makeText(this, hola.getName(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
