package br.com.music.screenmusic.repository;

import br.com.music.screenmusic.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("FROM Artista a ORDER BY SIZE (a.musicas) DESC LIMIT 3")
    List<Artista> top3Artistas();
}
