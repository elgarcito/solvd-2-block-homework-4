package com.solvd.essay.persistence.impl;

import com.solvd.essay.domain.LabTestReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LabTestReportRepositoryImpl extends AbstractDao<LabTestReport> {
    public LabTestReportRepositoryImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected String getUpdateQuery(LabTestReport newThingToUpdate) {
        String updateQuery=String.format("update %s set essay_code=\"%s\" ,date_of_essay=\"%s\",essay_description=\"%s\",equipment_for_test_model_id=%s,batch_info_id=%s,employee_id=%s,essay_module_id=%s where id=%s",
                getTableName(),newThingToUpdate.getEssayCode(),newThingToUpdate.getDateOfEssay(),
                newThingToUpdate.getEssayDescription(), newThingToUpdate.getEquipmentForTestModelId(),
                newThingToUpdate.getBatchInfoId(),newThingToUpdate.getEmployeeId(),
                newThingToUpdate.getEssayModuleId(),newThingToUpdate.getId());

        return updateQuery;
    }

    @Override
    protected String getTableName() {
        return "lab_test_report";
    }

    @Override
    protected String getTableFields() {
        return "essay_code,date_of_essay,essay_description,equipment_for_test_model_id,batch_info_id,employee_id,essay_module_id";
    }

    @Override
    protected Long getThingId(LabTestReport thing) {
        return thing.getId();
    }

    @Override
    protected LabTestReport mapResultToObject(ResultSet resultSet, Connection conn) throws SQLException {
        LabTestReport entity=new LabTestReport();
        entity.setId(resultSet.getLong("id"));
        entity.setEssayCode(resultSet.getString("essay_code"));
        entity.setDateOfEssay(resultSet.getDate("date_of_essay"));
        entity.setEssayDescription(resultSet.getString("essay_description"));
        entity.setEquipmentForTestModelId(resultSet.getLong("equipment_for_test_model_id"));
        entity.setBatchInfoId(resultSet.getLong("batch_info_id"));
        entity.setEmployeeId(resultSet.getLong("employee_id"));
        entity.setEssayModuleId(resultSet.getLong("essay_module_id"));
        return entity;
    }

    @Override
    protected String getCreateQuery() {
        String createQuery= String.format("insert into %s(%s) value (?,?,?,?,?,?,?)",getTableName(),getTableFields());
        return createQuery;
    }

    @Override
    protected void setQueryStatements(PreparedStatement ps, LabTestReport thingToCreate) {
        try {
            ps.setString(1,thingToCreate.getEssayCode());
            ps.setDate(2,thingToCreate.getDateOfEssay());
            ps.setString(3,thingToCreate.getEssayDescription());
            ps.setLong(4,thingToCreate.getEquipmentForTestModelId());
            ps.setLong(5,thingToCreate.getBatchInfoId());
            ps.setLong(6,thingToCreate.getEmployeeId());
            ps.setLong(7,thingToCreate.getEssayModuleId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
