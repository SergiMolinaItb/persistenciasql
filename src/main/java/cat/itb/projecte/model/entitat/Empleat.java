package cat.itb.projecte.model.entitat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleat {
    private int id;
    private String nom;
    private String email;
    private String telefon;
    private boolean directiu;
}
