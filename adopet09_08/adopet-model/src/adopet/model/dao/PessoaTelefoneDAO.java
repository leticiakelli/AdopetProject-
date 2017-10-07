package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.PessoaTelefoneCriteria;
import adopet.model.entity.PessoaTelefone;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PessoaTelefoneDAO implements BaseDAO<PessoaTelefone> {

    @Override
    public Long create(Connection conn, PessoaTelefone entity) throws Exception {
        String sql = "INSERT INTO pessoa_telefone( telefone, celular) VALUES (?, ?) RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getTelefone());
        statement.setString(++i, entity.getCelular());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("id"));
        }
        resultSet.close();
        statement.close();
        return entity.getId();

    }

    @Override
    public PessoaTelefone readById(Connection conn, Long id) throws Exception {
        String sql = "select * from pessoa_telefone where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        PessoaTelefone pessoa_telefone = null;
        while (resultSet.next()) {
            if (pessoa_telefone == null) {
                pessoa_telefone = new PessoaTelefone();
                pessoa_telefone.setId(resultSet.getLong("id"));
                pessoa_telefone.setTelefone(resultSet.getString("telefone"));
                pessoa_telefone.setCelular(resultSet.getString("celular"));
            }

        }
        resultSet.close();
        statement.close();
        return pessoa_telefone;
    }

    @Override
    public List<PessoaTelefone> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from pessoa_telefone WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(PessoaTelefoneCriteria.TELEFONE_EQ)) {
                String telefone = (String) criteria.get(PessoaTelefoneCriteria.TELEFONE_EQ);
                sql += " AND pessoa_telefone.telefone = ?";
                paramList.add(telefone);
            }

            if (criteria.containsKey(PessoaTelefoneCriteria.CELULAR_EQ)) {
                String celular = (String) criteria.get(PessoaTelefoneCriteria.CELULAR_EQ);
                sql += " AND pessoa_telefone.celular = ?";
                paramList.add(celular);
            }


        }

        if (limit != null) {
            sql += " LIMIT ?";
            paramList.add(limit);
        }
        if (offset != null) {
            sql += " OFFSET ?";
            paramList.add(offset);
        }

        PreparedStatement statement = PreparedStatementBuilder.build(conn, sql, paramList);
        ResultSet resultSet = statement.executeQuery();
        List<PessoaTelefone> pessoa_telefoneList = new ArrayList<>();
        PessoaTelefone pessoa_telefone = null;
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            if (pessoa_telefone == null || !pessoa_telefone.getId().equals(id)) {
                pessoa_telefone = new PessoaTelefone();
                pessoa_telefone.setId(resultSet.getLong("id"));
                pessoa_telefone.setTelefone(resultSet.getString("telefone"));
                pessoa_telefone.setCelular(resultSet.getString("celular"));
                pessoa_telefoneList.add(pessoa_telefone);
            }
        }
        resultSet.close();
        statement.close();
        return pessoa_telefoneList;
    }

    @Override
    public void update(Connection conn, PessoaTelefone entity) throws Exception {
        String sql = "UPDATE pessoa_telefone  SET  telefone=?, celular=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getTelefone());
        statement.setString(++i, entity.getCelular());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM pessoa_telefone WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
