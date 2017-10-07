package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BaseEspecieService;
import adopet.model.dao.EspecieDAO;
import adopet.model.entity.Especie;
import adopet.model.entity.Especie;
import adopet.model.entity.Especie;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspecieService implements BaseEspecieService {

    @Override
    public Long create(Especie entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            EspecieDAO dao = new EspecieDAO();
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
    public Especie readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EspecieDAO dao = new EspecieDAO();
            Especie usuario = dao.readById(conn, id);
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
    public List<Especie> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EspecieDAO dao = new EspecieDAO();
            List<Especie> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(Especie entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EspecieDAO dao = new EspecieDAO();
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
            EspecieDAO dao = new EspecieDAO();
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
        if (fields.get("nome") == null) {
            errors.put("nome", "Nome da foto incorreto");
        }
        return errors;
    }
}
