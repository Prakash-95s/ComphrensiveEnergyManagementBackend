package com.constructivist.cems.cems.repository;

import com.constructivist.cems.cems.model.HomeEnergy;
import com.constructivist.cems.cems.model.User;
import com.constructivist.cems.cems.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HomeEnergyRepository extends JpaRepository<HomeEnergy, Long> {
    Collection<HomeEnergy> findByUsers(User users);

    List<HomeEnergy> findByEnergyTypes(String energyTypes);
}

