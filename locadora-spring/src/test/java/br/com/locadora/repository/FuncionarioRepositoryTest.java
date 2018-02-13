package br.com.locadora.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.locadora.model.Funcionario;
import br.com.locadora.util.ValidatorMessagesUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionarioRepositoryTest {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		Funcionario funcionario = new Funcionario("Joselito", "999000222202", "josel",
				"$2a$10$ZUjQiCY5JXatIxuJW8Box.pqHUMKymyv75LKtcvLFqx2qq3Z.uEae", "Rua Tal","joselitosemnocao@mail.com");
		this.funcionarioRepository.save(funcionario);
		assertThat(funcionario.getId()).isNotNull();
		assertThat(funcionario.getNome()).isEqualTo("Joselito");
		assertThat(funcionario.getEmail()).isEqualTo("joselitosemnocao@mail.com");
	}
	
	@Test
	public void deleteShouldRemoveData() {
		Funcionario funcionario = new Funcionario("Joselito", "999000222202", "josel",
				"$2a$10$ZUjQiCY5JXatIxuJW8Box.pqHUMKymyv75LKtcvLFqx2qq3Z.uEae", "Rua Tal","joselitosemnocao@mail.com");
		this.funcionarioRepository.save(funcionario);
		funcionarioRepository.delete(funcionario);
		assertThat(funcionarioRepository.findOne(funcionario.getId())).isNull();
		
	}
	
	
	@Test
	public void updateShouldChangeAndPersistData() {
		Funcionario funcionario = new Funcionario("Joselito", "999000222202", "josel",
				"$2a$10$ZUjQiCY5JXatIxuJW8Box.pqHUMKymyv75LKtcvLFqx2qq3Z.uEae", "Rua Tal","joselitosemnocao@mail.com");
		this.funcionarioRepository.save(funcionario);
		funcionario.setNome("Sonic the Hedgehog");
		funcionario.setEmail("sonic@dev.com");
		this.funcionarioRepository.save(funcionario);
		System.out.println(funcionario);
		funcionario = this.funcionarioRepository.findOne(funcionario.getId());
		System.out.println(funcionario);
		funcionario = this.funcionarioRepository.findOne(3l);
		System.out.println(funcionario);
		assertThat(funcionario.getNome()).isEqualTo("Samuel Ferraz");
		assertThat(funcionario.getEmail()).isEqualTo(null);
	}
	
	@Test
	public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
		Funcionario funcionario = new Funcionario("Joselito", "999000222202", "josel",
				"$2a$10$ZUjQiCY5JXatIxuJW8Box.pqHUMKymyv75LKtcvLFqx2qq3Z.uEae", "Rua Tal","joselitosemnocao@mail.com");
		Funcionario funcionario2 = new Funcionario("joselito","203902930", "josel2",
				"$2a$10$ZUjQiCY5JXatIxuJW8Box.pqHUMKymyv75LKtcvLFqx2qq3Z.uEae", "Rua kasn","joselitosemnocao@mail.com");
		this.funcionarioRepository.save(funcionario);
		this.funcionarioRepository.save(funcionario2);
		List<Funcionario> funcionarioList = funcionarioRepository.findByNomeIgnoreCaseContaining("joselito");
		assertThat(funcionarioList.size()).isEqualTo(2);
	}
	
	@Test
	public void createWhenNameIsNullShouldThrowConstrantViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo nome é obrigatorio");
		this.funcionarioRepository.save(new Funcionario());
	}
	
	@Test
	public void createWhenEmailIsNullShouldThrowConstrantViolationException() {
		thrown.expect(ConstraintViolationException.class);
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Mario Bros");
		this.funcionarioRepository.save(funcionario);
	}
	
	@Test
	public void createWhenEmailIsNotValidShouldThrowConstrantViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo email não é valido");
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Mario Bros");
		funcionario.setEmail("Mario Bros");
		this.funcionarioRepository.save(funcionario);
	}
}
