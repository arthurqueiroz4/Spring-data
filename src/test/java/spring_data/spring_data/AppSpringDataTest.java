package spring_data.spring_data;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring_data.dao.InterfaceSpringDataUser;
import spring_data.dao.InterfaceTelefone;
import spring_data.model.Telefone;
import spring_data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser dataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {
		UsuarioSpringData data = new UsuarioSpringData();
		data.setEmail("javaavancado@javaavancado.com");
		data.setIdade(19);
		data.setLogin("java");
		data.setSenha("123");
		data.setNome("java1");
		
		dataUser.save(data);
	}
	
	@Test
	public void testeConsulta() {
		UsuarioSpringData data = dataUser.findById(13L).orElse(null);
		System.out.println(data.toString());
		data.getTelefones().forEach(t -> {
			System.out.println("Tipo: "+ t.getTipo());
			System.out.println("Numero: "+ t.getNumero());
		});
	}
	
	@Test
	public void testeConsultaAll() {
		Iterable<UsuarioSpringData> data = dataUser.findAll();
		data.forEach(c -> System.out.println(c.toString()));
	}
	
	@Test
	public void testeUpdate() {
		UsuarioSpringData data = dataUser.findById(1L).orElse(null);
		data.setNome("java1");
		dataUser.save(data);
	}
	
	@Test
	public void testeDelete() {
		UsuarioSpringData data = dataUser.findById(2L).orElse(null);
		dataUser.delete(data);
	}
	
	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> data = dataUser.buscarPorNome("java1");
		data.forEach(c -> System.out.println(c.toString()));
	}
	
	@Test
	public void testeConsultaNomeParam() {
		UsuarioSpringData data = dataUser.buscaPorNomeParam("java1");
		System.out.println(data.toString());
	}
	@Test
	public void testeDeletePorNome() {
		dataUser.deletePorNome("java avancado");
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		dataUser.updateEmailPorNome("javaavancado123@javaavancado.com", "java1");
	}
	
	@Test
	public void testeInsertTelefone() {
		UsuarioSpringData data = dataUser.findById(13L).orElse(null);
		
		Telefone telefone = new Telefone();
		telefone.setNumero("085085085085");
		telefone.setTipo("Celular");
		telefone.setUsuarioSpringData(data);
		
		interfaceTelefone.save(telefone);
	}
}
