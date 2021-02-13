package com.example.backend.constant;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(Sets.newHashSet(UserPermission.COURSE_READ, UserPermission.COURSE_WRITE, UserPermission.STUDENT_READ, UserPermission.STUDENT_WRITE)),
    USER(Sets.newHashSet()),
    ADMINTRAINEE(Sets.newHashSet(UserPermission.COURSE_READ, UserPermission.STUDENT_READ));
    private final Set<UserPermission> permissionSet;

    UserRole(Set<UserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<UserPermission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthoritySet = getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return grantedAuthoritySet;
    }
}
