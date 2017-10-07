package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.TimelineCriteria;
import adopet.model.entity.Timeline;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimelineDAO implements BaseDAO<Timeline> {

    @Override
    public Long create(Connection conn, Timeline entity) throws Exception {
        String sql = "INSERT INTO timeline( data_hora, texto, anuncio_id, pessoa_cpf)  VALUES ( ?, ?, ?, ?) RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setTimestamp(++i, entity.getData_hora());
        statement.setString(++i, entity.getTexto());
        statement.setLong(++i, entity.getAnuncio_id());
        statement.setString(++i, entity.getPessoa_cpf());
        //statement.setLong(++i, entity.getFoto_id());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("id"));
        }
        resultSet.close();
        statement.close();
        return entity.getId();

    }

    @Override
    public Timeline readById(Connection conn, Long id) throws Exception {
        String sql = "select * from timeline where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Timeline timeline = null;
        while (resultSet.next()) {
            if (timeline == null) {
                timeline = new Timeline();
                timeline.setId(resultSet.getLong("id"));
                timeline.setTexto(resultSet.getString("texto"));

                timeline.setData_hora(resultSet.getTimestamp("data_hora"));
                timeline.setPessoa_cpf(resultSet.getString("pessoa_cpf"));
                //timeline.setFoto_id(resultSet.getLong("foto_id"));
            }

        }
        resultSet.close();
        statement.close();
        return timeline;
    }

    @Override
    public List<Timeline> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from timeline WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(TimelineCriteria.TEXTO_EQ)) {
                String telefone = (String) criteria.get(TimelineCriteria.TEXTO_EQ);
                sql += " AND timeline.texto = ?";
                paramList.add(telefone);
            }
            
             if (criteria.containsKey(TimelineCriteria.ANUNCIO_ID_EQ)) {
                Long anuncio_id = (Long) criteria.get(TimelineCriteria.ANUNCIO_ID_EQ);
                sql += " AND timeline.anuncio_id = ?";
                paramList.add(anuncio_id);
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
        List<Timeline> timelineList = new ArrayList<>();
        Timeline timeline = null;
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            if (timeline == null || !timeline.getId().equals(id)) {
                timeline = new Timeline();
                timeline.setId(resultSet.getLong("id"));
                timeline.setTexto(resultSet.getString("texto"));

                timeline.setData_hora(resultSet.getTimestamp("data_hora"));
                timeline.setPessoa_cpf(resultSet.getString("pessoa_cpf"));
             //   timeline.setFoto_id(resultSet.getLong("foto_id"));
                timelineList.add(timeline);
            }
        }
        resultSet.close();
        statement.close();
        return timelineList;
    }

    @Override
    public void update(Connection conn, Timeline entity) throws Exception {
        String sql = "UPDATE timeline  SET  data_hora=?, texto=?, anuncio_id=?, pessoa_cpf=?, foto_id=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setTimestamp(++i, entity.getData_hora());
        statement.setString(++i, entity.getTexto());

        statement.setLong(++i, entity.getAnuncio_id());
        statement.setString(++i, entity.getPessoa_cpf());
      //  statement.setLong(++i, entity.getFoto_id());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM timeline WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
