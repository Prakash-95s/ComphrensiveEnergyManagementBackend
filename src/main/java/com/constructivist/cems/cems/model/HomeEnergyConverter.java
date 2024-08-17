package com.constructivist.cems.cems.model;
import com.constructivist.cems.cems.model.HomeEnergy;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;



import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class HomeEnergyConverter implements AttributeConverter<HomeEnergy, String> {

    @Override
    public String convertToDatabaseColumn(HomeEnergy homeEnergy) {
        // Convert HomeEnergy object to a delimited string for easier storage in the database
        return homeEnergy.getHomeEnergyId() + "|" +
                homeEnergy.getHomeName() + "|" +
                homeEnergy.getEnergyTypes() + "|" +
                homeEnergy.getEnergyConsumption() + "|" +
                homeEnergy.getAppliancesTypes() + "|" +
                homeEnergy.getCarbonFootprint() + "|" +
                homeEnergy.isApartment();
    }

    @Override
    public HomeEnergy convertToEntityAttribute(String dbData) {
        // Convert the delimited string back to a HomeEnergy object
        String[] data = dbData.split("\\|");
        // Assuming data[2] is a comma-separated string, like "solar,wind,hydro"

        HomeEnergy homeEnergy = new HomeEnergy();
        homeEnergy.setHomeEnergyId(Long.parseLong(data[0]));
        homeEnergy.setHomeName(data[1]);
        // Set the List<String> to the HomeEnergy object
        homeEnergy.setEnergyTypes(data[2]);
        homeEnergy.setEnergyConsumption(Double.parseDouble(data[3]));
        homeEnergy.setAppliancesTypes(data[4]);
        homeEnergy.setCarbonFootprint(Double.parseDouble(data[5]));
        homeEnergy.setApartment(Boolean.parseBoolean(data[6]));

        return homeEnergy;
    }
}
