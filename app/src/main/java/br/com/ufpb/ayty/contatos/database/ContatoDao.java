package br.com.ufpb.ayty.contatos.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import br.com.ufpb.ayty.contatos.util.Contato;

/**
 * Created by Ittalo Pessoa on 08/12/2015.
 */
public class ContatoDao extends BaseDaoImpl<Contato, Integer> {

    public ContatoDao(ConnectionSource connectionSource) throws SQLException {
        super(Contato.class);
        setConnectionSource(connectionSource);
        initialize();
    }

    @Override
    public QueryBuilder<Contato, Integer> queryBuilder() {
        return super.queryBuilder();
    }

    @Override
    public List<Contato> query(PreparedQuery<Contato> preparedQuery) throws SQLException {
        return super.query(preparedQuery);
    }
}
