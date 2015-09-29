package com.epam.dao;

import com.epam.errors.DaoException;
import com.epam.models.LikeModel;

import java.util.List;

/**
 * User: Dimitr
 * Date: 29.09.2015
 * Time: 13:47
 */
public interface ILikesDao {
    void insert(LikeModel entity) throws DaoException;

    LikeModel find(LikeModel entity) throws DaoException;

    List<LikeModel> findAllByFbId(String facebookId) throws DaoException;
}
