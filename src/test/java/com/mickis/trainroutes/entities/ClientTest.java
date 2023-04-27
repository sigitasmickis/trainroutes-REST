package com.mickis.trainroutes.entities;

import com.mickis.trainroutes.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void whenClientCreated_itIsPersisted() {
        var client = new Client("min", "min","email@email.com");
        var savedClient = clientRepository.save(client);
        var persistedClient = entityManager.find(Client.class, savedClient.getId());
        assertEquals(savedClient, persistedClient);
    }

    @Test
    public void whenReadingClient_theyAreFoundByName() {
        var client = new Client("min", "min","email@email.com");
        var persistedClient = entityManager.persist(client);
        var foundClient = clientRepository.findByName("min");
        assertEquals(persistedClient, foundClient);
    }

}