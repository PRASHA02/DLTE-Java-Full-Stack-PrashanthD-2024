//package element.spring.boot.springboot.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UsersServices implements UserDetailsService {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//
//    public Users signingUp(Users users){
//        int ack = jdbcTemplate.update("insert into users values(?,?,?,?,?,?)",new Object[]{
//               users.getUsername(),users.getPassword(),users.getRole(), users.getAddress(),users.getContact(),users.getEmail()
//        });
//        return users;
//    }
//
//    public Users findByUsername(String username){
//        Users users = jdbcTemplate.queryForObject("select * from users where username=?",
//                new Object[]{username},new BeanPropertyRowMapper<>(Users.class));
//        return users;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users officials = findByUsername(username);
//        if(officials==null)
//            throw new UsernameNotFoundException(username);
//        return officials;
//    }
//}
