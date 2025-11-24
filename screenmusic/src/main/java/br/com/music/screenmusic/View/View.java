package br.com.music.screenmusic.View;

import br.com.music.screenmusic.models.Artista;
import br.com.music.screenmusic.models.Musica;
import br.com.music.screenmusic.models.TipoArtista;
import br.com.music.screenmusic.repository.IArtistaRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//Spring passa a gerenciar a classe
@Component
public class View {
    private final Scanner sc = new Scanner(System.in);
    //injeção da interface
    private final IArtistaRepository iArtistaRepository;

    public View(IArtistaRepository iArtistaRepository) {
        this.iArtistaRepository = iArtistaRepository;
    }

    public void exibeMenu() {
        int opcao = 0;
        while (opcao != 9) {
            System.out.println("\n*** SCREEN MUSIC ***");
            System.out.println("Escolha a opção:");
            System.out.println("1. Salvar artista");
            System.out.println("2. Adicionar música");
            System.out.println("3. Listar artistas");
            System.out.println("4. Listar músicas");
            System.out.println("5. Top 3 artistas com mais musicas");
            System.out.println("9. Sair");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    adicionaArtista();
                    break;
                case 2:
                    adicionaMusica();
                    break;
                case 3:
                    listaArtistas();
                    break;
                case 4:
                    listaMusicas();
                    break;
                case 5:
                    top3Artistas();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void adicionaArtista() {
        System.out.println("Digite o nome do artista:");
        String nomeArtista = sc.nextLine();

        System.out.println("Digite a descrição do artista:");
        String descricaoArtista = sc.nextLine();

        try {
            System.out.println("O artista é 'Solo', 'Dupla' ou 'Banda'? ");
            TipoArtista tipoArtista = TipoArtista.valueOf(sc.nextLine().toUpperCase().trim());

            Artista artista = new Artista(nomeArtista, descricaoArtista, tipoArtista);
            iArtistaRepository.save(artista);
            System.out.println("Artista salvo com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido! Selecione SOLO, DUPLA ou BANDA");
        }
    }

    private void adicionaMusica() {
        listaArtistas();
        System.out.println("Digite o ID do artista para adicionar a música: ");
        Long artistaId = Long.parseLong(sc.nextLine());

        //artista recebe o id que foi digitado
        Artista artista = iArtistaRepository.findById(artistaId)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado!"));

        System.out.println("Digite o título da música:");
        String titulo = sc.nextLine();

        Musica musica = new Musica(titulo, artista);
        //add a musica na lista
        artista.getMusicas().add(musica);

        //resalva o artista (q nao muda nada)
        //de forma indireta salva a musica
        //o artista possui mapeamento do tipo cascata
        iArtistaRepository.save(artista);
        System.out.println("Música '" + titulo + "' adicionada ao artista " + artista.getNome());
    }

    private void listaArtistas() {
        List<Artista> artistas = iArtistaRepository.findAll();
        if (artistas.isEmpty()) {
            System.out.println("Nenhum artista cadastrado.");
            return;
        }
        System.out.println("Artistas cadastrados:");
        artistas.forEach(a -> System.out.println("\nID: " + a.getId() + "\nArtista: " + a.getNome() + "\nDescrição: " +
                a.getDescricaoArtista() + "\nTipo: " + a.getTipoArtista()));
    }

    private void listaMusicas() {
        listaArtistas();
        System.out.println("Digite o ID do artista para listar suas músicas: ");
        Long artistaId = Long.parseLong(sc.nextLine());

        Artista artista = iArtistaRepository.findById(artistaId)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        List<Musica> musicas = artista.getMusicas();

        musicas.stream()
                .sorted(Comparator.comparing(Musica::getNome))
                .forEach(m -> System.out.println("\nMúsica: " + m.getNome() + "\n Artista: " + m.getArtista().getNome()));
    }

    private void top3Artistas() {
        List<Artista> top3ArtistasEncontrados = iArtistaRepository.top3Artistas();

        System.out.println("*********** Top 3 Artistas ***********");

        top3ArtistasEncontrados.stream()
                .forEach(a -> {
                    System.out.println("\nArtista: " + a.getNome());
                    a.getMusicas().stream()
                            .forEach(m -> System.out.println("Música: " + m.getNome()));
                });
    }

}