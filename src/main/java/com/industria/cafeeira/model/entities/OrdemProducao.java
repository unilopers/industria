package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ordens_producao")
public class OrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String codigo;

    @ManyToOne
    @JoinColumn(name="lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name="etiqueta_id")
    private Etiqueta etiqueta;

    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
}
