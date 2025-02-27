package school.sptech.STAYIN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.STAYIN.Enum.Status;
import school.sptech.STAYIN.model.Quarto;

import java.util.List;
import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto,Integer> {

    Optional<Quarto> findByNumero(Integer numero);

    List<Quarto> findByStatus(Status status);
}
