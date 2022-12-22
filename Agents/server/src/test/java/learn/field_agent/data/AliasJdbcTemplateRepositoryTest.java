package learn.field_agent.data;

import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {

    final static int NEXT_ALIAS_ID = 1;

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindTommy() {
        Alias actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals(1, actual.getAliasId());
        assertEquals("Test Alias", actual.getName());
    }

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(NEXT_ALIAS_ID, actual.getAliasId());
    }


    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
        alias.setAliasId(1);
        assertTrue(repository.update(alias));
        alias.setAliasId(10);
        assertFalse(repository.update(alias));
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }

    private Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Test Alias");
        alias.setPersona("Test Persona");
        alias.setAgentId(1);
        return alias;
    }
}