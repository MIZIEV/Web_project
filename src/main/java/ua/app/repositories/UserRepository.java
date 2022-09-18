package ua.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.app.models.AppUser;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {
    public Optional<AppUser> findByUserName(String userName);
}