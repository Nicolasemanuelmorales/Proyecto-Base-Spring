package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class PaisesConCapitalTropicoDeCancerTest extends SpringTest{
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesConCapitalAlNorteDelTropicoDeCancer(){
		
		//preparacion
		
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
		
		//ejecucion
		Session session = getSession();
		session.save(argentina);
		session.save(eeuu);
		
		
		//contrastacion
		
		List<Pais> paisesAlNorteDelTropico = getSession().createCriteria(Pais.class)
				 .createAlias("capital", "cap")
				 .createAlias("cap.ubicacionGeografica","ubicacion")
				 .add(Restrictions.gt("ubicacion.latitud", 23.4372222222))
				 .list(); //eq / ne / lt / gt / etc. 

		assertThat(paisesAlNorteDelTropico).isNotNull();
		assertThat(paisesAlNorteDelTropico.size()).isEqualTo(1);
		
	}
}
