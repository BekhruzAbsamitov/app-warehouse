package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.ClientDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Result add(@RequestBody ClientDto clientDto) {
        return clientService.add(clientDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return clientService.delete(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody ClientDto clientDto) {
        return clientService.edit(id, clientDto);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @GetMapping
    public List<Client> get() {
        return clientService.get();
    }
}
