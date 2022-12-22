package learn.field_agent.data;

import learn.field_agent.models.AgencyAgent;
import org.springframework.transaction.annotation.Transactional;

public interface AgencyAgentRepository {
    boolean add(AgencyAgent agencyAgent);

    boolean update(AgencyAgent agencyAgent);

    boolean deleteByKey(int agencyId, int agentId);
}
