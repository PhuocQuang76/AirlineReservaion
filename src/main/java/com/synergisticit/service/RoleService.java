package com.synergisticit.service;

import com.synergisticit.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public Role save(Role role);

    public List<Role> findAll();

    public Role findById(Long roleId);

    public void deleteById(Long roleId);

    public Role update(Role role);
}
