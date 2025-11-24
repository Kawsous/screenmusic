package br.com.music.screenmusic.models;

import jakarta.persistence.*;

@Entity
@Table
public class Musica {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Musica(String nome, Artista artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public Musica() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}