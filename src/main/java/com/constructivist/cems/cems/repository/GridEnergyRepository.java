package com.constructivist.cems.cems.repository;

import com.constructivist.cems.cems.model.GridEnergy;
import com.constructivist.cems.cems.model.HomeEnergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GridEnergyRepository extends JpaRepository<GridEnergy, Long>{
}
