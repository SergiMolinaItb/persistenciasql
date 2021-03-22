package cat.itb.projecte.model.servei;

import cat.itb.projecte.model.entitat.Empleat;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class EmpleatService {
    private List<Empleat> repositori = new ArrayList<>();
    public void afegir(Empleat e) {
        repositori.add(e);
    }
    public List<Empleat> llistat() {
        repositori.sort(new Comparator<Empleat>() {
            @Override
            public int compare(Empleat empleat, Empleat t1) {
                return empleat.getId()-t1.getId();
            }
        });
        return repositori;
    }
    public List<Empleat> llistatOrdenatPerNom(){
        repositori.sort(new Comparator<Empleat>() {
            @Override
            public int compare(Empleat empleat, Empleat t1) {
                return empleat.getNom().compareToIgnoreCase(t1.getNom());
            }
        });
        return repositori;
    }
    public Empleat consultaPerId(int id){
        for (int i = 0;i<repositori.size();i++){
            if (repositori.get(i).getId()==id){
                return repositori.get(i);
            }
        }
        return new Empleat(0,"No existeix",null,null,false);
    }
    public void eliminarPerId(int id){
        for (int i = 0;i<repositori.size();i++){
            if (repositori.get(i).getId()==id){
                repositori.remove(repositori.get(i));
            }
        }
    }
    public void substituir(Empleat e){
        for (int i = 0;i<repositori.size();i++){
            if (repositori.get(i).getId()==e.getId()){
                repositori.get(i).setNom(e.getNom());
                repositori.get(i).setEmail(e.getEmail());
                repositori.get(i).setTelefon(e.getTelefon());
                repositori.get(i).setDirectiu(e.isDirectiu());
            }
        }
    }
    @PostConstruct
    public void init() {
        repositori.addAll(
                Arrays.asList(
                        new Empleat(1, "Montse Madridejos", "montse@itb.cat", "677123456",true),
                        new Empleat(2, "Alberto Vila", "alberto@itb.cat", "699876543",false),
                        new Empleat(3, "Robert López", "robert@bbc.com", "123456789",false)
                )
        );
    }
}
