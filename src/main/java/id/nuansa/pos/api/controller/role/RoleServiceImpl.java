package id.nuansa.pos.api.controller.role;

import id.nuansa.pos.api.model.request.AddRoleRequest;
import id.nuansa.pos.entity.Role;
import id.nuansa.pos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(AddRoleRequest request) {
        Role role = new Role();
        role.setSecureId(UUID.randomUUID().toString());
        role.setName(request.getName());
        role.setCreatedAt(new Date());
        role.setUpdatedAt(new Date());

        return roleRepository.save(role);
    }

    @Override
    public Role update(String secureId, AddRoleRequest request) {
        Role role = getOne(secureId);
        if (null == role) return null;

        role.setName(request.getName());
        role.setUpdatedAt(new Date());

        return roleRepository.save(role);
    }

    @Override
    public Role getOne(String secureId) {
        return roleRepository.findBySecureId(secureId);
    }

    @Override
    public Role getOneByName(String name) {
        return roleRepository.findByName(name);
    }
}
