package br.com.ufpb.ayty.contatos.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import br.com.ufpb.ayty.contatos.AgendaApplication;
import br.com.ufpb.ayty.contatos.R;
import br.com.ufpb.ayty.contatos.util.Contato;

public class ViewContactActivity extends AppCompatActivity {

    public static final String TAG = "Visualizar Contato";

    private AgendaApplication application;
    private Contato contato;

    private TextView mViewNumberPhone;
    private TextView mViewPhoneType;
    private TextView mViewAndressMail;

    private View mViewPhone;
    private View mViewMail;
    private ImageView mMessageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        application = ((AgendaApplication) getApplicationContext());
        contato = application.getContatos().get(getIntent().getExtras().getInt("position"));

        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(contato.getNomeCompleto());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_view);
        toolbar.setTitle(contato.getNomeCompleto());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewNumberPhone = (TextView) findViewById(R.id.phoneNumber);
        mViewNumberPhone.setText(contato.getTelefone());

        mViewPhoneType = (TextView) findViewById(R.id.type_phone);
        mViewPhoneType.setText(contato.getTipoTelefone());

        mViewAndressMail = (TextView) findViewById(R.id.mailUser);
        mViewAndressMail.setText(contato.getEmail());

        mViewPhone = findViewById(R.id.telefone_ligar);
        mViewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Ligando...", Snackbar.LENGTH_SHORT).show();
            }
        });

        mViewMail = findViewById(R.id.mail);
        mViewMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Enviando email...", Snackbar.LENGTH_SHORT).show();
            }
        });

        mMessageView = (ImageView) findViewById(R.id.message_contact);
        mMessageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Enviando messagem...", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent intent = new Intent(ViewContactActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(id == R.id.action_edit){
            Intent intent = new Intent(ViewContactActivity.this, AddContactActivity.class);
            intent.putExtra("position", getIntent().getExtras().getInt("position"));
            startActivity(intent);
            finish();
        }else if (id == R.id.action_delete){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_del_message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    application.removerContato(contato);
                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            }).create().show();
        }

        return super.onOptionsItemSelected(item);
    }
}
