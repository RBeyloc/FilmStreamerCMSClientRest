package com.example.demo.repository;

import com.example.demo.model.View;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ViewRepository extends CrudRepository<View, Long> {

}