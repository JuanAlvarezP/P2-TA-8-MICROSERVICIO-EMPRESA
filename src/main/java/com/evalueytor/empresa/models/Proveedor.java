package com.evalueytor.empresa.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data

public class Proveedor extends Empresa{
    private long calificacion;
}
