package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.macieksob.rentCar.dto.RoleDTO;
import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    private RoleDTO mapToDTO(Role role){
        RoleDTO map = modelMapper.map(role, RoleDTO.class);
        return map;
    }

    private Role mapToEntity(RoleDTO roleDTO){
        Role role = modelMapper.map(roleDTO, Role.class);
        return role;
    }

    public Role addRole(RoleDTO role){
        Role role1 = mapToEntity(role);

        return roleRepository.save(role1);
    }

    public void deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }

    public void deleteRole(RoleDTO role){
        Role role1 = mapToEntity(role);
        roleRepository.delete(role1);
    }

    public List<RoleDTO> getRoles(){
        return roleRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Role editRole(Long id, RoleDTO newRole){
        Role role = roleRepository.getById(id);

        role.setId(newRole.getId());
        role.setName(newRole.getName());
        role.setUsers(newRole.getUsers());

        return roleRepository.save(role);
    }


}
