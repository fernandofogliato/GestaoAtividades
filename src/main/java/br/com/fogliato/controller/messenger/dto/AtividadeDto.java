package br.com.fogliato.controller.messenger.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.fogliato.controller.rest.validation.CheckTipoAtividade;

public class AtividadeDto {

    private Long idAtividade;

    @NotNull(message = "Por favor, informe o título da atividade.")
    private String titulo;

    @NotNull(message = "Por favor, informe a descrição da atividade.")
    private String descricao;

    @CheckTipoAtividade
    private String tipoAtividade;

    private LocalDateTime dataConclusao;

    public Long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Long idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idAtividade == null) ? 0 : idAtividade.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AtividadeDto other = (AtividadeDto) obj;
        if (idAtividade == null) {
            if (other.idAtividade != null)
                return false;
        } else if (!idAtividade.equals(other.idAtividade))
            return false;
        return true;
    }

}
