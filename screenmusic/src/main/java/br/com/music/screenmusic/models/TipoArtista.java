package br.com.music.screenmusic.models;

public enum TipoArtista {
    SOLO ("Solo"),
    DUPLA ("Dupla"),
    BANDA ("Banda");

    //este atributo é relacionado ao valor Solo, dupla, banda
    private final String descricao;

    TipoArtista(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
