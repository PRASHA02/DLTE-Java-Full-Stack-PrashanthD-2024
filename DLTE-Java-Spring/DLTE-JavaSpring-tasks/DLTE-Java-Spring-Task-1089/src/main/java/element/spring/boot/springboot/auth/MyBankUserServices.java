package element.spring.boot.springboot.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankUserServices implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public MyBankUsers signingUp(MyBankUsers myBankUsers){
        int ack = jdbcTemplate.update("insert into mybank_users values(?,?,?,?,?,?)",new Object[]{
                myBankUsers.getName(),myBankUsers.getUsername(),
                myBankUsers.getPassword(),myBankUsers.getEmail(),
                myBankUsers.getContact(),myBankUsers.getAadhaar()
        });
        return myBankUsers;
    }

    public  MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from mybank_users where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}
