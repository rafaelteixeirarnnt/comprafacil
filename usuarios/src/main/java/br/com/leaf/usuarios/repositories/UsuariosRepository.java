package br.com.leaf.usuarios.repositories;

import br.com.leaf.usuarios.models.Usuarios;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {

    Optional<Usuarios> findByEmail(String email);

}
