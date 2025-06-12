package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    Optional<User> getUserByUsername(String username);
    List<User> getUsersByRole(String role);
    List<User> getAllUsers();
}
