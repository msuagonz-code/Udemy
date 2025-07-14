package org.sam.webapp.servlet.webapp.session.repositories.Impl;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webapp.servlet.webapp.session.configs.Repository;
import org.sam.webapp.servlet.webapp.session.models.entities.Usuario;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;
import org.sam.webapp.servlet.webapp.session.repositories.UsuarioRepository;

import java.util.List;

@Repository
@RepositoryJpa
public class UsuarioRepositoryJpaImpl implements UsuarioRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public Usuario getByUsername(String username) throws Exception {
        return entityManager.createQuery("select u from Usuario u where u.username = :username ", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        return entityManager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario getById(Long id) throws Exception {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        if(usuario.getId() != null && usuario.getId() > 0){
            entityManager.merge(usuario);
        }else{
            entityManager.persist(usuario);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        entityManager.remove(getById(id));
    }
}
