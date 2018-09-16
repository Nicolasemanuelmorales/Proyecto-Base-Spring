package ar.edu.unlam.tallerweb1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import static org.assertj.core.api.Assertions.*;

public class ProyectoTest extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void TestQueBuscaPisesDeHablaInglesa() {

		Pais pais1 = new Pais();
		pais1.setNombre("EEUU");
		pais1.setIdioma("Ingles");

		Pais pais2 = new Pais();
		pais2.setNombre("Argentina");
		pais2.setIdioma("Español");

		Session session = getSession();
		session.save(pais1);
		session.save(pais2);

		List<Pais> paisHablaInglesa = getSession()
				.createCriteria(Pais.class)
				.add(Restrictions.eq("idioma", "Ingles"))
				.list();

		assertThat(paisHablaInglesa).isNotNull();
		assertThat(paisHablaInglesa.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	@Rollback
	public void TestQueBuscaPaisesDelContinenteEuropeo() {

		Continente continente1 = new Continente();
		continente1.setNombre("E");
		
		Pais pais1 = new Pais();
		pais1.setNombre("España");
		pais1.setContinente(continente1);

		Pais pais2 = new Pais();
		pais2.setNombre("Italia");
		pais2.setContinente(continente1);

		Session session = getSession();
		session.save(pais1);
		session.save(pais2);
		session.save(continente1);

		List<Pais> paisEuropeo = getSession()
				.createCriteria(Pais.class)
				.createAlias("continente", "cont")
				.add(Restrictions.eq("cont.nombre", "E"))
				.list();

		assertThat(paisEuropeo).isNotNull();
		assertThat(paisEuropeo.size()).isEqualTo(2);
	}

}
