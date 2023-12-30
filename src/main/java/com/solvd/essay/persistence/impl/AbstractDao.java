package com.solvd.essay.persistence.impl;

import com.solvd.essay.persistence.InterfaceGenericDao;
import com.solvd.essay.persistence.InterfaceGenericDao1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements InterfaceGenericDao1<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    private T newClass;
    public Connection conn;

    public AbstractDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(T thingToCreate) throws SQLException {
        try {
            conn.setAutoCommit(false);
            if(thingToCreate==null){
                throw new RuntimeException("No null entity can be added");
            }

            //String query="insert into "+getTableName()+"("+getTableFields()+")" +" values ("+getThingFields(thingToCreate)+")";
            String createQuery= getCreateQuery(); // only has the queries with ??? return string
            PreparedStatement ps =conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            setQueryStatements(ps,thingToCreate);// do the setString,date, etc for each value return void
            //ps.setString(1, getThingFields(thingToCreate));
            int number= ps.executeUpdate();
            LOGGER.info("Object was added to database successfully the row affected was: "+number);
        } catch (Exception e) {
            LOGGER.error(e.getMessage()) ;
            conn.rollback();
        }
        finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        try {
            conn.setAutoCommit(false);
            String query=String.format("select * from %s",getTableName());
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet result =ps.executeQuery();
            List<T> listOfThings=new ArrayList<>();
            while (result.next()){
                newClass = mapResultToObject(result,conn);
                listOfThings.add(newClass);
            }
            LOGGER.info("All the elements were added to the list successfully");
            return listOfThings;
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);

        }
        finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public T findById(long id) throws SQLException {
        try {
            if (id==0l){
                LOGGER.info("The id must be greater than 0L, returning a null value");
                return null;
            }
            conn.setAutoCommit(false);
            String query= String.format("select * from %s where id=?",getTableName());
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,id);

            ResultSet result =ps.executeQuery();
            if (!result.next()){
                LOGGER.info("The value can not be found, returning a null value");
                return null;
            }

            T newClass = mapResultToObject(result ,conn);
            LOGGER.info("The object was found successfully");
        return newClass;

        } catch (SQLException e) {
            conn.rollback();
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void deleteById(Long thingId) throws SQLException {
        try {
            T thingToDelete=findById(thingId);
            conn.setAutoCommit(false);
            if (thingToDelete==null) {
                LOGGER.info("The object does not exist, delete operation failed");
                return;
            }
            String query=String.format("delete from %s where id=?",getTableName()) ;
            PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,thingId);
            int rowNumber= ps.executeUpdate();
            LOGGER.info("The selected object was deleted successfully row affected: "+rowNumber);
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }
        finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void delete(T thingToDelete) {
        try {
            Long idOfObject=getThingId(thingToDelete);
            deleteById(idOfObject);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T thingToUpdate) throws SQLException {
        try {
            conn.setAutoCommit(false);
            T checkIfExist=findById(getThingId(thingToUpdate));
            conn.setAutoCommit(false);
            if (checkIfExist==null) {
                LOGGER.info("The object does not exist, update operation failed");
                return;
            }
            PreparedStatement ps = conn.prepareStatement(getUpdateQuery(thingToUpdate),Statement.RETURN_GENERATED_KEYS);
            int number= ps.executeUpdate();
            LOGGER.info("The object was updated successfully the row affected was: "+number);
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }
        finally {
            conn.setAutoCommit(true);
        }
    }

    protected abstract String getUpdateQuery(T newThingToUpdate);


    /*Return the table name */
    protected abstract String getTableName();

    /*Return the table fields in the order that we want to show it or use it it must be separated by
    * commas and lower case
    * */
    protected abstract String getTableFields();

    /*Return the field values of the object that we want to add to the attributes in the new row of the table.
    they must be separated by comma in one string*/
    //protected abstract String getThingFields(T thing);

    protected abstract Long getThingId(T thing);

    protected abstract T mapResultToObject(ResultSet resultSet,Connection conn) throws SQLException;

    protected abstract String getCreateQuery();

    protected abstract void setQueryStatements(PreparedStatement ps,T thingToCreate);
}
