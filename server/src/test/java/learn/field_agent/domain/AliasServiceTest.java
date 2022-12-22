package learn.field_agent.domain;

import learn.field_agent.data.AgentRepository;
import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.LocationRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {
    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @MockBean
    AgentRepository agentRepository;



    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias mockOut = makeAlias();
        mockOut.setAliasId(1);

        when(repository.add(alias)).thenReturn(mockOut);

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

   @Test
    void shouldNotAddWhenInvalid() {
        // Should not add `null`.
        Result<Alias> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if agent doesn't exist.
        Alias alias = makeAlias();
        alias.setAliasId(0);
        // Mocking the agent repository findById method to return null is not strictly necessary
        // as the default mock implementation for that method will return null.
        // Adding here for clarity on what our expectations are.
        /*when(agentRepository.findById(1)).thenReturn(null);
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());*/

        // Make sure that an agent is found by default for the remaining test cases.
        Agent agent = new Agent();
        when(agentRepository.findById(1)).thenReturn(agent);

        // Should not add if aliasId is greater than 0.
        alias = makeAlias();
        alias.setAliasId(1);
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if name is null.
        alias = makeAlias();
        alias.setAliasId(0);
        alias.setName(null);
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if name is empty.
        alias = makeAlias();
        alias.setAliasId(0);
        alias.setName("");
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if agent ID is not set.
        alias = makeAlias();
        alias.setAliasId(0);
        alias.setAgentId(0);
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        // Make sure that an existing alias with the same name is found.
        Alias shadow = makeAlias();
        shadow.setAliasId(0);
        service.add(shadow);
        when(repository.findByName("Shadow")).thenReturn(List.of(shadow));

        // Should not add if a duplicate alias name is found and the persona is null.
        alias = makeAlias();
        alias.setAliasId(0);
        alias.setPersona(null);
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if a duplicate alias name is found and the persona is empty.
        alias = makeAlias();
        alias.setAliasId(0);
        alias.setPersona("");
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add if a duplicate alias name is found and the persona is not unique.
        shadow.setPersona("A shadow in the night.");
        alias = makeAlias();
        alias.setAliasId(0);
        alias.setPersona("A shadow in the night.");
        result = service.add(alias);
        assertEquals(ResultType.INVALID, result.getType());

        alias = makeAlias();
        alias.setName("   ");


        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
        alias.setAliasId(1);

        when(repository.update(alias)).thenReturn(true);

        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }
    @Test
    void shouldNotUpdateWhenInvalid() {
        Alias alias = makeAlias();
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        alias.setAliasId(1);
        alias.setName("");
        actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }


    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Test Alias");
        alias.setPersona("Test Persona");
        alias.setAgentId(1);
        return alias;
    }


}