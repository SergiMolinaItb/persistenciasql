package cat.itb.projecte.model.servei;

import cat.itb.projecte.model.entitat.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElMeuUserDetailsService implements UserDetailsService {

    @Autowired
    private ServeiUsuaris serveiUsuaris;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuari u= serveiUsuaris.consultaPerId(s);
        System.out.println(u);
        User.UserBuilder builder=null;
        if(u!=null){
            builder=User.withUsername(s);
            builder.disabled(false);
            builder.password(u.getPassword());

            builder.roles(u.getRol());
        }
        else throw new UsernameNotFoundException("Usuari no trobat");
        return builder.build();
    }
}

