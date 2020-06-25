package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.State;
import com.illia.riasurfing.helpers.MethodHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:application-test.properties"})
@ActiveProfiles("test")
public class StateRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void testFindByValueReturnState() {
        //given
        prepareData();
        //when
        final State state = MethodHelper.statesList2().get(0);
        final State state1 = stateRepository.findByValue(state.getValue()).get();
        //then
        assertThat(state1.getName()).isEqualTo(state.getName());
        assertThat(state1.getValue()).isEqualTo(state.getValue());
        assertThat(state1.getCities()).isNull();
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByValueReturnNull() {
        //given
        prepareData();
        //when
        final int incorrectValue = 0;
        stateRepository.findByValue(incorrectValue).get();
    }

    private void prepareData() {
        MethodHelper.statesList2().forEach(item -> entityManager.persist(item));
        entityManager.flush();
    }
}
