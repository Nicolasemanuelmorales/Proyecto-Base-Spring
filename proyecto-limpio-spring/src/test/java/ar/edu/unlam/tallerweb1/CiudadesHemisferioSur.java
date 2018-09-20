package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class CiudadesHemisferioSur extends SpringTest{
	
	@Test @Transactional @Rollback
	public void TestQueBuscaCiudadesDelHemisferioSur() {

		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud(4000.9);
		ubicacion1.setLongitud(215.3);
		
		Ciudad ciudad1 = new Ciudad();
		ciudad1.setNombre("Bs As");
		ciudad1.setUbicacionGeografica(ubicacion1);
		
		Ubicacion ubicacion2 = new Ubicacion();
		ubicacion2.setLatitud(25.9);
		ubicacion2.setLongitud(215.3);
		
		Ciudad ciudad2 = new Ciudad();
		ciudad2.setNombre("C.Mexico");
		ciudad2.setUbicacionGeografica(ubicacion2);
		
		Session session = getSession();
		session.save(ciudad1);
		session.save(ciudad2);

		List<Ciudad> ciudadesHemisferioSur = getSession()
				.createCriteria(Ciudad.class)
				.createAlias("ubicacionGeografica","ub")
				.add(Restrictions.le("ub.latitud", 200.6))
				.list();

		assertThat(ciudadesHemisferioSur).isNotNull();
		assertThat(ciudadesHemisferioSur.size()).isEqualTo(1);
	}
}
