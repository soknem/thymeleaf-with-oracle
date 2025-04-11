package test1.demo1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import test1.demo1.model.Customer;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<Customer> getAllCustomers() {

        Map<String, Object> result = simpleJdbcCall.execute();
        return (List<Customer>) result.get("p_cursor");
    }

}
