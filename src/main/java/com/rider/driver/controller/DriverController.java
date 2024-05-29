package com.rider.driver.controller;
import com.rider.driver.dtos.DriverDto;
import com.rider.driver.entities.Driver;
import com.rider.driver.entities.DriverStatus;
import com.rider.driver.repositories.DriverRepository;
import com.rider.driver.validators.DriverValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/driver", produces = {"application/json"})
@Tag(name = "driver")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverValidator driverValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(driverValidator);
    }

    @Operation(summary = "Realiza o cadastro de novos drivers", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Driver criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "409", description = "Conflito - o driver já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)

    public DriverDto salvarDriver(@Valid @RequestBody Driver driver) {
        driverRepository.save(driver);
        return new DriverDto(driver);
    }

    @Operation(summary = "Lista todos os drivers existentes no banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drivers listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Nenhum driver encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    public List<DriverDto> listarDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        List<DriverDto> listDriversDto = new ArrayList<>();

        for (Driver driver : drivers) {
            DriverDto driverDto = new DriverDto(driver);
            driverDto.add(linkTo(methodOn(DriverController.class).buscarDriverPorId(driver.getId())).withSelfRel());
            listDriversDto.add(driverDto);
        }
        return listDriversDto;
    }

    @Operation(summary = "Busca um driver pelo seu id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public DriverDto buscarDriverPorId(@PathVariable("id") UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        DriverDto driverDto = new DriverDto(driver);
        driverDto.add(linkTo(methodOn(DriverController.class).atualizarDriver(driver.getId(),driver)).withSelfRel());
        if (driver.getStatus() == DriverStatus.OFFLINE){
            driverDto.add(linkTo(methodOn(DriverController.class).makeAvailableFromOffline(driver.getId())).withRel("Start"));
        }
        if (driver.getStatus() == DriverStatus.AVAILABLE) {
            driver.add(linkTo(methodOn(DriverController.class).makeOfflineFromAvailable(driver.getId())).withRel("Stop"));
            driver.add(linkTo(methodOn(DriverController.class).makeBusyFromAvailable(driver.getId())).withRel("Accept"));
        }
        if (driver.getStatus() == DriverStatus.BUSY) {
            driver.add(linkTo(methodOn(DriverController.class).makeAvailableFromBusy(driver.getId())).withRel("Finish"));
        }

        return driverDto;
    }

    @Operation(summary = "Deletar um driver usando seu id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Driver apagado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deletarDriverPorId(@PathVariable("id") UUID id) {
        driverRepository.findById(id)
                .map(driver -> {
                    driverRepository.deleteById(driver.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @Operation(summary = "Atulizar informações do driver pelo seu id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)

    @Transactional
    public DriverDto atualizarDriver(@PathVariable("id") UUID id, @Valid @RequestBody Driver driverAtualizado) {

        return driverRepository.findById(id)
                .map(driver -> {
                    if (driverAtualizado.getName() != null) {
                        driver.setName(driverAtualizado.getName());
                    }
                    driverRepository.save(driver);
                    return new DriverDto(driver);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @Operation(summary = "Atualizar o status de um driver de Offline para Available pelo seu id", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do driver atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/start/{id}")
    @ResponseStatus(HttpStatus.OK)

    @Transactional
    public DriverDto makeAvailableFromOffline(@PathVariable("id") UUID id) {

        return driverRepository.findById(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.OFFLINE) {
                        driver.setStatus(DriverStatus.AVAILABLE);
                    }
                    driverRepository.save(driver);
                    DriverDto driverDto;
                    driverDto = new DriverDto(driver);
                    driverDto.add(linkTo(methodOn(DriverController.class).makeOfflineFromAvailable(driver.getId())).withRel("Stop"));
                    driverDto.add(linkTo(methodOn(DriverController.class).makeBusyFromAvailable(driver.getId())).withRel("Accept"));
                    return driverDto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @Operation(summary = "Atualizar o status de um driver de Available para Offline pelo seu id", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do driver atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/stop/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public DriverDto makeOfflineFromAvailable(@PathVariable UUID id) {

        return driverRepository.findById(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.AVAILABLE) {
                        driver.setStatus(DriverStatus.OFFLINE);
                    }
                    driverRepository.save(driver);
                    DriverDto driverDto;
                    driverDto = new DriverDto(driver);
                    driverDto.add(linkTo(methodOn(DriverController.class).makeAvailableFromOffline(driver.getId())).withRel("Start"));
                    return driverDto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }

    @Operation(summary = "Atualizar o status de um driver de Available para Busy pelo seu id", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do driver atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/accept/{id}") // Eu deveria lançar uma exception caso o usuario tente ir de busy direto para offline e vice versa? 412
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public DriverDto makeBusyFromAvailable(@PathVariable UUID id) {

        return driverRepository.findById(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.AVAILABLE){
                        driver.setStatus(DriverStatus.BUSY);
                    }
                    driverRepository.save(driver);
                    DriverDto driverDto;
                    driverDto = new DriverDto(driver);
                    driverDto.add(linkTo(methodOn(DriverController.class).makeAvailableFromBusy(driver.getId())).withRel("Finish"));
                    return driverDto;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado."));
    }
    @Operation(summary = "Atualizar o status de um driver de Busy para Available pelo seu id", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do driver atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Driver não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/finish/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public DriverDto makeAvailableFromBusy(@PathVariable UUID id){

        return driverRepository.findById(id)
                .map(driver -> {
                    if (driver.getStatus() == DriverStatus.BUSY){
                        driver.setStatus(DriverStatus.AVAILABLE);
                    }
                    driverRepository.save(driver);
                    DriverDto driverDto;
                    driverDto = new DriverDto(driver);
                    driverDto.add(linkTo(methodOn(DriverController.class).makeBusyFromAvailable(driver.getId())).withRel("Accept"));
                    driverDto.add(linkTo(methodOn(DriverController.class).makeOfflineFromAvailable(driver.getId())).withRel("Stop"));
                    return driverDto;

                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));
    }
}