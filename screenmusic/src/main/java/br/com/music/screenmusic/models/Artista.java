package br.com.music.screenmusic.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table

@Component
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricaoArtista;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

    public Artista(String nome, String descricaoArtista, TipoArtista tipoArtista) {
        this.nome = nome;
        this.descricaoArtista = descricaoArtista;
        this.tipoArtista = tipoArtista;
    }

    public Artista() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoArtista() {
        return descricaoArtista;
    }

    public void setDescricaoArtista(String descricaoArtista) {
        this.descricaoArtista = descricaoArtista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricaoArtista='" + descricaoArtista + '\'' +
                ", musicas=" + musicas +
                ", tipoArtista=" + tipoArtista +
                '}';
    }
}