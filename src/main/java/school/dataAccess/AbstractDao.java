package school.dataAccess;

import javax.persistence.EntityManager;

public abstract class AbstractDao<E,I> {
    private EntityManager entityManager;

    public AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(E entity){
        entityManager.persist(entity);
    }

    public E load(I id){
        return entityManager.find(getEntityClass(),id);
    }

    public void delete(E entity){
        entityManager.remove(entity);
    }

    public void update(E entity){
        entityManager.merge(entity);
    }

    public abstract Class<E> getEntityClass();
}
