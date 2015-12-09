package br.com.ufpb.ayty.contatos;

import android.accounts.Account;
import android.app.Application;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import br.com.ufpb.ayty.contatos.database.ContatoDao;
import br.com.ufpb.ayty.contatos.database.DBHelper;
import br.com.ufpb.ayty.contatos.util.Contato;

public class AgendaApplication extends Application{

    public static final String TAG = "AgendaApplication";

    private List<Contato> contatos;
    private DBHelper helper;
    private ContatoDao contatoDao;

    @Override
    public void onCreate() {
        super.onCreate();
        helper = new DBHelper(getBaseContext());
        try {
            contatoDao = new ContatoDao(helper.getConnectionSource());
            contatos = contatoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            contatos = new ArrayList<>();
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    public void atualizarLista(){
        List<Contato> back = contatos;
        try {
            contatos = contatoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            contatos = back;
        }
    }
    public void addContato(Contato contato){
        try {
            contatoDao.createOrUpdate(contato);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            atualizarLista();
        }


    }

    public void removerContato(Contato contato){
        try {
            contatoDao.delete(contato);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            atualizarLista();
        }
    }

    public List<Contato> getContatos(){
        atualizarLista();
        return contatos;
    }
    public List<Contato> getContatosByName(String nome) throws SQLException {
        // get our query builder from the DAO
        QueryBuilder<Contato, Integer> queryBuilder =
                contatoDao.queryBuilder();
        // the 'password' field must be equal to "qwerty"
        queryBuilder.where().like("(nome || sobrenome)", "%"+nome+"%");
        // prepare our sql statement
        PreparedQuery<Contato> preparedQuery = queryBuilder.prepare();
        // query for all accounts that have "qwerty" as a password
        List<Contato> contatos = contatoDao.query(preparedQuery);

        return contatos;
    }

}
