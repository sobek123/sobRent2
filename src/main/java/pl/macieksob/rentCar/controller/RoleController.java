package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.RoleDTO;
import pl.macieksob.rentCar.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin("http://localhost:3000")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/newRole")
    public String addRole(@RequestBody @Valid RoleDTO role){
        roleService.addRole(role);

        return "";
    }

    @GetMapping
    public List<RoleDTO> getAllRoles(){
        return roleService.getRoles();
    }

    @PutMapping("/editRole/{id}")
    public String editRole(@PathVariable Long id, @RequestBody RoleDTO newRole){
        roleService.editRole(id, newRole);

        return "";
    }

    @DeleteMapping("/deleteRole/{id}")
    public String deleteRole(@PathVariable Long id){
        roleService.deleteRoleById(id);

        return "";
    }

    @DeleteMapping("/deleteRole")
    public String deleteRole(@RequestBody RoleDTO role){
        roleService.deleteRole(role);

        return "";
    }

    @GetMapping("/{id}")
    public String getRole(@PathVariable Long id){
        roleService.getRole(id);

        return "";
    }
}
