package io.joel.repositories;

import io.joel.models.Secret;
import io.joel.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretRepository extends CrudRepository<Secret, Long>{
    List<Secret> findAllByOwner(User owner);
    Secret findByIdAndOwner(long id, User owner);
}
