package com.unicomer.micro.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unicomer.micro.clients.repository.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
