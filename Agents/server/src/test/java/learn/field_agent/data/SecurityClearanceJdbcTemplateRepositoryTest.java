package learn.field_agent.data;

import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    final static int NEXT_SECURITY_CLEARANCE_ID = 3;
    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(4);
        assertEquals(null, actual);
    }
    @Test
    void shouldAdd(){
        SecurityClearance securityClearance = makeSecurityClearance();
        SecurityClearance actual = repository.add(securityClearance);
        assertNotNull(actual);
        assertEquals(NEXT_SECURITY_CLEARANCE_ID, actual.getSecurityClearanceId());
    }
    @Test
    void shouldUpdate(){
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(2);
        assertTrue(repository.update(securityClearance));
        securityClearance.setSecurityClearanceId(10);
        assertFalse(repository.update(securityClearance));

    }
    @Test
    void shouldDelete(){

    }
    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Test Security Clearance");
        return securityClearance;
    }
}