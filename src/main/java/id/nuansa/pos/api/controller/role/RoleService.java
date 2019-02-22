package id.nuansa.pos.api.controller.role;

import id.nuansa.pos.api.model.request.AddRoleRequest;
import id.nuansa.pos.entity.Role;

public interface RoleService {

    Role add(AddRoleRequest request);

    Role update(String secureId, AddRoleRequest request);

    Role getOne(String secureId);

    Role getOneByName(String name);
}
