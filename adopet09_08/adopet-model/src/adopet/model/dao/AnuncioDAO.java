package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.AnuncioCriteria;
import adopet.model.entity.Anuncio;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnuncioDAO implements BaseDAO<Anuncio> {

    @Override
    public Long create(Connection conn, Anuncio entity) throws Exception {
        String sql = "INSERT INTO anuncio( data_hora, sexo, porte, raca, recompensa, caracteristicas, tipo, status, idade, local, especie_id, foto_id, pessoa_anunciante_cpf,  pessoa_adotante_cpf) VALUES ( ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?,?) RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setTimestamp(++i, entity.getData_hora());
        statement.setString(++i, entity.getSexo());
        statement.setString(++i, entity.getPorte());
        statement.setString(++i, entity.getRaca());
        statement.setString(++i, entity.getRecompensa());
        statement.setString(++i, entity.getCaracteristicas());
        statement.setString(++i, entity.getTipo());
        statement.setString(++i, entity.getStatus());
        statement.setInt(++i, entity.getIdade());
        statement.setString(++i, entity.getLocal());
        statement.setLong(++i, entity.getEspecie_id());
        statement.setLong(++i, entity.getFoto_id());
        statement.setString(++i, entity.getPessoaAnuncianteCpf());
        statement.setString(++i, entity.getPessoaAdotanteCpf());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("id"));
        }
        resultSet.close();
        statement.close();
        return entity.getId();

    }

    @Override
    public Anuncio readById(Connection conn, Long id) throws Exception {
        String sql = "select * from anuncio where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Anuncio anuncio = null;
        while (resultSet.next()) {
            if (anuncio == null) {
                anuncio = new Anuncio();
                anuncio.setId(resultSet.getLong("id"));
                anuncio.setSexo(resultSet.getString("sexo"));
                anuncio.setData_hora(resultSet.getTimestamp("data_hora"));
                anuncio.setPorte(resultSet.getString("porte"));
                anuncio.setRaca(resultSet.getString("raca"));
                anuncio.setRecompensa(resultSet.getString("recompensa"));
                anuncio.setCaracteristicas(resultSet.getString("caracteristicas"));
                anuncio.setTipo(resultSet.getString("tipo"));
                anuncio.setStatus(resultSet.getString("status"));
                anuncio.setIdade(resultSet.getInt("idade"));
                anuncio.setLocal(resultSet.getString("local"));
                anuncio.setFoto_id(resultSet.getLong("foto_id"));
                anuncio.setEspecie_id(resultSet.getLong("especie_id"));
                anuncio.setPessoaAnuncianteCpf(resultSet.getString("pessoa_anunciante_cpf"));
                anuncio.setPessoaAdotanteCpf(resultSet.getString("pessoa_adotante_cpf"));
            }

        }
        resultSet.close();
        statement.close();
        return anuncio;
    }

    @Override
    public List<Anuncio> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from anuncio WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(AnuncioCriteria.TIPO_EQ)) {
                String telefone = (String) criteria.get(AnuncioCriteria.TIPO_EQ);
                sql += " AND anuncio.tipo = ?";
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
        List<Anuncio> anuncioList = new ArrayList<>();
        Anuncio anuncio = null;
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            if (anuncio == null || !anuncio.getId().equals(id)) {
                anuncio = new Anuncio();
                anuncio.setId(resultSet.getLong("id"));
                anuncio.setSexo(resultSet.getString("sexo"));
                anuncio.setData_hora(resultSet.getTimestamp("data_hora"));
                anuncio.setPorte(resultSet.getString("porte"));
                anuncio.setRaca(resultSet.getString("raca"));
                anuncio.setRecompensa(resultSet.getString("recompensa"));
                anuncio.setCaracteristicas(resultSet.getString("caracteristicas"));
                anuncio.setTipo(resultSet.getString("tipo"));
                anuncio.setStatus(resultSet.getString("status"));
                anuncio.setIdade(resultSet.getInt("idade"));
                anuncio.setLocal(resultSet.getString("local"));
                anuncio.setFoto_id(resultSet.getLong("foto_id"));
                anuncio.setEspecie_id(resultSet.getLong("especie_id"));
                anuncio.setPessoaAnuncianteCpf(resultSet.getString("pessoa_anunciante_cpf"));
                anuncio.setPessoaAdotanteCpf(resultSet.getString("pessoa_adotante_cpf"));
                anuncioList.add(anuncio);
            }
        }
        resultSet.close();
        statement.close();
        return anuncioList;
    }

    @Override
    public void update(Connection conn, Anuncio entity) throws Exception {
        String sql = "UPDATE anuncio SET  data_hora=?, sexo=?, porte=?, raca=?, recompensa=?, caracteristicas=?,  tipo=?, status=?, idade=?, local=?, especie_id=?, foto_id=?,        pessoa_anunciante_cpf=?, pessoa_adotante_cpf=?  WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;

        statement.setTimestamp(++i, entity.getData_hora());
        statement.setString(++i, entity.getSexo());
        statement.setString(++i, entity.getPorte());
        statement.setString(++i, entity.getRaca());
        statement.setString(++i, entity.getRecompensa());
        statement.setString(++i, entity.getCaracteristicas());
        statement.setString(++i, entity.getTipo());
        statement.setString(++i, entity.getStatus());
        statement.setInt(++i, entity.getIdade());
        statement.setString(++i, entity.getLocal());
        statement.setLong(++i, entity.getEspecie_id());
        statement.setLong(++i, entity.getFoto_id());
        statement.setString(++i, entity.getPessoaAnuncianteCpf());
        statement.setString(++i, entity.getPessoaAdotanteCpf());
        statement.setLong(++i, entity.getId());

        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM anuncio WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
