package com.constructivist.cems.cems.repository;

import com.constructivist. cems.cems.model.Transport;
import com.constructivist.cems.cems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long>{
    List<Transport> findByUser(User user);

    List<Transport> getVehiclesByUser(User user);
}
