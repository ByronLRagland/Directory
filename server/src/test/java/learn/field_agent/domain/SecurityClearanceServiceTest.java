package learn.field_agent.domain;

import learn.field_agent.data.LocationRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;


    @Test
    void shouldFindSecret() {
        SecurityClearance expected = makeSecurityClearance();
        when(repository.findById(1)).thenReturn(expected);
        SecurityClearance actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(0);
        SecurityClearance mockOut = makeSecurityClearance();
        mockOut.setSecurityClearanceId(1);

        when(repository.add(securityClearance)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }
    @Test
    void shouldNotAddWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setName("   ");

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(1);

        when(repository.update(securityClearance)).thenReturn(true);

        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }
    @Test
    void shouldNotUpdateWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(1);
        securityClearance.setName("");
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Secret");
        securityClearance.setSecurityClearanceId(1);
        return securityClearance;
    }
}