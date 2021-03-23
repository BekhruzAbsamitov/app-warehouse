package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Result edit(Integer id, ClientDto clientDto) {
        final Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return new Result("Client not found", false);
        }
        final Client client = optionalClient.get();
        if (clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber())) {
            return  new Result("Phone number already exists", false);
        }
        client.setPhoneNumber(client.getPhoneNumber());
        client.setName(clientDto.getName());

        clientRepository.save(client);
        return new Result("Client edited", true);

    }

    public Result delete(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return new Result("Client Deleted", true);
        }
        return new Result("Client not found", false);
    }

    public Result add(ClientDto clientDto) {
        Client client = new Client();
        if (clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber())) {
            return  new Result("Phone number already exists", false);
        }
        client.setPhoneNumber(client.getPhoneNumber());
        client.setName(clientDto.getName());

        clientRepository.save(client);
        return new Result("Client saved", true);
    }

    public List<Client> get() {
        return clientRepository.findAll();
    }

    public Client getById(Integer id) {
        final Optional<Client> optionalClient =
                clientRepository.findById(id);

        if (optionalClient.isEmpty()) {
            return null;
        }
        return optionalClient.get();
    }
}
