package br.com.ufpb.ayty.contatos;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import br.com.ufpb.ayty.contatos.util.Contato;

public class AgendaApplication extends Application{

    public static final String TAG = "AgendaApplication";

    private List<Contato> contatos;

    @Override
    public void onCreate() {
        super.onCreate();
        contatos = new ArrayList<>();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void addContato(Contato contato){
        contatos.add(contato);
    }

    public void removerContato(Contato contato){
        contatos.remove(contato);
    }

    public List<Contato> getContatos(){
        return contatos;
    }

}
