package com.solvd.essay.persistence.jdbcImpl;

import com.solvd.essay.domain.EssayModule;

import java.sql.*;
import java.util.List;

public class EssayModuleRepositoryImpl extends AbstractDao<EssayModule> {
    public EssayModuleRepositoryImpl() {
    }



    @Override
    public String getTableName() {
        return "essay_module";
    }


    @Override
    public String getTableFields() {
        return "module_description";
    }

    /*
    @Override
    public String getThingFields(EssayModule essayModule.xml) {
        return essayModule.xml.getModuleDescription();
    }

     */

    @Override
    public EssayModule mapResultToObject(ResultSet resultSet,Connection conn) throws SQLException {

        EssayModule entity= new EssayModule();
        entity.setId(resultSet.getLong("id"));
        entity.setModuleDescription(resultSet.getString("module_description"));
        return entity;
    }

    @Override
    public Long getThingId(EssayModule entity) {
        return entity.getId();
    }

    @Override
    protected String getUpdateQuery(EssayModule entity,Long id) {
        String updateQuery= String.format("update essay_module set module_description=\"%s\" where id= %s",
                entity.getModuleDescription(),id);

        return updateQuery;
    }

    @Override
    protected String getCreateQuery() {
        String createQuery= String.format("insert into %s(%s) value (?)",getTableName(),getTableFields());
        return createQuery;
    }

    @Override
    protected void setQueryStatements(PreparedStatement ps, EssayModule thingToCreate) {
        try {
            ps.setString(1,thingToCreate.getModuleDescription());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected EssayModule returnVoidInstance() {
        return new EssayModule();
    }

    @Override
    protected void saveListInObject(EssayModule thing, List<EssayModule> list) {
        thing.setEssayModuleList(list);
    }
}
