package ch.unil.doplab.service.domain;

import ch.unil.doplab.domain.User;

import ch.unil.doplab.domain.User;

public class UserService {

    public boolean authenticate(User user) {
        // Vérification simple (à remplacer par une vérif BDD)
        return "admin".equals(user.getUsername()) && "1234".equals(user.getPassword());
    }
}

