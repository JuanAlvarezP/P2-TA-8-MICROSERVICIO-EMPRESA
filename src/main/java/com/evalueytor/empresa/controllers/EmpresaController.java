package com.evalueytor.empresa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalueytor.empresa.models.Empresa;
import com.evalueytor.empresa.models.Proveedor;
import com.evalueytor.empresa.repositories.EmpresaRepository;
import com.evalueytor.empresa.repositories.ProveedorRepository;

@RestController
@RequestMapping("/api")
public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ProveedorRepository proveedorRepository;

    // Listar todo
    @GetMapping("empresa/findall")
    public List<Empresa> listarEmpresa() {
        return empresaRepository.findAll();
    }

    // Listar por Id
    @GetMapping("/empresa/findbyid/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> premioOptional = empresaRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva empresa
    @PostMapping("/empresa/save")
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa nuevoPremio) {
        Empresa premioGuardado = empresaRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }

    // Actualizar empresa
    @PutMapping("/empresa/updatebyid/{id}")
    public ResponseEntity<Empresa> actualizarEmopresa(@PathVariable Long id, @RequestBody Empresa empresaActual) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        return empresaOptional.map(empresa -> {
            empresa.setId(id);
            empresa.setNombre(empresaActual.getNombre());
            empresa.setDireccion(empresaActual.getDireccion());
            empresa.setRuc(empresaActual.getRuc());
            empresa.setRepresentante_legal(empresaActual.getRepresentante_legal());
            empresa.setTelefono(empresaActual.getTelefono());
            Empresa empresaActualGuardado = empresaRepository.save(empresa);
            return new ResponseEntity<>(empresaActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("proveedor/findall")
    public List<Proveedor> listarProveedor() {
        return proveedorRepository.findAll();
    }

    // Listar por Id
    @GetMapping("/proveedor/findbyid/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        return proveedorOptional.map(proveedor -> new ResponseEntity<>(proveedor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva empresa
    @PostMapping("/proveedor/save")
    public ResponseEntity<Empresa> crearProveedor(@RequestBody Proveedor nuevoProveedor) {
        Empresa proveedorGuardado = empresaRepository.save(nuevoProveedor);
        return new ResponseEntity<>(proveedorGuardado, HttpStatus.CREATED);
    }


}
