package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigoPedido", unique = true, nullable = false)
    private String codigoPedido;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

//    @ManyToOne
//    @JoinColumn(name = "idUsuario")
//    private Usuario usuario;
}
