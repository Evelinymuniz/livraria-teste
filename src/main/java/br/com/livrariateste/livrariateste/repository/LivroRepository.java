package br.com.livrariateste.livrariateste.repository;

import br.com.livrariateste.livrariateste.model.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Integer> {

}
