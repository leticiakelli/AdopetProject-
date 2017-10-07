package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.UsuarioCriteria;
import adopet.model.entity.Usuario;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsuarioDAO implements BaseDAO<Usuario> {

    @Override
    public Long create(Connection conn, Usuario entity) throws Exception {
        String sql = "INSERT INTO usuario( email, senha) VALUES (?, md5('Repolho'||?||'Paralelepipedo')) RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getEmail());
        statement.setString(++i, entity.getSenha());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("id"));
        }
        resultSet.close();
        statement.close();
        return entity.getId();
                
    }


    @Override
    public Usuario readById(Connection conn, Long id) throws Exception {
        String sql = "select * from usuario where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Usuario usuario = null;
        while (resultSet.next()) {
            if (usuario == null) {
                usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
            }

        }
        resultSet.close();
        statement.close();
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from usuario WHERE 1=1";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(UsuarioCriteria.EMAIL_EQ)) {
                String email = (String) criteria.get(UsuarioCriteria.EMAIL_EQ);
                sql += " AND usuario.email = ?";
                paramList.add(email);
            }

            if (criteria.containsKey(UsuarioCriteria.SENHA_EQ)) {
                String senha = (String) criteria.get(UsuarioCriteria.SENHA_EQ);
                sql += " AND usuario.senha = md5('Repolho'||?||'Paralelepipedo')";
                paramList.add(senha);
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
        List<Usuario> usuarioList = new ArrayList<>();
        Usuario usuario = null;
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            if (usuario == null || !usuario.getId().equals(id)) {
                usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuarioList.add(usuario);
            }

        }
        resultSet.close();
        statement.close();
        return usuarioList;
    }

    @Override
    public void update(Connection conn, Usuario entity) throws Exception {
        String sql = "UPDATE usuario SET  email=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getEmail());
     //   statement.setString(++i, entity.getSenha());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

}
