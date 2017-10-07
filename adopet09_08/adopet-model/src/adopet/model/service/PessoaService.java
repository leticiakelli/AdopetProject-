package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BasePessoaService;
import adopet.model.criteria.PessoaCriteria;
import adopet.model.dao.PessoaDAO;
import adopet.model.entity.Pessoa;
import adopet.model.entity.Pessoa;
import adopet.model.util.ValidaCPF;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PessoaService implements BasePessoaService {

    @Override
    public Long create(Pessoa entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            PessoaDAO dao = new PessoaDAO();
            id = dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return id;
    }
    
    public String createByCpf(Pessoa entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        String cpf = null;
        try {
            PessoaDAO dao = new PessoaDAO();
            cpf = dao.createByCpf(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return cpf;
    }

    @Override
    public Pessoa readById(Long id) throws Exception {
        return  null;
    }
    
    @Override
    public Pessoa readByCpf(String cpf) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaDAO dao = new PessoaDAO();
            Pessoa usuario = dao.readByCPF(conn, cpf);
            conn.commit();
            conn.close();
            return usuario;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public List<Pessoa> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaDAO dao = new PessoaDAO();
            List<Pessoa> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
            return usuarioList;
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void update(Pessoa entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaDAO dao = new PessoaDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaDAO dao = new PessoaDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }
    
    public void deleteByCpf(String cpf) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaDAO dao = new PessoaDAO();
            dao.deleteByCpf(conn, cpf);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        Map<String, String> errors = new HashMap<>();

        String cpf = (String) fields.get("cpf");
        if (cpf != null) {
            //valida cpf
            if (!ValidaCPF.isCPF(cpf)) {
                errors.put("cpf", "CPF incorreto");
            }

        }

        Long foto_id = (Long) fields.get("foto_id");
        if (foto_id == null) {
            errors.put("foto_id", "foto_id incorreto");

        }

        Long usuario_id = (Long) fields.get("foto_id");
        if (usuario_id == null) {
            errors.put("usuario_id", "usuario_id incorreto");
        }

        return errors;
    }
}
