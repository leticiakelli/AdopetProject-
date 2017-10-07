package adopet.model.dao;

import adopet.model.base.BaseDAO;
import adopet.model.criteria.PessoaCriteria;
import adopet.model.entity.Pessoa;
import adopet.model.util.PreparedStatementBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PessoaDAO implements BaseDAO<Pessoa> {

    @Override
    public Long create(Connection conn, Pessoa entity) throws Exception {
        String sql = "INSERT INTO pessoa( cpf, nome, logradouro, numero, complemento, bairro, cidade, estado,    foto_id, usuario_id, pessoa_telefone_id)  VALUES (?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?) RETURNING cpf;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getLogradouro());
        statement.setInt(++i, entity.getNumero());
        statement.setString(++i, entity.getComplemento());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        statement.setLong(++i, entity.getFoto_id());
        statement.setLong(++i, entity.getUsuario_id());
        statement.setLong(++i, entity.getPessoaTelefone_id());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setId(resultSet.getLong("cpf"));
        }
        resultSet.close();
        statement.close();
        return entity.getId();

    }

    public String createByCpf(Connection conn, Pessoa entity) throws Exception {
        String sql = "INSERT INTO pessoa( cpf, nome, logradouro, numero, complemento, bairro, cidade, estado,    foto_id, usuario_id, pessoa_telefone_id)  VALUES (?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?) RETURNING cpf;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getLogradouro());
        statement.setInt(++i, entity.getNumero());
        statement.setString(++i, entity.getComplemento());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        statement.setLong(++i, entity.getFoto_id());
        statement.setLong(++i, entity.getUsuario_id());
        statement.setLong(++i, entity.getPessoaTelefone_id());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            entity.setCpf(resultSet.getString("cpf"));
        }
        resultSet.close();
        statement.close();
        return entity.getCpf();

    }

    @Override
    public Pessoa readById(Connection conn, Long id) throws Exception {
        String sql = "select * from pessoa where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Pessoa pessoa = null;
        while (resultSet.next()) {
            if (pessoa == null) {
                pessoa = new Pessoa();
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setLogradouro(resultSet.getString("logradouro"));
                pessoa.setNumero(resultSet.getInt("numero"));
                pessoa.setComplemento(resultSet.getString("complemento"));
                pessoa.setBairro(resultSet.getString("bairro"));
                pessoa.setCidade(resultSet.getString("cidade"));
                pessoa.setEstado(resultSet.getString("estado"));
                pessoa.setFoto_id(resultSet.getLong("foto_id"));
                pessoa.setUsuario_id(resultSet.getLong("usuario_id"));
                pessoa.setPessoaTelefone_id(resultSet.getLong("pessoa_telefone_id"));

            }

        }
        resultSet.close();
        statement.close();
        return pessoa;
    }

    public Pessoa readByCPF(Connection conn, String cpf) throws Exception {
        String sql = "select * from pessoa where cpf=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        ResultSet resultSet = statement.executeQuery();
        Pessoa pessoa = null;
        while (resultSet.next()) {
            if (pessoa == null) {
                pessoa = new Pessoa();
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setLogradouro(resultSet.getString("logradouro"));
                pessoa.setNumero(resultSet.getInt("numero"));
                pessoa.setComplemento(resultSet.getString("complemento"));
                pessoa.setBairro(resultSet.getString("bairro"));
                pessoa.setCidade(resultSet.getString("cidade"));
                pessoa.setEstado(resultSet.getString("estado"));
                pessoa.setFoto_id(resultSet.getLong("foto_id"));
                pessoa.setUsuario_id(resultSet.getLong("usuario_id"));
                pessoa.setPessoaTelefone_id(resultSet.getLong("pessoa_telefone_id"));

            }

        }
        resultSet.close();
        statement.close();
        return pessoa;
    }

    @Override
    public List<Pessoa> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "select * from pessoa WHERE 1=1 ";

        List<Object> paramList = new ArrayList<>();
        if (criteria != null) {

            if (criteria.containsKey(PessoaCriteria.CPF_EQ)) {
                String cpf = (String) criteria.get(PessoaCriteria.CPF_EQ);
                sql += " AND pessoa.cpf = ?";
                paramList.add(cpf);
            }

            if (criteria.containsKey(PessoaCriteria.NOME_EQ)) {
                String nome = (String) criteria.get(PessoaCriteria.NOME_EQ);
                sql += " AND pessoa.nome = ?";
                paramList.add(nome);
            }

            if (criteria.containsKey(PessoaCriteria.FOTO_ID_EQ)) {
                String nome = (String) criteria.get(PessoaCriteria.FOTO_ID_EQ);
                sql += " AND pessoa.foto_id = ?";
                paramList.add(nome);
            }

            if (criteria.containsKey(PessoaCriteria.USUARIO_ID_EQ)) {
                Long param = (Long) criteria.get(PessoaCriteria.USUARIO_ID_EQ);
                sql += " AND pessoa.usuario_id = ?";
                paramList.add(param);
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
        List<Pessoa> pessoaList = new ArrayList<>();
        Pessoa pessoa = null;
        while (resultSet.next()) {
            pessoa = new Pessoa();
            pessoa.setCpf(resultSet.getString("cpf"));
            pessoa.setNome(resultSet.getString("nome"));
            pessoa.setLogradouro(resultSet.getString("logradouro"));
            pessoa.setNumero(resultSet.getInt("numero"));
            pessoa.setComplemento(resultSet.getString("complemento"));
            pessoa.setBairro(resultSet.getString("bairro"));
            pessoa.setCidade(resultSet.getString("cidade"));
            pessoa.setEstado(resultSet.getString("estado"));
            pessoa.setFoto_id(resultSet.getLong("foto_id"));
            pessoa.setUsuario_id(resultSet.getLong("usuario_id"));
            pessoa.setPessoaTelefone_id(resultSet.getLong("pessoa_telefone_id"));
            pessoaList.add(pessoa);

        }
        resultSet.close();
        statement.close();
        return pessoaList;
    }

    @Override
    public void update(Connection conn, Pessoa entity) throws Exception {
        String sql = "UPDATE pessoa SET cpf=?, nome=?, logradouro=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?, foto_id=?, usuario_id=?, pessoa_telefone_id=? WHERE cpf=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, entity.getCpf());
        statement.setString(++i, entity.getNome());
        statement.setString(++i, entity.getLogradouro());
        statement.setInt(++i, entity.getNumero());
        statement.setString(++i, entity.getComplemento());
        statement.setString(++i, entity.getBairro());
        statement.setString(++i, entity.getCidade());
        statement.setString(++i, entity.getEstado());
        statement.setLong(++i, entity.getFoto_id());
        statement.setLong(++i, entity.getUsuario_id());
        statement.setLong(++i, entity.getPessoaTelefone_id());
        statement.setString(++i, entity.getCpf());

        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM pessoa WHERE cpf=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
    }

    public void deleteByCpf(Connection conn, String cpf) throws Exception {
        String sql = "DELETE FROM pessoa WHERE cpf=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.execute();
        statement.close();
    }

}
