package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BasePessoaTelefone;
import adopet.model.dao.PessoaTelefoneDAO;
import adopet.model.entity.PessoaTelefone;
import adopet.model.entity.PessoaTelefone;
import adopet.model.util.ValidaCPF;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PessoaTelefoneService implements BasePessoaTelefone {

    @Override
    public Long create(PessoaTelefone entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            PessoaTelefoneDAO dao = new PessoaTelefoneDAO();
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

    @Override
    public PessoaTelefone readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaTelefoneDAO dao = new PessoaTelefoneDAO();
            PessoaTelefone usuario = dao.readById(conn, id);
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
    public List<PessoaTelefone> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaTelefoneDAO dao = new PessoaTelefoneDAO();
            List<PessoaTelefone> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(PessoaTelefone entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PessoaTelefoneDAO dao = new PessoaTelefoneDAO();
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
            PessoaTelefoneDAO dao = new PessoaTelefoneDAO();
            dao.delete(conn, id);
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

        String telefone = (String) fields.get("telefone");
        if(telefone==null){
            errors.put("telefone", "Telefone esta vazio");
        }
//TODO: testar parametros
        return errors;
    }
}
