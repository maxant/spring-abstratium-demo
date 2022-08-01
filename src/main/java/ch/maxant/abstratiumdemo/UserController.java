package ch.maxant.abstratiumdemo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/users") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyService myService;

    @Autowired
    private MyOtherService myOtherService;

    @Autowired
    Logger log;

    @PersistenceContext
    private EntityManager em;

    @PostMapping(path = "/add")
    @Transactional(propagation = Propagation.REQUIRED)
    @Secured("ROLE_ADMIN") // Note how we need to prepend `ROLE_` here! search for EnableGlobalMethodSecurity in this code base
    public User addNewUser(@RequestParam String name) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        log.info("user is " + securityContext.getAuthentication().getName());
        User u = new User();
        u.setName(name + "-" + myService.getRandomLong());
        u.setModified(LocalDateTime.now());
        u = userRepository.save(u);
        return u;
    }

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        myOtherService.executeSomeLogic();
        return userRepository.findAll();
    }

    @GetMapping(path = "/find/name-like/{name}")
    public Iterable<User> findWithNameLike(@PathVariable("name") String name) {
        return userRepository.findWithNameLike("%" + name + "%");
    }

    @GetMapping(path = "/find/name/{name}")
    public Iterable<User> findByName(@PathVariable("name") String name) {
        return userRepository.findByNameAndModifiedNotNull(name);
    }

    @GetMapping(path = "/count")
    @Transactional(propagation = Propagation.REQUIRED)
    public long countUsers() {
        // using entity manager rather than repo
        return (long) em.createQuery("select count(*) from User u").getSingleResult();
    }

    @PutMapping(path = "/update/all")
    @Transactional(propagation = Propagation.REQUIRED)
    public int udpateAll() {
        return userRepository.updateAll(LocalDateTime.now());
    }

}