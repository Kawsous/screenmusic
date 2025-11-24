package br.com.music.screenmusic;

import br.com.music.screenmusic.View.View;
import br.com.music.screenmusic.repository.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmusicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmusicApplication.class, args);
	}

    private final View view;

    public ScreenmusicApplication(View view) {
        this.view = view;
    }

    @Override
    public void run(String... args) throws Exception {
        view.exibeMenu();
    }
}