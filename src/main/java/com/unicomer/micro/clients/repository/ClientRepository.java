package com.unicomer.micro.clients.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unicomer.micro.clients.repository.entity.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>{

}
