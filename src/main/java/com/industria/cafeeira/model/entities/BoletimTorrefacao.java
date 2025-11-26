package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "boletimtorrefacao")

public class BoletimTorrefacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Obrigatorio")
    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "ordem_servicos", nullable = false)
    private String ordemServicos;

    @Column(name = "tipo_cafe", nullable = false)
    private String tipoCafe;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrdemServicos() {
        return ordemServicos;
    }

    public void setOrdemServicos(String ordemServicos) {
        this.ordemServicos = ordemServicos;
    }

    public String getTipoCafe() {
        return tipoCafe;
    }

    public void setTipoCafe(String tipoCafe) {
        this.tipoCafe = tipoCafe;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
