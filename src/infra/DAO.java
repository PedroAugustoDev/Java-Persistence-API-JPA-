package infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO<T> {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<T> tClass;


    //static block
    static{
       try {
           emf = Persistence.createEntityManagerFactory("jpa_teste");
       } catch (Exception e){
           //do nothing
       }
    }

    public DAO(){
        this(null);
    }

    public DAO(Class tClass){
        this.tClass = tClass;
        this.em = emf.createEntityManager();
    }

    //CRUD
    //Insert -> insert into
    public DAO<T> insert(T object){
        em.persist(object);
        return this;
    }

    //Read -> select from... where id = ?
    public T findById(Object PK){
        return em.find(tClass, PK);
    }

    //Read -> select * from...
    public List<T> findLimitRegister(int maxLimit, int ofsset){
        final String JPQL = "SELECT u FROM " + tClass.getName() + " u";
        TypedQuery<T> query = em.createQuery(JPQL, tClass);
        query.setMaxResults(maxLimit);
        if(ofsset > 0)query.setFirstResult(ofsset);
        return query.getResultList();
    }

    //update
    public DAO<T> update(T object){
        em.merge(object);
        return this;
    }

    public void atomicInsert(T object){
        this.startTransaction()
                .insert(object)
                .transactionCommit();
    }

    public DAO<T> startTransaction(){
        em.getTransaction().begin();
        return this;
    }
    public DAO<T> transactionCommit(){
        em.getTransaction().commit();
        return this;
    }

    public void remove(T object){
        em.remove(object);
    }

    public void detach(T object){
        em.detach(object);
    }

    public void close(){
        em.close();
        emf.close();
    }

}
