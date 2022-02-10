package com.App.demo.calc.repo;

import com.App.demo.calc.models.Calculations;
import org.springframework.data.repository.CrudRepository;

public interface CalculationsRepository extends CrudRepository<Calculations, Long> {
}
