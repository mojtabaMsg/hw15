package school.dataAccess;

import school.entities.Teacher;

import javax.persistence.EntityManager;

public class TeacherDao extends AbstractDao<Teacher,Integer>{
    public TeacherDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }
}
