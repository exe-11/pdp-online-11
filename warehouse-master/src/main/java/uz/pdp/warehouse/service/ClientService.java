package uz.pdp.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements BaseService<Client, Client> {
    
    private final ClientRepository clientRepository;

    @Override
    public Result add(Client client) {
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("This client already exists", false);
        clientRepository.save(client);
        return new Result("Client successfully added", true);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAllByActiveIsTrue();
    }

    @Override
    public Client getOne(Integer id) {
        return clientRepository.findByIdAndActiveIsTrue(id).orElse(null);
    }

    @Override
    public Result edit(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findByIdAndActiveIsTrue(id);
        if(optionalClient.isEmpty())
            return new Result("Client not found", false);

        Client editingClient = optionalClient.get();
        editingClient.setName(client.getName());
        editingClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editingClient);

        return new Result("Client successfully edited", true);
    }

    @Override
    public Result delete(Integer id) {
        Optional<Client> optionalClient = clientRepository.findByIdAndActiveIsTrue(id);
        if(optionalClient.isEmpty())
            return new Result("Client not found", false);
        Client deletingClient = optionalClient.get();
        deletingClient.setActive(false);
        clientRepository.save(deletingClient);

        return new Result("Client successfully deleted", true);
    }
}
