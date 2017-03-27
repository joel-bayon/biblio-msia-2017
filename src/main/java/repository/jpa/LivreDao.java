package repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import entity.Emprunt;
import entity.Livre;

@Transactional
public interface LivreDao extends JpaRepository<Livre, Integer> {

	@Modifying
	@Query("update Livre l set l.titre = ?2, l.auteur=?3, l.parution = ?4 where l.id = ?1")
	public void update(Integer id, String titre, String auteur, int parution);
	@Query("select count(l) from Livre l where l.titre = ?1 and l.auteur=?2")
	public long getCount(String titre, String auteur);
	@Query("select count(l) from Livre l where l = ?1")
	public long getCount(Livre livre);
	@Query("select l from Livre l where l.id not in (select e.livre.id from Emprunt e where fin = null)")
	List<Livre> getLivreNotEmprunt();
	
}

