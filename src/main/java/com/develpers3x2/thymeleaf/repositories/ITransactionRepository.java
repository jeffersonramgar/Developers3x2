package com.develpers3x2.thymeleaf.repositories;

import com.develpers3x2.thymeleaf.entidad.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionRepository extends CrudRepository<Transaction, Long> {
}
