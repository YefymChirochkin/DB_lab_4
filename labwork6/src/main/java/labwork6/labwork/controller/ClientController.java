package labwork6.labwork.controller;


import labwork6.labwork.domain.Client;
import labwork6.labwork.dto.ClientDto;
import labwork6.labwork.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/clients")
@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)

    public ResponseEntity<List<ClientDto>> getAll() {
        List<Client> clients = clientService.getAll();
        List<ClientDto> clientDtos = new ArrayList<>();
        for (Client client : clients) {
            ClientDto clientDto = new ClientDto(
                    client.getClient_id(),
                    client.getAge(),
                    client.getName(),
                    client.getSurname()
            );
            clientDtos.add(clientDto);
        }
        return new ResponseEntity(clientDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Integer id) {
        Client clientOld = clientService.getById(id);
        if (clientOld != null) {
            ClientDto clientDto = new ClientDto(
                    clientOld.getClient_id(),
                    clientOld.getAge(),
                    clientOld.getName(),
                    clientOld.getSurname()
            );
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Client newClient) {
        clientService.create(newClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClientDto> update(@PathVariable Integer id,
                                             @RequestBody Client client) {
        Client clientOld = clientService.getById(id);
        if (clientOld != null) {
            clientService.update(id, client);
            ClientDto clientOldDto = new ClientDto(
                    client.getClient_id(),
                    client.getAge(),
                    client.getName(),
                    client.getSurname()
            );
            return new ResponseEntity<>(clientOldDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (clientService.getById(id) != null) {
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
