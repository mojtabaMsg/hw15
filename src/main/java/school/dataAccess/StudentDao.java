package school.dataAccess;

import school.entities.Student;

import javax.persistence.EntityManager;

public class StudentDao extends AbstractDao<Student,Integer> {
    public StudentDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
