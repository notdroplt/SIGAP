package br.cefetmg.inf.sigap.service;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImagemService {
    private static ImagemService single = null;
    private static final String caminhoImagens = "/var/sigap/imagens";

    private ImagemService() throws IOException {
        Files.createDirectories(Paths.get(caminhoImagens));
    }

    public static String getCaminhoImagens() {
        return caminhoImagens;
    }

    /**
     * Transformar o serviço em singleton elimina problemas de paralelismo
     * @return instância de ItemService
     */
    public static ImagemService getInstance() throws IOException {
        if (single == null) single = new ImagemService();

        return single;
    }

    /**
     * Adiciona uma imagem para o servidor, e a salva como
     * @param b64 base64 da imagem recebida
     * @return caminho da imagem no servidor
     */
    public String adicionarImagem(String b64)  {
        try {
            return String.valueOf(Files.writeString(
                    Path.of(caminhoImagens + "/" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)),
                    b64
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adiciona uma imagem para o servidor, e a salva como
     * @param nome nome da imagem
     * @return base64 da imagem
     */
    public String recuperarImagem(String nome) {
        try {
            return Files.readString(Path.of(nome));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
