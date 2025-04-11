package test1.demo1.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import test1.demo1.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    @PostConstruct
    private void init() {
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_ALL_CUSTOMERS")
                .returningResultSet("DATAOUTPUT", new CustomerRowMapper());
    }

    public List<Customer> getAllCustomers() {
        Map<String, Object> result = simpleJdbcCall.execute();
        return (List<Customer>) result.get("DATAOUTPUT");
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer c = new Customer();
            c.setId(rs.getLong("CUS_ID"));
            c.setFirstName(rs.getString("FIRST_NAME"));
            c.setLastName(rs.getString("LAST_NAME"));
            c.setSalary(rs.getDouble("SALARY"));
            c.setDob(rs.getDate("DOB"));
            c.setGender(rs.getString("GENDER"));
            c.setMobilePhone(rs.getString("MOBILE_PHONE"));
            return c;
        }
    }
}
