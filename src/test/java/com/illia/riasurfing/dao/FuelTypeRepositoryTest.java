package com.illia.riasurfing.dao;

import com.illia.riasurfing.helpers.MethodHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:application-test.properties"})
@ActiveProfiles("test")
public class FuelTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired FuelTypeRepository fuelTypeRepository;

    @Test
    public void testIfRecordExistsByValueReturnTrue() {
        //given
        prepareData();
        //when
        final boolean b = fuelTypeRepository.existsByValue(MethodHelper.fuelTypesList2().get(0).getValue());
        //then
        assertTrue(b);
    }

    @Test
    public void testIfRecordExistsByValueReturnFalse() {
        //given
        prepareData();
        //when
        final int incorrectValue = 0;
        final boolean b = fuelTypeRepository.existsByValue(incorrectValue);
        //then
        assertFalse(b);
    }

    private void prepareData() {
        MethodHelper.fuelTypesList2().forEach(item -> entityManager.persist(item));
        entityManager.flush();
    }

}
