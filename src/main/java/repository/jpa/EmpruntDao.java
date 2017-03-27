package repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import entity.Emprunt;

@Transactional
public interface EmpruntDao extends JpaRepository<Emprunt, Integer> {

	@Query("select e from Emprunt e join e.livre l where l.id = ?1")
	List<Emprunt> findByLivre(Integer id);

	@Query("select e from Emprunt e join e.adherent a where a.id = ?1 and e.fin = null")
	List<Emprunt> findByAdherent(Integer id);

	@Query("select e from Emprunt e join e.adherent a where a.id = ?1 and e.fin = null")
	List<Emprunt> getEmpruntsEnCoursByAdherent(int id1);
	
	@Query("select e from Emprunt e where e.fin = null")
	List<Emprunt> getAllEmpruntsEnCours();
	
	@Query("select e from Emprunt e where e.fin != null")
	List<Emprunt> getAllEmpruntsTermines();

	@Query("select e from Emprunt e join e.livre l where l.id = ?1 and e.fin = null")
	Emprunt getEmpruntEnCoursByLivre(int idLivre);
}
