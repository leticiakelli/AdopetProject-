
package adopet.model.service;

import adopet.model.ConnectionManager;
import adopet.model.base.service.BaseTimelineService;
import adopet.model.dao.TimelineDAO;
import adopet.model.entity.Timeline;
import adopet.model.entity.Timeline;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TimelineService implements BaseTimelineService{
    @Override
    public Long create(Timeline entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Long id = null;
        try {
            TimelineDAO dao = new TimelineDAO();
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
    public Timeline readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TimelineDAO dao = new TimelineDAO();
            Timeline usuario = dao.readById(conn, id);
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
    public List<Timeline> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TimelineDAO dao = new TimelineDAO();
            List<Timeline> usuarioList = dao.readByCriteria(conn, criteria, limit, offset);
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
    public void update(Timeline entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TimelineDAO dao = new TimelineDAO();
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
            TimelineDAO dao = new TimelineDAO();
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
        if (fields.get("texto") == null) {
            errors.put("texto", "texto incorreto");
        }
        return errors;
    }
}
