package com.constructivist.cems.cems.repository;

import com.constructivist.cems.cems.model.City;
import com.constructivist.cems.cems.model.GridEnergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}