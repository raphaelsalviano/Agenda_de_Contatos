package br.com.ufpb.ayty.contatos.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import br.com.ufpb.ayty.contatos.R;
import br.com.ufpb.ayty.contatos.util.Contato;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.ContatoViewHolder>{

    public static final String TAG = "ContatosAdapter";

    private final List<Contato> contatos;
    private final Context context;
    private final ContatoOnClickListener onClickListener;
    private final Random random;

    public ContatosAdapter(List<Contato> contatos, Context context, ContatoOnClickListener onClickListener) {
        this.contatos = contatos;
        this.context = context;
        this.onClickListener = onClickListener;
        this.random = new Random();
    }

    public interface ContatoOnClickListener{
        public void onClickContato(View view, int idx);
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_contact_adapter, parent, false);

        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContatoViewHolder holder, final int position) {

        final Contato contato = contatos.get(position);

        holder.mImageContact.setImageResource(contato.getIdImage());
        holder.mTextContact.setText(contato.getNome() + " " + contato.getSobrenome());

        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickContato(holder.view, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.contatos != null ? this.contatos.size() : 0;
    }

    public class ContatoViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private ImageView mImageContact;
        private TextView mTextContact;

        public ContatoViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            mImageContact = (ImageView) view.findViewById(R.id.contato_img);
            mTextContact = (TextView) view.findViewById(R.id.contact_name_adapter);
        }
    }
}
