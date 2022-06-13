package com.example.demo.repository;

import com.example.demo.model.View;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ViewRepository extends CrudRepository<View, UUID> {

}