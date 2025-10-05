/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repozitorijum;

/**
 *
 * @author damja
 */
public interface DbRepozitorijum<T> extends Repozitorijum<T> {
    default public void connect() throws Exception{
        DbConnection.getInstance().getConnection();
    }
    default public void disconnect() throws Exception{
        DbConnection.getInstance().getConnection().close();
    }
    default public void commit() throws Exception{
        DbConnection.getInstance().getConnection().commit();
    }
    default public void rollback() throws Exception{
        DbConnection.getInstance().getConnection().rollback();
    }
}
