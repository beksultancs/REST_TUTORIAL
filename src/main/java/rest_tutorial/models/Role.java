package rest_tutorial.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Beksultan
 */
public enum Role implements GrantedAuthority {
    TEACHER,
    STUDENT;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
