package element.spring.boot.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Users signingUp(Users myBankOfficials){
        int ack = jdbcTemplate.update("insert into my_bank_officials values(?,?,?,?,?,?)",new Object[]{
                myBankOfficials.getUsername(),
                myBankOfficials.getPassword(),
                myBankOfficials.getRole(),
                myBankOfficials.getAddress(),
                myBankOfficials.getContact(),
                myBankOfficials.getEmail()
        });
        return myBankOfficials;
    }
    public Users findByUsername(String username){
        Users myBankOfficials = jdbcTemplate.queryForObject("select * from users where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(Users.class));
        return myBankOfficials;
    }
    public void updateAttempts(Users myBankOfficials){
        jdbcTemplate.update("update users set attempts=? where username=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});

    }

    public void updateStatus(Users myBankOfficials){
        jdbcTemplate.update("update users set status=0 where username=?",
                new Object[]{myBankOfficials.getUsername()});

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}
