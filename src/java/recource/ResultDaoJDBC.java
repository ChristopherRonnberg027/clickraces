/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recource;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Result;

/**
 *
 * @author ITHSivju
 */
public class ResultDaoJDBC implements ResultDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public ResultDaoJDBC() {}
    
    public ResultDaoJDBC(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    

    @Override
    public void createResult(Result resultToCreate) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(resultToCreate);
        em.getTransaction().commit();
        
        em.close();
        
    }

    @Override
    public List<Result> getToTen() {
        em.getTransaction().begin();
        
        return em.createQuery("SELECT name, COUNT(name) FROM result GROUP BY name ORDER BY COUNT(name);")
                .getResultList();
    }
    
    
    
}
