package cat.itb.projecte.controlador;


import cat.itb.projecte.model.entitat.Empleat;
import cat.itb.projecte.model.servei.EmpleatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorEmpleats {

    @Autowired
    private EmpleatService servei;

    @GetMapping("/empleats/listPerName")
    public String llistarPerNom(Model m){
        m.addAttribute("llistaEmpleats",servei.llistatOrdenatPerNom());
        return "llistat";
    }

    @GetMapping("/empleats/list")
    public String llistar(Model m){
        m.addAttribute("llistaEmpleats",servei.llistat());
        return "llistat";
    }

    @GetMapping("/empleats/new")
    public String afegirEmpleat(Model m){
        m.addAttribute("empleatForm",new Empleat());
        return "afegir";
    }

    @GetMapping("/empleats/edit")
    public String afegirEmpleat(@RequestParam(value = "id",required = false) int id,Model m){
        Empleat nou = new Empleat();
        nou.setId(id);
        m.addAttribute("empleatForm",nou);
        m.addAttribute("id", id);
        return "afegir";
    }

    @PostMapping("empleats/new/submit")
    public String afegirSubmit(@ModelAttribute("empleatForm") Empleat emp){
      servei.afegir(emp);
      return "redirect:/empleats/list";
    }

    @PostMapping("empleats/edit/submit")
    public String editSubmit(@ModelAttribute("empleatForm") Empleat emp){
        servei.substituir(emp);
        return "redirect:/empleats/list";
    }

    @GetMapping("empleats/eliminar")
    public String eliminarEmpleat(@RequestParam(value = "id",required = false) int id,Model m){
        servei.eliminarPerId(id);
        return "redirect:/empleats/list";
    }
}
