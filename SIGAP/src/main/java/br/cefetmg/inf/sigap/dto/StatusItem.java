package br.cefetmg.inf.sigap.dto;

/**
 * Status de um item que está no sistema
 */
public enum StatusItem {
    /**
     * Item foi colocado por um aluno que perdeu esse item
     */
    PERDIDO,

    /**
     * Item foi encontrado e colocado no sistema, porém
     * não houveram baixas
     */
    ENCONTRADO,

    /**
     * Item já foi devolvido para quem o perdeu
     */
    DEVOLVIDO
}
