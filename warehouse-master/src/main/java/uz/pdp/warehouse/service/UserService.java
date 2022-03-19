package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User, User> {
    
    private final UserRepository userRepository;

    @Override
    public Result add(User user) {
        if(userRepository.existsByPhoneNumber(user.getPhoneNumber()))
            return new Result("This user already exists", false);

        userRepository.save(user);
        return new Result("User successfully added", true);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllByActiveIsTrue();
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findByIdAndActiveIsTrue(id);
        if(optionalUser.isEmpty())
            return new Result("User not found", false);

        User editingUser = optionalUser.get();
        editingUser.setFirstName(user.getFirstName());
        editingUser.setLastName(user.getLastName());
        editingUser.setCode(user.getCode());
        editingUser.setPassword(user.getPassword());
        editingUser.setPhoneNumber(user.getPhoneNumber());
        editingUser.setWarehouses(user.getWarehouses());
        userRepository.save(editingUser);

        return new Result("User successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<User> optionalUser = userRepository.findByIdAndActiveIsTrue(id);
        if(optionalUser.isEmpty())
            return new Result("User not found", false);
        User deletingUser = optionalUser.get();
        deletingUser.setActive(false);
        userRepository.save(deletingUser);
        return new Result("User successfully deleted", true);
    }
}
