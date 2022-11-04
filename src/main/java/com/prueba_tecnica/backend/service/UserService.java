package com.prueba_tecnica.backend.service;


import com.prueba_tecnica.backend.model.Request.UserRequest;
import com.prueba_tecnica.backend.model.Rol;
import com.prueba_tecnica.backend.model.User;
import com.prueba_tecnica.backend.repository.RolRepository;
import com.prueba_tecnica.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service  @Transactional @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Map<String,?>> getUsers() {
        return userRepository.getAllUsers();
    }

    public Optional<User> findUser(UserRequest model) {
        return userRepository.findUserByLogin(model.getLogin());
    }

    public void addNewUser(UserRequest user) {
        Optional<User> userOptional = userRepository.findUserByLogin(user.getLogin());

        if (userOptional.isPresent()) {
            throw  new IllegalStateException("login taken");
        }

        if (user.getEmail() == null || user.getEmail().length() < 5) {
            throw  new IllegalStateException("invalid email");
        }

        if (user.getPassword() == null || user.getPassword().length() < 2 || user.getPassword().length() > 10 ) {
            throw  new IllegalStateException("invalid password");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // valid roll id
        if (user.getRol_id() == null) {
            throw  new IllegalStateException("invalid roll id");
        }

        Optional<Rol> rol = rolRepository.findById(user.getRol_id());
        // valid if exists roll id
        if (!rol.isPresent()) {
            throw  new IllegalStateException("Roll id does not exist");
        }

        User modelUser = new User(
            user.getName(),
            user.getDate_of_birth(),
                user.getPhone(),
                user.getAddress(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                rol.get()
        );

        userRepository.save(modelUser);

    }

    public void updateUser(UserRequest model) {
        Optional<User> userOptional = userRepository.findById(model.getId());

        if (!userOptional.isPresent()) {
            throw  new IllegalStateException("user does not exist");
        }

        Optional<User> anotherLogin = userRepository.existsAnotherLogin(model.getLogin(), model.getId());

        if (anotherLogin.isPresent()) {
            throw new IllegalStateException("login already exists in another user");
        }

        if (model.getPassword() == null) {
            model.setPassword(userOptional.get().getPassword());
        }

        if (model.getPassword().length() < 2) {
            throw new IllegalStateException("invalid passwrod");
        }

        if (model.getLogin() == null || model.getLogin().length() < 2) {
            throw new IllegalStateException("invalid login");
        }

        // valid roll id
        if (model.getRol_id() == null) {
            throw  new IllegalStateException("invalid roll id");
        }

        Optional<Rol> rol = rolRepository.findById(model.getRol_id());
        // valid if exists roll id
        if (!rol.isPresent()) {
            throw  new IllegalStateException("Roll id does not exist");
        }

        User updatedUser = userOptional.get();
        updatedUser.setName(model.getName());
        updatedUser.setDateOfBirth(model.getDate_of_birth());
        updatedUser.setAge(Period.between(model.getDate_of_birth(), LocalDate.now()).getYears());
        updatedUser.setPhone(model.getPhone());
        updatedUser.setAddress(model.getAddress());
        updatedUser.setEmail(model.getEmail());
        updatedUser.setLogin(model.getLogin());
        updatedUser.setPassword(model.getPassword());
        updatedUser.setRol(rol.get());

        userRepository.save(updatedUser);
    }

    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new IllegalStateException("user id does not exist");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("The user does not exist");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getPassword(), authorities);
    }
//    public void addNewStudent(Student student) {
//        Optional<Student> studentOptional = studentRepository.findStudentByEmail((student.getEmail()));
//
//        if (studentOptional.isPresent()) {
//            throw  new IllegalStateException("email taken");
//        }
//
//        studentRepository.save(student);
//    }
//
//    public void deleteStudent(Long studentId) {
//        boolean exists = studentRepository.existsById(studentId);
//
//        if (!exists) {
//            throw new IllegalStateException("student with id " + studentId + " does not exists");
//        }
//
//        studentRepository.deleteById(studentId);
//    }
//
//    @Transactional
//    public void updateStudent(Long studentId, String name, String email) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));
//
//        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
//            student.setName(name);
//        }
//
//        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
//            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
//
//            if (studentOptional.isPresent()) {
//                throw new IllegalStateException("email taken");
//            }
//
//            student.setEmail(email);
//        }
//    }
}
