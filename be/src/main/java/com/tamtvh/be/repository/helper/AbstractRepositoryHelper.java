package com.tamtvh.be.repository.helper;

import javax.persistence.EntityManager;

public interface AbstractRepositoryHelper <E, ID> {

    default EntityManager getEntityManagerClass() {
        return AbstractRepositoryCustomTool.getEntityManager();
    }
}
