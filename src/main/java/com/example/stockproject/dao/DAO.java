package com.example.stockproject.dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
    protected Connection conn;

    public DAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Methode de création dans la BDD
     * @param obj : objet à créer dans la BDD
     * @return bool:
     * true: confirme la création d'objet en BDD
     * false: indique que l'objet n'a pas été créée en BDD
     */
    public abstract boolean create(T obj);

    /**
     * Methode de déletion dans la BDD
     * @param obj : Objet à supprimer dans la BDD
     * @return bool:
     * true: confirme la déletion de l'objet en BDD
     * false: indique que l'objet n'a pas été supprimé en BDD
     */
    public abstract boolean delete(T obj);

    /**
     * Methode de maj dans la BDD
     * @param obj : Objet à modifier dans la BDD
     * @return
     */
    public abstract boolean update(T obj);

    /**
     * Methode de recherche pour des objets dans la DB
     * @param id : index de l'élément à chercher
     * @return
     */
    public abstract T find(int id);
    public abstract List<T> findall();
}
