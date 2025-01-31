package br.cefetmg.inf.sigap.backend;

import br.cefetmg.inf.sigap.dto.Usuario;

import static br.cefetmg.inf.sigap.service.UsuarioService.*;

public class BuscaAluno {
    public static long[] buscaAluno(String nome, String sobreNome)
    {
        Usuario usuarios[] = listarUsuarios();
        int cont = usuarios.length;
        int num = 0;
        for(int i = 0; i<cont; i++)
        {
            String nomeAtual = usuarios[i].getNome();
            if(nomeAtual.contains(nome) && nomeAtual.contains(sobreNome))
            {
                num++;
            }
        }

        long[] ids = new long[num];
        num=0;

        for(int i = 0; i<cont; i++)
        {
            String nomeAtual = usuarios[i].getNome();
            if(nomeAtual.contains(nome) && nomeAtual.contains(sobreNome))
            {
                ids[num] = (long) usuarios[i].getId();
                num++;
            }
        }
        return ids;
    }
}
