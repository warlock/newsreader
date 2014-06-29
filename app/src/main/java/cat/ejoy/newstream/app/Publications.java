package cat.ejoy.newstream.app;

import android.content.Intent;
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
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cat.ejoy.newstream.app.R;

public class Publications extends ActionBarActivity implements AdapterView.OnItemClickListener {

    List<Product> Products = new ArrayList<Product>();
    ListView productListView;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);

        productListView = (ListView) findViewById(R.id.listView);
        dbHandler = new DatabaseHandler(getApplicationContext());
        try {
            dbHandler.loadDataBase();
            dbHandler.openDataBase();
            Bundle bundle = getIntent().getExtras();
            String country = bundle.getString("COUNTRY");
            Products = dbHandler.getProductsCountry(country);
        } catch (IOException e) {
            throw new Error("Error en carregar la db");
        }
        ArrayAdapter<Product> adapter = new ProductListAdapter();
        productListView.setAdapter(adapter);
        productListView.setOnItemClickListener(this);
        dbHandler.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product currentProduct = Products.get(position);
        //Toast.makeText(this, currentProduct.getUrl(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Publications.this,WebActivity.class);
        Bundle b = new Bundle();
        b.putString("URL",currentProduct.getUrl());
        intent.putExtras(b);
        startActivity(intent);
    }

    private class ProductListAdapter extends ArrayAdapter<Product> {
        public ProductListAdapter() {
            super(Publications.this, R.layout.listview_item, Products);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            Product currentProduct = Products.get(position);

            TextView name = (TextView) view.findViewById(R.id.textViewName);
            name.setText(currentProduct.getName());
            //TextView country = (TextView) view.findViewById(R.id.textViewCountry);
            //country.setText(currentProduct.getCountry());
            //TextView url = (TextView) view.findViewById(R.id.textViewUrl);
            //url.setText(currentProduct.getUrl());
            return view;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.publications, menu);
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
