package org.sam.curso.springboot.error.springboot_error.models.domain;

public class User {

    private Long id;
    private String name;
    private String lasname;
    private Role role;

    public User() {
    }

    public User(Long id, String name, String lasname) {
        this.id = id;
        this.name = name;
        this.lasname = lasname;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLasname() {
        return lasname;
    }
    
    public void setLasname(String lasname) {
        this.lasname = lasname;
    }

    public Role getRole() {
        return role;
    }

    //public String getRoleName() {
    //    return role.getName();
    //}

    public void setRole(Role role) {
        this.role = role;
    }

}
