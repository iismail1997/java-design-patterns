package com.iluwater.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.iluwatar.dao.factory.DAO;
import com.iluwatar.dao.factory.DaoFactory;
import com.iluwatar.dao.factory.DaoType;
import com.iluwatar.dao.factory.DynamoDBProvider;
import com.iluwatar.dao.factory.ReadOnlyDAOImpl;
import com.iluwatar.dao.factory.ReadWriteDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DaoFactoryTest {
    private static final AmazonDynamoDB dynamoDB = DynamoDBProvider.getDynamoDB();

    @Test
    public void getReadOnlyDao() {
        DAO readOnlyDao = DaoFactory.getDAO(DaoType.ReadOnly, dynamoDB);

        assertEquals(ReadOnlyDAOImpl.class, readOnlyDao.getClass());
    }

    @Test
    public void getReadWriteDao() {
        DAO readWriteDao = DaoFactory.getDAO(DaoType.ReadWrite, dynamoDB);

        assertEquals(ReadWriteDAOImpl.class, readWriteDao.getClass());
    }

    @Test
    public void getUnknownDao() {
        DAO unknownDao = DaoFactory.getDAO(null, dynamoDB);

        assertNull(unknownDao);
    }
}
