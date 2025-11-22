package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, insertable = false, updatable = false)
    private String codigo;

    @Column(nullable = true)
    private long fk_fornecedor;

    @Column(nullable = true)
    private long fk_produto;

    private String tipo;
    private BigDecimal quantidade;
    private Date data_vencimento;
    private String descricao;

    public Lote(){

    }

    public Lote(Long id,
                String codigo,
                long fk_fornecedor,
                long fk_produto,
                String tipo,
                BigDecimal quantidade,
                Date data_vencimento,
                String descricao){

        this.id = id;
        this.codigo = codigo;
        this.fk_fornecedor = fk_fornecedor;
        this.fk_produto = fk_produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data_vencimento = data_vencimento;
        this.descricao = descricao;
    }

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

    public long getFk_fornecedor() {
        return fk_fornecedor;
    }

    public void setFk_fornecedor(long fk_fornecedor) {
        this.fk_fornecedor = fk_fornecedor;
    }

    public long getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(long fk_produto) {
        this.fk_produto = fk_produto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
