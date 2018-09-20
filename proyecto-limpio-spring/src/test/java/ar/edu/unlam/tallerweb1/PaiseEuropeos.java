package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;

public class PaiseEuropeos extends SpringTest{
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesEuropeos(){
		
		//preparacion
		
		Continente america = new Continente();
		america.setNombre("America");
		Continente europa = new Continente();
		europa.setNombre("Europa");
		
		Pais argentina = new Pais();
		argentina.setNombre("Argentina");
		argentina.setHabitantes(4000000);
		argentina.setIdioma("Español");
		argentina.setContinente(america);
		
		Pais italia = new Pais();
		italia.setNombre("Italia");
		italia.setHabitantes(15674652);
		italia.setIdioma("Italiano");
		italia.setContinente(europa);
		
		Pais inglaterra = new Pais();
		inglaterra.setNombre("Inglaterra");
		inglaterra.setHabitantes(2124524);
		inglaterra.setIdioma("Ingles");
		inglaterra.setContinente(europa);
		
		
		//ejecucion
		Session session = getSession();
		session.save(argentina);
		session.save(italia);
		session.save(inglaterra);
		
		
		//contrastacion
		
		List<Pais> paisesEuropeos = getSession().createCriteria(Pais.class)
				 .createAlias("continente", "cont")
				 .add(Restrictions.eq("cont.nombre", "Europa"))
				 .list(); //eq / ne / lt / gt / etc. 

		assertThat(paisesEuropeos).isNotNull();
		assertThat(paisesEuropeos.size()).isEqualTo(2);
		
	}
}
