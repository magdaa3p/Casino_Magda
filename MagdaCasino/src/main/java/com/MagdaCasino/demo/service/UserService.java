package com.MagdaCasino.demo.service;

import com.MagdaCasino.demo.model.User;
import com.MagdaCasino.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Obtener todos los usuarios
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// Guardar un nuevo usuario
	public User save(User user) {
		return userRepository.save(user);
	}

	// Encontrar un usuario por ID
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public String playGame(User user, int guess) {
		Random random = new Random();
		int numeroActual = random.nextInt(10) + 1;
		int moneyLost = 10;
		if (guess != numeroActual) {
			user.setMoney(user.getMoney() - moneyLost);
			userRepository.save(user);
			return "¡Incorrecto! El número era " + numeroActual + ". Has perdido " + moneyLost + " unidades.";
		} else {
			return "¡Correcto! El número era " + numeroActual + ".";
		}
	}
	public void updateUser(Long id, User user) {
		User usuarioExistente = userRepository.findById(id).orElseThrow();
		usuarioExistente.setName(user.getName());
		usuarioExistente.setEmail(user.getEmail());
		usuarioExistente.setMoney(user.getMoney());
		userRepository.save(usuarioExistente);
		
	}
	public void delateById(Long id) {
		userRepository.deleteById(id);
	}
	
}
