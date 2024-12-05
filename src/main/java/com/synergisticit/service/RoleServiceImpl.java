package com.synergisticit.service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if(role.isPresent()){
            return role.get();
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
}
