use field_agent_test;
-- disable safe updates
set sql_safe_updates = 0;
call set_known_good_state();
-- enable safe updates
set sql_safe_updates = 1;