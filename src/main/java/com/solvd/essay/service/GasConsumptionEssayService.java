package com.solvd.essay.service;

import com.solvd.essay.domain.GasConsumptionEssay;
import com.solvd.essay.domain.LabTestReport;
import com.solvd.essay.persistence.impl.AbstractDao;
import com.solvd.essay.persistence.impl.LabTestReportRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GasConsumptionEssayService {
    private final AbstractDao<GasConsumptionEssay> gasConsumptionEssayImpl;

    public GasConsumptionEssayService(AbstractDao<GasConsumptionEssay> gasConsumptionEssayAbstractDao){
        this.gasConsumptionEssayImpl = gasConsumptionEssayAbstractDao;
    }

    public void create(GasConsumptionEssay gasConsumptionEssay){
        try {
            gasConsumptionEssayImpl.create(gasConsumptionEssay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<GasConsumptionEssay> findAll(Connection conn) throws SQLException {
        List<GasConsumptionEssay> listOfGasEssays=gasConsumptionEssayImpl.getAll();
        for (GasConsumptionEssay gasConEssay:listOfGasEssays) {}
        return listOfGasEssays;

    }

    public GasConsumptionEssay findOne(Long id, Connection conn) throws SQLException {
        GasConsumptionEssay gasConEssay= gasConsumptionEssayImpl.findById(id);
        return gasConEssay;
    }

    public void deleteOne(Long id) throws SQLException {
        gasConsumptionEssayImpl.deleteById(id);
    }

    public void deleteEntity(GasConsumptionEssay gasConsumptionEssay){
        gasConsumptionEssayImpl.delete(gasConsumptionEssay);
    }

    public void updateEntity(GasConsumptionEssay gasConsumptionEssay){
        try {
            gasConsumptionEssayImpl.update(gasConsumptionEssay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
