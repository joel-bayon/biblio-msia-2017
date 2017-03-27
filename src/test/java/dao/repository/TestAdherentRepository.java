package dao.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import entity.Adherent;
import repository.jpa.AdherentDao;

public class TestAdherentRepository {
	
	static ApplicationContext spring = new ClassPathXmlApplicationContext("spring.xml");
	AdherentDao adherentDao = spring.getBean(AdherentDao.class);
	
	
	@Test
	public  void daoTest() throws RuntimeException {
		Adherent ad1 = new Adherent("Dupond", "Jean", "0234567812", "jean.dupont.@yahoo.fr");
		Adherent ad2 = new Adherent("Durant", "Jacques", "0223674512", "jacques.durant@free.fr");
		Adherent ad3 = new Adherent("Martin", "Bernadette", "0138792012", "m.bernadette@gmail.com");
		ad1 = adherentDao.save(ad1);
		ad2 = adherentDao.save(ad2);
		ad3 = adherentDao.save(ad3);

		for(Adherent a : adherentDao.findAll())
			System.out.println(a);
		
		Assert.assertEquals("DupondJacques0138792012", adherentDao.findOne(ad1.getId()).getNom()+adherentDao.findOne(ad2.getId()).getPrenom()+adherentDao.findOne(ad3.getId()).getTel());
		adherentDao.delete(ad1.getId());
		List<Adherent> adherents = adherentDao.findAll();
		Assert.assertEquals(2, adherents.size());
		adherentDao.delete(adherents);
		Assert.assertEquals(0, adherentDao.findAll().size());
	}
	
	@Test 
	public  void daoExceptionTest() throws RuntimeException {
		Adherent ad1 = new Adherent("Dupond", "Jean", "0234567812", "jean.dupont.@yahoo.fr");
		ad1 = adherentDao.save(ad1);
		Adherent a  = adherentDao.findOne(ad1.getId());
		Assert.assertEquals("jean.dupont.@yahoo.fr", a.getEmail());
		adherentDao.delete(a);
		a = adherentDao.findOne(ad1.getId());
		Assert.assertNull(a);
	}
}
