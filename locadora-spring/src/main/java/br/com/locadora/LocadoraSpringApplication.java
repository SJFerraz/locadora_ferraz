package br.com.locadora;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.locadora.endpoint.ClienteEndpoint;
import br.com.locadora.enumer.LocacaoStatus;
import br.com.locadora.model.Cliente;
import br.com.locadora.model.Funcionario;
import br.com.locadora.model.Locacao;
import br.com.locadora.model.Veiculo;
import br.com.locadora.repository.UsuarioRepository;
import br.com.locadora.util.CustomDateUtils;

@SpringBootApplication
public class LocadoraSpringApplication {
	
	private static UsuarioRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LocadoraSpringApplication.class, args);
		System.out.println(LocacaoStatus.ABERTO);
		Date date = new Date();
		
		
		
		System.out.println(CustomDateUtils.formateDateTimeCompleteDashToString(date));
//		
		System.out.println(CustomDateUtils.convertStringDateTimeDashToDateType("2012-06-01 12:00:02"));
		
		Cliente joselito = new Cliente("Joselito", "999000222202", "josel",
					"$2a$10$ZUjQiCY5JXatIxuJW8Box.pqHUMKymyv75LKtcvLFqx2qq3Z.uEae", "Rua Tal", "s.ferraz@live.com");
		Funcionario joao = new Funcionario("João Vitor", "222.333", "jvitor", "6789", "Analista TI", "j.silva@live.com");
		Veiculo vw = new Veiculo("VW", "Vectra", "GPG-5450", "2020", "azul");
		Locacao locacao = new Locacao(new Date(), null, null, 11.1, "Observado", null,1L, 2L);
				
		/*@SuppressWarnings("unchecked")
		Cliente cliente = (Cliente) userRepository.save(joselito);
		
		System.out.println(cliente);*/
	}
}
