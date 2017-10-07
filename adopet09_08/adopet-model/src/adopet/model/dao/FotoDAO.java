package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.FotoCriteria;
import adopet.model.entity.Foto;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FotoDAO implements BaseDAO<Foto> {

    @Override
    public Long create(Connection conn, Foto entity) throws Exception {
        String sql = "INSERT INTO foto(nome) VALUES (?) RETURNING id;";
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
    public Foto readById(Connection conn, Long id) throws Exception {
        String sql = "select * from foto where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Foto foto = null;
        while (resultSet.next()) {
            if (foto == null) {
                foto = new Foto();
                foto.setId(resultSet.getLong("id"));
                foto.setNome(resultSet.getString("nome"));
            }

        }
        resultSet.close();
        statement.close();
        return foto;
    }

    @Override
    public List<Foto> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from foto WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(FotoCriteria.NOME_EQ)) {
                String telefone = (String) criteria.get(FotoCriteria.NOME_EQ);
                sql += " AND foto.nome = ?";
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
        List<Foto> fotoList = new ArrayList<>();
        Foto foto = null;
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            if (foto == null || !foto.getId().equals(id)) {
                foto = new Foto();
                foto.setId(resultSet.getLong("id"));
                foto.setNome(resultSet.getString("nome"));
                fotoList.add(foto);
            }
        }
        resultSet.close();
        statement.close();
        return fotoList;
    }

    @Override
    public void update(Connection conn, Foto entity) throws Exception {
        String sql = "UPDATE foto  SET nome=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setString(++i, entity.getNome());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM foto WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
