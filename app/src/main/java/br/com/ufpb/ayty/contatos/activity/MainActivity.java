package br.com.ufpb.ayty.contatos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpb.ayty.contatos.AgendaApplication;
import br.com.ufpb.ayty.contatos.R;
import br.com.ufpb.ayty.contatos.custom.ContatosAdapter;
import br.com.ufpb.ayty.contatos.custom.UpDownRecyclerScroll;
import br.com.ufpb.ayty.contatos.util.Contato;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";

    private AgendaApplication application;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private List<Contato> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = ((AgendaApplication) getApplicationContext());
        if (application.getContatos() != null) {
            contatos = application.getContatos();
        }else{
            contatos = new ArrayList<>();
        }

        setUpToolbar(R.id.toolbar_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ContatosAdapter(contatos, getContext(), onClickListener()));
        recyclerView.addOnScrollListener(new UpDownRecyclerScroll() {
            @Override
            public void show() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void hide() {
                fab.animate().translationY(fab.getHeight() + 30).setInterpolator(new AccelerateInterpolator(2)).start();

            }
        });
    }

    private ContatosAdapter.ContatoOnClickListener onClickListener() {
        return new ContatosAdapter.ContatoOnClickListener() {
            @Override
            public void onClickContato(View view, int idx) {
                Intent intent = new Intent(MainActivity.this, ViewContactActivity.class);
                intent.putExtra("position", idx);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchList());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(this);
            alertBuilder.setIcon(R.mipmap.ic_launcher).setMessage(R.string.about_text)
                    .setTitle(R.string.action_about).create().show();
        }

        return super.onOptionsItemSelected(item);
    }

    private class SearchList implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            searchContatosView(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            searchContatosView(newText);
            return true;
        }


        private int searchContatosView(String text) {
            return 0;
        }
    }

}
