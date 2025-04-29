package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private PasswordEncoder encoder;

	@InjectMocks
	private UsuarioService usuarioService;

	// Inicia todos os mocks antes de iniciar os testes
	@BeforeEach
	void setup(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createUserSuccess() throws Exception {
		Usuario usuario = new Usuario("Felipe", "email@email.com", "123456");
		String senhaCriptografada = "";

		when(encoder.encode("123456")).thenReturn(senhaCriptografada);

		// 1 - Cria instancia da classe Usuario
		// 2 - Responde com o proprio usuario.
		when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

		// Simula o save do usuario.
		Usuario resultado = usuarioService.save(usuario);

		assertNotNull(resultado);
		assertEquals("Felipe", resultado.getNome());
		assertEquals("email@email.com", resultado.getEmail());
		assertEquals(senhaCriptografada, resultado.getSenha());

		verify(encoder, times(1)).encode("123456");
		verify(usuarioRepository, times(1)).save(resultado);
	}

	@Test
	void createUserError() throws IllegalArgumentException{
		// Quando há algum verificação, como campo em branco
		Usuario usuario = new Usuario(null, "email@email.com", "123456");

		// lança a exceção com assertThrows passando por parametro a classe de exceção e um método anonimo com o service
//		IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.save(usuario));

		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> usuarioService.save(usuario)
		);

		// Compara a exceção lançada com a da exceção original
		assertEquals("Campos obrigatórios!", exception.getMessage());

		verify(usuarioRepository, never()).save(any());
	}


	@Test
	void findById() {
	}

	@Test
	@DisplayName("Deve retornar o email informado caso usuario cadastrado")
	void findByEmail() {
		Usuario usuario = new Usuario("Felipe", "email@email.com", "123456");

		when(usuarioRepository.findByEmail("email@email.com")).thenReturn(Optional.of(usuario));

		Optional<Usuario> result = Optional.ofNullable(usuarioService.findByEmail("email@email.com"));

		assertEquals("email@email.com", result.get().getEmail());
		verify(usuarioRepository, times(1)).findByEmail("email@email.com");
	}

	@Test
	void delete() {
	}
}