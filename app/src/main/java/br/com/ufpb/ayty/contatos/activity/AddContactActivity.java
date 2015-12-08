package br.com.ufpb.ayty.contatos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

import br.com.ufpb.ayty.contatos.AgendaApplication;
import br.com.ufpb.ayty.contatos.R;
import br.com.ufpb.ayty.contatos.util.Contato;

public class AddContactActivity extends BaseActivity {

    public static final String TAG = "Adicionar Contatos";

    private EditText mTextName;
    private EditText mTextLastName;
    private EditText mTextPhone;
    private EditText mTextMail;

    private Spinner mSpinnerPhone;
    private Spinner mSpinnerMail;

    private AgendaApplication application;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        setUpToolbar(R.id.toolbar_add_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        application = ((AgendaApplication) getActivity().getApplicationContext());

        if(getIntent().getExtras() != null){
            contato = application.getContatos().get(getIntent().getExtras().getInt("position"));
        }

        mTextName = (EditText) findViewById(R.id.mTextName);
        mTextName.setTypeface(getTypeface());

        mTextLastName = (EditText) findViewById(R.id.mTextLastName);
        mTextLastName.setTypeface(getTypeface());

        mTextPhone = (EditText) findViewById(R.id.mTextPhone);
        mTextPhone.setTypeface(getTypeface());

        mTextMail = (EditText) findViewById(R.id.mTextMail);
        mTextMail.setTypeface(getTypeface());

        mSpinnerPhone = (Spinner) findViewById(R.id.spinner_phone);
        mSpinnerPhone.setAdapter(autoPopulateSpinner());

        mSpinnerMail = (Spinner) findViewById(R.id.spinner_mail);
        mSpinnerMail.setAdapter(autoPopulateSpinner());

        if(contato != null){
            mTextName.setText(contato.getNome());
            mTextLastName.setText(contato.getSobrenome());
            mTextPhone.setText(contato.getTelefone());
            mTextMail.setText(contato.getEmail());
        }

    }

    private void attemptCreateContact() {

        Intent intent;

        // Captura os valores
        String nome = mTextName.getText().toString();
        String sobrenome = mTextLastName.getText().toString();
        String telefone = mTextPhone.getText().toString();
        String email = mTextMail.getText().toString();

        String tipoNumero = mSpinnerPhone.getSelectedItem().toString();
        String tipoMail = mSpinnerMail.getSelectedItem().toString();

        boolean cancel = false;

        if (!TextUtils.isEmpty(nome)) {
            cancel = true;
        } else if (!TextUtils.isEmpty(sobrenome)) {
            cancel = true;
        } else if (!TextUtils.isEmpty(telefone)) {
            cancel = true;
        } else if (!TextUtils.isEmpty(email)) {
            cancel = true;
        }

        if (cancel) {
            if(contato == null){
                contato = new Contato();
            }
            contato.setIdImage(getDrawable());
            contato.setNome(nome);
            contato.setSobrenome(sobrenome);
            contato.setTelefone(telefone);
            contato.setTipoTelefone(tipoNumero);
            contato.setEmail(email);
            contato.setTipoEmail(tipoMail);

            // Adiciona o contato no banco
            application.addContato(contato);

            // Avisa que voi adicionado
            Toast.makeText(getContext(), getString(R.string.add_sucess), Toast.LENGTH_SHORT).show();

            // Vai pra tela de mostrar o contato
            intent = new Intent(getActivity(), ViewContactActivity.class);
            intent.putExtra("position", (application.getContatos().size() - 1));
            startActivity(intent);
            finish();

        } else {
            intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private int getDrawable(){

        int[] draw = new int[]{R.drawable.ic_yellow_light, R.drawable.ic_roxo, R.drawable.ic_red,
                R.drawable.ic_purple, R.drawable.ic_blue, R.drawable.ic_cyan,
                R.drawable.ic_green, R.drawable.ic_green_black, R.drawable.ic_indigo, R.drawable.ic_orange,
                R.drawable.ic_pink};
        Random random = new Random();

        return draw[random.nextInt(draw.length - 1)];
    }

    private ArrayAdapter<String> autoPopulateSpinner() {
        String[] itens = new String[]{"Celular", "Residencial", "Comercial", "Outros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, itens);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            attemptCreateContact();
        } else if (id == R.id.action_clean_all) {
            mTextName.setText("");
            mTextLastName.setText("");
            mTextPhone.setText("");
            mTextMail.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}
