package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.EspecieCriteria;
import adopet.model.entity.Especie;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EspecieDAO implements BaseDAO<Especie> {

    @Override
    public Long create(Connection conn, Especie entity) throws Exception {
        String sql = "INSERT INTO especie(nome) VALUES (?) RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getNome());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("id"));
        }
        resultSet.close();
        statement.close();
        return entity.getId();

    }

    @Override
    public Especie readById(Connection conn, Long id) throws Exception {
        String sql = "select * from especie where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Especie especie = null;
        while (resultSet.next()) {
            if (especie == null) {
                especie = new Especie();
                especie.setId(resultSet.getLong("id"));
                especie.setNome(resultSet.getString("nome"));
            }

        }
        resultSet.close();
        statement.close();
        return especie;
    }

    @Override
    public List<Especie> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from especie WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(EspecieCriteria.NOME_EQ)) {
                String telefone = (String) criteria.get(EspecieCriteria.NOME_EQ);
                sql += " AND especie.nome = ?";
                paramList.add(telefone);
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
        List<Especie> especieList = new ArrayList<>();
        Especie especie = null;
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            if (especie == null || !especie.getId().equals(id)) {
                especie = new Especie();
                especie.setId(resultSet.getLong("id"));
                especie.setNome(resultSet.getString("nome"));
                especieList.add(especie);
            }
        }
        resultSet.close();
        statement.close();
        return especieList;
    }

    @Override
    public void update(Connection conn, Especie entity) throws Exception {
        String sql = "UPDATE especie  SET nome=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getNome());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM especie WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
