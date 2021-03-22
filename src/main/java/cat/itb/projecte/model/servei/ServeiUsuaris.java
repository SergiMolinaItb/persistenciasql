package cat.itb.projecte.model.servei;

import cat.itb.projecte.model.entitat.Usuari;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServeiUsuaris {

    ArrayList<Usuari> usuaris = new ArrayList<>();

    public Usuari consultaPerId(String s){
        for (Usuari usuari : usuaris) {
            if (usuari.getUsername().equals(s)) {
                return usuari;
            }
        }
      return new Usuari();
    }

    public void afegir(Usuari e) {
        usuaris.add(e);
    }

    public List<Usuari> llistat() {
        return usuaris;
    }

    @PostConstruct
    public void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuaris.addAll(
                Arrays.asList(
                        new Usuari("user", passwordEncoder.encode("user"), "USER"),
                        new Usuari("admin", passwordEncoder.encode("admin"), "ADMIN")
                        ));
    }
}
