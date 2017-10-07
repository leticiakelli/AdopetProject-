package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BaseAnuncioService;
import adopet.model.dao.AnuncioDAO;
import adopet.model.entity.Anuncio;
import adopet.model.entity.Anuncio;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnuncioService implements BaseAnuncioService {

    @Override
    public Long create(Anuncio entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            AnuncioDAO dao = new AnuncioDAO();
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
    public Anuncio readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AnuncioDAO dao = new AnuncioDAO();
            Anuncio usuario = dao.readById(conn, id);
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
    public List<Anuncio> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AnuncioDAO dao = new AnuncioDAO();
            List<Anuncio> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(Anuncio entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            AnuncioDAO dao = new AnuncioDAO();
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
            AnuncioDAO dao = new AnuncioDAO();
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
        if (fields.get("tipo") == null) {
            errors.put("tipo", "Tipo incorreto");
        }
        return errors;
    }
}
