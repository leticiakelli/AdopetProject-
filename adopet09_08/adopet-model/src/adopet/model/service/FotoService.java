package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BaseFotoService;
import adopet.model.dao.FotoDAO;
import adopet.model.entity.Foto;
import adopet.model.entity.Foto;
import adopet.model.entity.Usuario;
import adopet.model.util.ValidaCPF;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FotoService implements BaseFotoService {

    @Override
    public Long create(Foto entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            FotoDAO dao = new FotoDAO();
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
    public Foto readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            FotoDAO dao = new FotoDAO();
            Foto usuario = dao.readById(conn, id);
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
    public List<Foto> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            FotoDAO dao = new FotoDAO();
            List<Foto> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(Foto entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            FotoDAO dao = new FotoDAO();
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
            FotoDAO dao = new FotoDAO();
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
