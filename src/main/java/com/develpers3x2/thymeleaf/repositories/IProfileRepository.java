package com.develpers3x2.thymeleaf.repositories;

import com.develpers3x2.thymeleaf.entidad.Profile;
import org.springframework.data.repository.CrudRepository;

public interface IProfileRepository extends CrudRepository<Profile, Long> {
}
