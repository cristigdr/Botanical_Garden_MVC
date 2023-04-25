package Model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class PlantRepository {

    private Plant plant;

    public PlantRepository() {
    }
    public PlantRepository(Plant plant) {
        this.plant = plant;
    }

    public boolean savePlant(Plant plant){
        boolean saved = false;
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(plant);
            tx.commit();
            saved = true;
        }catch(HibernateException e){
            tx.rollback();
        }finally{
            session.close();
        }
        return saved;
    }

    public boolean updatePlant(Plant updatedPlant, String plantId) {
        boolean updated = false;
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Plant plantToUpdate = session.get(Plant.class, plantId);
            plantToUpdate.setName(updatedPlant.getName());
            plantToUpdate.setType(updatedPlant.getType());
            plantToUpdate.setSpecies(updatedPlant.getSpecies());
            plantToUpdate.setCarnivorous(updatedPlant.getCarnivorous());
            plantToUpdate.setZone(updatedPlant.getZone());
            session.update(plantToUpdate);
            tx.commit();
            updated = true;

        } catch (HibernateException e) {
            tx.rollback();

        } finally {
            session.close();
        }
        return updated;
    }

    public boolean deletePlant(String plantId) {
        boolean deleted = false;
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Plant plantToDelete = session.get(Plant.class, plantId);
            if (plantToDelete != null) {
                session.delete(plantToDelete);
                tx.commit();
                deleted = true;
            }
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }

        return deleted;
    }

    public List<Plant> getPlants() {
        Session session = Repository.getSessionFactory().openSession();
        List<Plant> users = session.createQuery("from Plant", Plant.class).getResultList();
        session.close();
        return users;
    }

    public List<Plant> filterPlants(String criteria, String filter) {
        Session session = Repository.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Plant> plants = null;
        try {
            String sql = "SELECT * FROM plants WHERE " + criteria + " LIKE :filter";
            NativeQuery<Plant> query = session.createNativeQuery(sql, Plant.class);
            query.setParameter("filter", "%" + filter + "%");
            plants = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return plants;
    }

    public boolean checkIfPlantExists(Plant plant) {
        List<Plant> plants = getPlants();
        for (Plant p : plants) {
            if (p.getName().equals(plant.getName())) {
                return true;
            }
        }
        return false;
    }

}
