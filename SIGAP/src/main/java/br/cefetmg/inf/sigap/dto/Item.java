package br.cefetmg.inf.sigap.dto;

import java.time.LocalDate;

/**
 * Classe responsável pela estrutura interna e pelo acesso
 * de um item perdido ou encontrado
 *
 * @version 0.1
 * @author Arthur Tolomelli
 */
public class Item{

    /**
     * Identificação do item perdido dentro do sistema
     */
    private Long id;

    /**
     * Identificação do usuário que submeteu esse item
     */
    private Long uid;

    /**
     * Nome do item perdido
     */
    private String nome;

    /**
     * Data em que o item foi perdido
     */
    private LocalDate dataPerdido;

    /**
     * Data em que o item foi encontrado
     */
    private LocalDate dataAchado;

    /**
     * Data em que o item foi devolvido
     */
    private LocalDate dataDevolvido;

    /**
     * Local onde o item está
     */
    private String local;

    /**
     * Descrição do item perdido
     */
    private String descricao;

    /**
     * Descrição do lugar onde foi achado
     */
    private String lugarAchado;

    /**
     * Descrição do lugar onde foi perdido
     */
    private String lugarPerdido;

    /**
     * Caminho para a foto do item/similar dentro do sistema
     */
    private String foto;

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
    public Item(Long uid, String nome, LocalDate dataPerdido, LocalDate dataAchado, LocalDate dataDevolvido, String local, String descricao,
                String lugarAchado, String lugarPerdido, String foto, StatusItem status) {
        this.uid = uid;
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

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataPerdido() {
        return dataPerdido;
    }

    public LocalDate getDataAchado() {
        return dataAchado;
    }

    public LocalDate getDataDevolvido() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataPerdido(LocalDate dataPerdido) {
        this.dataPerdido = dataPerdido;
    }

    public void setDataAchado(LocalDate dataAchado) {
        this.dataAchado = dataAchado;
    }

    public void setDataDevolvido(LocalDate dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setLugarAchado(String lugarAchado) {
        this.lugarAchado = lugarAchado;
    }

    public void setLugarPerdido(String lugarPerdido) {
        this.lugarPerdido = lugarPerdido;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Boolean copare(Item comparando)
    {
        if(comparando.nome.equals(nome))
            if(dataAchado.isAfter(comparando.dataPerdido) || dataAchado.isEqual(comparando.dataPerdido))
                if(lugarAchado!=null && comparando.lugarPerdido!=null)
                {
                    if(comparando.lugarPerdido.equals(lugarAchado))
                    {
                        return true;
                    } 
                }   else{
                    return true;
                }
        return false;
    }
}
