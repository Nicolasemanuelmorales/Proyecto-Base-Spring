package ar.edu.unlam.tallerweb1;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class AllTest extends SpringTest{
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesDeHablaInglesa(){

		Continente america = new Continente();
		america.setNombre("America");
		Continente europa = new Continente();
		europa.setNombre("Europa");
		
		Pais argentina = new Pais();
		argentina.setNombre("Argentina");
		argentina.setHabitantes(4000000);
		argentina.setIdioma("Español");
		argentina.setContinente(america);
		
		Pais eeuu = new Pais();
		eeuu.setNombre("Estados Unidos");
		eeuu.setHabitantes(15674652);
		eeuu.setIdioma("Ingles");
		eeuu.setContinente(america);
		
		Pais inglaterra = new Pais();
		inglaterra.setNombre("Inglaterra");
		inglaterra.setHabitantes(2124524);
		inglaterra.setIdioma("Ingles");
		inglaterra.setContinente(europa);
		
		Session session = getSession();
		session.save(argentina);
		session.save(eeuu);
		session.save(inglaterra);

		List<Pais> paisesLenguaInglesa = getSession().createCriteria(Pais.class)
				 .add(Restrictions.eq("idioma", "Ingles"))
				 .list(); 

		assertThat(paisesLenguaInglesa).isNotNull();
		assertThat(paisesLenguaInglesa.size()).isEqualTo(2);
		
	}
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesEuropeos(){
		
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
		
		Session session = getSession();
		session.save(argentina);
		session.save(italia);
		session.save(inglaterra);

		List<Pais> paisesEuropeos = getSession().createCriteria(Pais.class)
				 .createAlias("continente", "cont")
				 .add(Restrictions.eq("cont.nombre", "Europa"))
				 .list(); 

		assertThat(paisesEuropeos).isNotNull();
		assertThat(paisesEuropeos.size()).isEqualTo(2);
		
	}
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesConCapitalAlNorteDelTropicoDeCancer(){
		
		Continente america = new Continente();
		america.setNombre("America");
		
		Ciudad capitalArgentina = new Ciudad();
		capitalArgentina.setNombre("Buenos Aires");
		Ubicacion ubicacionBsAs = new Ubicacion();
		ubicacionBsAs.setLatitud(-34.6131500);
		ubicacionBsAs.setLongitud(-58.3772300);
		capitalArgentina.setUbicacionGeografica(ubicacionBsAs);
		
		Ciudad capitalEEUU = new Ciudad();
		capitalEEUU.setNombre("Washington D. C.");
		Ubicacion ubicacionEEUU = new Ubicacion();
		ubicacionEEUU.setLatitud(38.8951100);
		ubicacionEEUU.setLongitud(-77.0363700);
		capitalEEUU.setUbicacionGeografica(ubicacionEEUU);
		
		Pais argentina = new Pais();
		argentina.setNombre("Argentina");
		argentina.setHabitantes(4000000);
		argentina.setIdioma("Español");
		argentina.setContinente(america);
		argentina.setCapital(capitalArgentina);
		
		Pais eeuu = new Pais();
		eeuu.setNombre("Estados Unidos");
		eeuu.setHabitantes(15674652);
		eeuu.setIdioma("Ingles");
		eeuu.setContinente(america);
		eeuu.setCapital(capitalEEUU);
		
		Session session = getSession();
		session.save(argentina);
		session.save(eeuu);
		
		List<Pais> paisesAlNorteDelTropico = getSession().createCriteria(Pais.class)
				 .createAlias("capital", "cap")
				 .createAlias("cap.ubicacionGeografica","ubicacion")
				 .add(Restrictions.gt("ubicacion.latitud", 23.4372222222))
				 .list(); 

		assertThat(paisesAlNorteDelTropico).isNotNull();
		assertThat(paisesAlNorteDelTropico.size()).isEqualTo(1);
		
	}
	
	@Test @Transactional @Rollback
	public void TestQueBuscaCiudadesDelHemisferioSur() {

		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud(-4000.9);
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
				.add(Restrictions.lt("ub.latitud", 0.00))
				.list();
		
		for(Ciudad ciudad : ciudadesHemisferioSur){
			assertThat(ciudad.getUbicacionGeografica().getLatitud()).isLessThanOrEqualTo(0.00);
		}

	}
	
	
}