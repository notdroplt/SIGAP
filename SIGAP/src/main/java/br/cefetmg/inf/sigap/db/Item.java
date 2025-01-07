package br.cefetmg.inf.sigap.db;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

/**
 * Classe responsável pela estrutura interna e pelo acesso
 * de um item perdido ou encontrado
 *
 * @version 0.1
 * @author Arthur Tolomelli
 */
@Entity
@Table(name = "Item")
public final class Item implements Serializable {

    /**
     * Identificação do item perdido dentro do sistema
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Nome do item perdido
     */
    @Column(name = "nome", nullable = false, length = 40)
    private final String nome;

    /**
     * Data em que o item foi perdido
     */
    @Column(name = "perdido", nullable = false)
    private final Date dataPerdido;

    /**
     * Data em que o item foi encontrado
     */
    @Column(name = "achado", nullable = true)
    private final Date dataAchado;

    /**
     * Data em que o item foi devolvido
     */
    @Column(name = "devolvido", nullable = true)
    private final Date dataDevolvido;

    /**
     * Local onde o item está
     */
    @Column(name = "local", nullable = false, length = 3)
    private final String local;

    /**
     * Descrição do item perdido
     */
    @Column(name = "descricao", nullable = false, length = 144)
    private final String descricao;

    /**
     * Descrição do lugar onde foi achado
     */
    @Column(name = "lugarA", nullable = true, length = 144)
    private final String lugarAchado;

    /**
     * Descrição do lugar onde foi perdido
     */
    @Column(name = "lugarP", nullable = false, length = 144)
    private final String lugarPerdido;

    /**
     * Caminho para a foto do item/similar dentro do sistema
     */
    @Column(name = "foto", nullable = false)
    private final String foto;

    /**
     * Status do item que foi perdido
     */
    private StatusItem status;

    /**
     * Construtor de um item
     *
     * @param nome nome do item
     * @param dataPerdido data em que foi perdido
     * @param dataAchado data em que foi achado
     * @param dataDevolvido data em que foi devolvido
     * @param local local onde está situado
     * @param descricao descrição do item
     * @param lugarAchado lugar em que foi achado
     * @param lugarPerdido lugar em que foi perdido
     * @param foto caminho da foto do item
     */
    public Item(String nome, Date dataPerdido, Date dataAchado, Date dataDevolvido, String local, String descricao,
                String lugarAchado, String lugarPerdido, String foto, StatusItem status) {
        this.nome = nome;
        this.dataPerdido = dataPerdido;
        this.dataAchado = dataAchado;
        this.dataDevolvido = dataDevolvido;
        this.local = local;
        this.descricao = descricao;
        this.lugarAchado = lugarAchado;
        this.lugarPerdido = lugarPerdido;
        this.foto = foto;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataPerdido() {
        return dataPerdido;
    }

    public Date getDataAchado() {
        return dataAchado;
    }

    public Date getDataDevolvido() {
        return dataDevolvido;
    }

    public String getLocal() {
        return local;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLugarAchado() {
        return lugarAchado;
    }

    public String getLugarPerdido() {
        return lugarPerdido;
    }

    public String getFoto() {
        return foto;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }


}
